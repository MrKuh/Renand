package at.htlkaindorf.game;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.ObstacleManager;
import at.htlkaindorf.entity.Player;
import at.htlkaindorf.tile.TileManager;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;


@Data
public class GamePanel extends JPanel implements Runnable {
    //window size
    public static final int originalTileSize = 16;
    public static final int scale = 6;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 9;
    private int screenWidth = tileSize * maxScreenCol; //(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = tileSize * maxScreenRow; //(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //fullscreen trigger needed
    private boolean fullScreen;
    private Display display;
    //Fonts
    Font myFont40 = new Font("Courier New", 1, 40);
    Font myFont20 = new Font("Courier New", 1, 20);
    Color fontColor = Color.WHITE;

    //StartScreen
    private boolean showStartScreen = true;

    //FPS
    public static int FPS = 60;
    //Key Handler
    private KeyHandler keyH = new KeyHandler(this);
    //gameThread
    private Thread gameThread;

    //Collision
    private ObstacleManager obstacleManager = new ObstacleManager(this);

    //score
    private int score = 0;
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private int scoreY = 50;
    private Player player = new Player(this, keyH);

    //World Speed
    public static double xspeed = 5.0;

    //Tile
    private TileManager tileManager = new TileManager(this);

    //Game State
    private boolean running = false;
    private boolean paused = false;

    private boolean runWithEnemies = false;

    public void resetTheGame() {
        obstacleManager = new ObstacleManager(this);
        player = new Player(this, keyH);
        tileManager = new TileManager(this);
    }

    public void collision() {
        paused = false;
        running = false;
        scores.add(score);
    }


    public void startGameThread() {


        try {
            String bip = "res/audio/indianSong.wav";
            File file = new File(bip);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }


        // Create an AudioStream object from the input stream.



        running = true;
        paused = false;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#0f0f0f0f"));
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        fullScreen = false;
    }


    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                if (running) {
                    update();
                    repaint();
                    //score++;
                }

                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS " + drawCount);
                System.out.println(paused);
                System.out.println(running);

                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        player.update();
        if (runWithEnemies) {
            obstacleManager.update();
        }
    }


    public void drawScore(Graphics2D g2) {
        g2.setFont(myFont40);
        g2.setColor(fontColor);

        String formatted = String.format("%07d", score);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY);
        formatted = String.format("HighScore: %-7d", (scores.size() == 0) ? 0 : scores.get(0));
        g2.setFont(myFont20);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY + scoreY / 2);
    }

    public void drawDeathScreen(Graphics2D g2) {

            try {
                BufferedImage[] deathScreen = new BufferedImage[]{
                        ImageIO.read(getClass().getResourceAsStream("/gameover/GameOverJetpack.png"))
                };
                //(screenHeight/2) - (deathScreen[0].getHeight()/2)
                g2.drawImage(deathScreen[0], (screenWidth / 2) - (deathScreen[0].getWidth()), deathScreen[0].getHeight(), deathScreen[0].getWidth() * 2, deathScreen[0].getHeight() * 2, null);

                g2.setFont(myFont40);
                g2.setColor(fontColor);

                int space = 0;
                Collections.sort(scores);
                scores = (ArrayList<Integer>) scores.stream().distinct().collect(Collectors.toList());
                Collections.reverse(scores);
                int scoreAmount = 5;
                if (scores.size() < scoreAmount) {
                    scoreAmount = scores.size();
                }
                FontMetrics metrics = g2.getFontMetrics(g2.getFont());
                for (int i = 0; i < scoreAmount; i++) {

                    String formatted = "HighScore " + (i + 1) + ": " + String.format("%07d", scores.get(i));


                    g2.drawString(formatted, (screenWidth / 2) - metrics.stringWidth(formatted) / 2, ((deathScreen[0].getHeight()) * 4) + space);
                    space += metrics.getHeight();
                }

                space = metrics.getHeight();


                String text = "Press ENTER to restart the game.";
                g2.drawString(text, screenWidth / 2 - metrics.stringWidth(text) / 2, screenHeight - tileSize - 40);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public void drawStartScreen(Graphics2D g2) {

            try {
                BufferedImage[] startScreen = new BufferedImage[]{
                        ImageIO.read(getClass().getResourceAsStream("/gameover/startSmall.png"))
                };

                int width = startScreen[0].getWidth() * 8;
                int height = startScreen[0].getHeight() * 8;
                g2.drawImage(startScreen[0], screenWidth / 2 - width / 2, (int) (height * 1.5), width, height, null);


                g2.setFont(myFont40);
                g2.setColor(fontColor);

                String text = "Press SPACE to start the game.";
                FontMetrics metrics = g2.getFontMetrics(g2.getFont());
                g2.drawString(text, screenWidth / 2 - metrics.stringWidth(text) / 2, screenHeight - tileSize - 40);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //background
        g2.setColor(Color.decode("#198dde"));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        //set main font
        g2.setFont(myFont40);
        g2.setColor(fontColor);

        tileManager.draw(g2);
        player.draw(g2);
        obstacleManager.draw(g2);

        drawScore(g2);
        if (!running && !paused) drawDeathScreen(g2);
        if (showStartScreen) drawStartScreen(g2);

        g2.dispose();
    }


}
