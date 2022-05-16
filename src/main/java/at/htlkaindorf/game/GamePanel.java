package at.htlkaindorf.game;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.ObstacleManager;
import at.htlkaindorf.entity.Player;
import at.htlkaindorf.tile.TileManager;
import lombok.Data;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

@Data
public class GamePanel extends JPanel implements Runnable {
    //window size
    public static final int originalTileSize = 16;
    public static final int scale = 6;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 9;
    private int screenWidth = tileSize * maxScreenCol;//(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = tileSize * maxScreenRow;//(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //fullscreen trigger needed
    private boolean fullScreen;
    private Display display;

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

    private boolean running = false;

    public void resetTheGame() {
        running = false;
        scores.add(score);
        obstacleManager = new ObstacleManager(this);
        player = new Player(this, keyH);
        tileManager = new TileManager(this);
    }



    public void startGameThread() {

        running = true;
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
                    if(running){
                    update();
                    repaint();
                    score++;
                    }

                    delta--;
                    drawCount++;
                }
                if (timer >= 1000000000) {
                    System.out.println("FPS " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

    }

    public void update() {
        player.update();
        obstacleManager.update();
    }


    public void drawScore(Graphics2D g2 ) {
        g2.setColor(Color.decode("#00000"));
        Font myFont = new Font ("Courier New", 1, 40);
        g2.setFont(myFont);
        String formatted = String.format("%07d", score);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY);
        formatted = String.format("HighScore: %-7d", (scores.size()==0)?0:scores.get(0));
        myFont = new Font ("Courier New", 1, 20);
        g2.setFont(myFont);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY + scoreY/2);
    }
    public void drawDeathScreen(Graphics2D g2) {
        if(!running){
            try {
                BufferedImage[] deathScreen = new BufferedImage[]{
                        ImageIO.read(getClass().getResourceAsStream("/gameover/GameOverJetpack.png"))
                };
                //(screenHeight/2) - (deathScreen[0].getHeight()/2)
                g2.drawImage(deathScreen[0], (screenWidth/2) - (deathScreen[0].getWidth())  , deathScreen[0].getHeight(), deathScreen[0].getWidth()*2, deathScreen[0].getHeight()*2, null);

                g2.setColor(Color.decode("#00000"));
                Font myFont = new Font ("Courier New", 1, 40);
                g2.setFont(myFont);
                int space = 0;
                Collections.sort(scores);
                Collections.reverse(scores);
                int scoreAmount = 5;
                if(scores.size() < scoreAmount){
                    scoreAmount = scores.size();
                }
                for (int i = 0; i < scoreAmount; i++) {
                    String formatted = String.format("%07d", scores.get(i));
                    g2.drawString("HighScore "+ (i+1) + ": " + formatted, (screenWidth/2) - (deathScreen[0].getWidth()) , ((deathScreen[0].getHeight())*4) + space);
                    space += 30;
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.decode("#198dde"));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        tileManager.draw(g2);

        player.draw(g2);

        obstacleManager.draw(g2);

        drawDeathScreen(g2);
        drawScore(g2);

        g2.dispose();
    }


}
