package at.htlkaindorf.game;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.ObstacleManager;
import at.htlkaindorf.entity.Player;
import at.htlkaindorf.strategy.GiftManager;
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

/**
 * This class {@code GamePanel} is the main class, which manages objects of every other class. <br>
 * It is contains every sound, animation, score, player, enemies and window features.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.87
 */
@Data
public class GamePanel extends JPanel implements Runnable {
    //window size
    /**
     * This constant contains the original size of a tile ({@code Player} and {@code PurpleMonster}).
     */
    public static final int originalTileSize = 16;
    /**
     * This constant contains the scale that the {@code originalTileSize} will be multiplied with.
     */
    public static final int scale = 6;
    /**
     * This constant is the tile size that you will see onscreen.
     */
    public static final int tileSize = originalTileSize * scale;
    /**
     * This constant defines how many tiles can be displayed at the same time side by side.
     */
    public static final int maxScreenCol = 16;
    /**
     * This constant defines how many tiles can be displayed at the same time among themselves.
     */
    public static final int maxScreenRow = 9;
    /**
     * This variable defines the screen width of the window by multiplying the {@code tileSize} and the {@code maxScreenCol}.
     */
    private int screenWidth = tileSize * maxScreenCol; //(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * This variable defines the screen height of the window by multiplying the {@code tileSize} and the {@code maxScreenRow}.
     */
    private int screenHeight = tileSize * maxScreenRow; //(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * This {@link Boolean} shows if the application is in fullscreen or not.
     */
    private boolean fullScreen;
    /**
     * This object from {@code Display} is needed to pass it to {@code KeyHandler}.
     */
    private Display display;

    /**
     * This variable contains the main theme music of the game.
     */
    private Clip mainSound;
    /**
     * This variable contains the fly sound of the {@code Player} of the game.
     */
    private Clip flySound;
    /**
     * This variable contains the fly-loop sound of the {@code Player} of the game.<br>
     * It is needed, when you hold on longer on the space bar.
     */
    private Clip flyLoopSound;
    /**
     * This variable contains the walk sound of the {@code Player} of the game.
     */
    private Clip walkSound;
    /**
     * This variable contains the sound that will be played after the {@code Player} died.
     */
    private Clip collisionSound;


    /**
     * This constant contains our font with the size of 40.
     */
    private static final Font myFont40 = new Font("Courier New", 1, 40);
    /**
     * This constant contains our font with the size of 20.
     */
    private static final Font myFont20 = new Font("Courier New", 1, 20);
    /**
     * This constant contains our font color for the text.
     */
    private static final Color fontColor = Color.WHITE;

    /**
     * This {@link Boolean} decides if the start screen should show.
     */
    private boolean showStartScreen = true;

    /**
     * This constant defines how many frames per second the game should have.
     */
    public static int FPS = 60;
    /**
     * This variable is used to get information of the {@code KeyHandler}, which Button has been clicked.
     */
    private KeyHandler keyHandler = new KeyHandler(this);
    /**
     * This {@link Thread} is the main thread of the game.
     */
    private Thread gameThread;

    /**
     * This variable contains the manager for the purple monsters aka {@code ObstacleManager}.
     */
    private ObstacleManager obstacleManager = new ObstacleManager(this);

    /**
     * This variable contains the manager for the gifts aka {@code GiftManager}.
     */
    private GiftManager giftManager = new GiftManager(this);

    /**
     * This variable contains the score of the current run.
     */
    private int score = 0;
    /**
     * This {@link ArrayList} contains all current highScores.
     */
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    /**
     * This variable contains y coordinate of the {@code score}.
     */
    private int scoreY = 50;
    /**
     * This variable contains the {@code Player} object. <br>
     * It is needed to print a player and to check if the player has hit a purple Monster.
     */
    private Player player = new Player(this, keyHandler);

    /**
     * This variable contains the added game speed. <br>
     * It increases after time.
     */
    private int addedGameSpeed = 0;
    /**
     * This variable contains the default game speed.
     */
    private double gameSpeed = 5.0;

    /**
     * This variable contains the manager for the clouds aka {@code ObstacleManager}.
     */
    private TileManager tileManager = new TileManager(this);

    /**
     * This {@link Boolean} decides if the running animation should be played.
     */
    private boolean running = false;
    /**
     * This {@link Boolean} decides if the game should be paused.
     */
    private boolean paused = false;
    /**
     * This {@link Boolean} decides if the game should run with or without enemies.
     */
    private boolean runWithEnemies = false;

    /**
     * This function resets the game after the death screen.
     */
    public void resetTheGame() {
        addedGameSpeed = 0;
        gameSpeed = 5.0;
        obstacleManager = new ObstacleManager(this);
        player = new Player(this, keyHandler);
        tileManager = new TileManager(this);
        giftManager = new GiftManager(this);
        collisionSound.stop();
        collisionSound.setFramePosition(0);
        mainSound.loop(Clip.LOOP_CONTINUOUSLY);

    }

    /**
     * This function stops everything. <br>
     * Only called after the {@code Player} has hit a {@code PurpleMonster}.
     */
    public void collision() {
        mainSound.stop();
        walkSound.stop();
        flySound.stop();
        flyLoopSound.stop();
        mainSound.setFramePosition(0);
        collisionSound.loop(Clip.LOOP_CONTINUOUSLY);
        paused = false;
        running = false;
        scores.add(score);
    }

    /**
     * This function initializes the audio objects.
     */
    public void initSounds() {
        try {
            File file = new File("res/audio/main.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            mainSound = AudioSystem.getClip();
            mainSound.open(audioInputStream);
            mainSound.loop(Clip.LOOP_CONTINUOUSLY);

            file = new File("res/audio/fly.wav");
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(file);
            flySound = AudioSystem.getClip();
            flySound.open(audioInputStream2);

            flySound.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (keyHandler.isSpacePressed() && event.getFramePosition() > 140000) {
                        //System.out.println(event.getFramePosition());
                        flyLoopSound.loop(Clip.LOOP_CONTINUOUSLY);
                    } else {
                        flyLoopSound.stop();
                    }
                }
            });

            file = new File("res/audio/loop.wav");
            AudioInputStream audioInputStream4 = AudioSystem.getAudioInputStream(file);
            flyLoopSound = AudioSystem.getClip();
            flyLoopSound.open(audioInputStream4);


            file = new File("res/audio/walk.wav");
            AudioInputStream audioInputStream5 = AudioSystem.getAudioInputStream(file);
            walkSound = AudioSystem.getClip();
            walkSound.open(audioInputStream5);


            file = new File("res/audio/collision.wav");
            AudioInputStream audioInputStream3 = AudioSystem.getAudioInputStream(file);
            collisionSound = AudioSystem.getClip();
            collisionSound.open(audioInputStream3);

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function start the game {@link Thread}.
     */
    public void startGameThread() {
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
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        fullScreen = false;

        initSounds();
    }

    /**
     * This is the {@code run()} of the {@code gameThread}. <br>
     * It manages everything with the frames per second.
     */
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
                //System.out.println("FPS " + drawCount);
                //System.out.println(paused);
                //System.out.println(running);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    /**
     * This function updates every manager, objects and adds the game speed.
     */
    public void update() {
        if (score / 100 - addedGameSpeed > 1 && gameSpeed < 10) {
            addedGameSpeed++;
            gameSpeed++;
        }

        player.update();
        if (runWithEnemies) {
            obstacleManager.update();
            giftManager.update();
        }
    }

    /**
     * This function draws the current score and highScore in the top right corner.
     *
     * @param g2 - the {@code Graphics2D} object for printing
     */
    public void drawScore(Graphics2D g2) {
        g2.setFont(myFont40);
        g2.setColor(fontColor);

        String formatted = String.format("%07d", score);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY);
        formatted = String.format("HighScore: %-7d", (scores.size() == 0) ? 0 : scores.get(0));
        g2.setFont(myFont20);
        g2.drawString(formatted, screenWidth - 2 * tileSize, scoreY + scoreY / 2);
    }

    /**
     * This function draws the death screen after the {@code Player} has hit a {@code PurpleMonster}.
     *
     * @param g2 - the {@code Graphics2D} object for printing
     */
    public void drawDeathScreen(Graphics2D g2) {

        try {
            BufferedImage[] deathScreen = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/game/GameOverJetpack.png"))
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

    /**
     * This function draws the start screen at the beginning and after skipping the death screen.
     *
     * @param g2 - the {@code Graphics2D} object for printing
     */
    public void drawStartScreen(Graphics2D g2) {

        try {
            BufferedImage[] startScreen = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/game/renand.png")),
                    ImageIO.read(getClass().getResourceAsStream("/game/startSmall.png"))
            };

            int width = startScreen[0].getWidth() * 9;
            int height = startScreen[0].getHeight() * 9;
            g2.drawImage(startScreen[0], screenWidth / 2 - width / 2, (int) (height * 1.5), width, height, null);

            int width1 = startScreen[1].getWidth() * 3;
            int height1 = startScreen[1].getHeight() * 3;
            g2.drawImage(startScreen[1], screenWidth / 2 - width1 / 2, screenHeight/2 - (height1/2), width1, height1, null);

            g2.setFont(myFont40);
            g2.setColor(fontColor);

            String text = "Press SPACE to start the game.";
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            g2.drawString(text, screenWidth / 2 - metrics.stringWidth(text) / 2, screenHeight - tileSize - 40);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function calls every draw function of every object that is needed.
     *
     * @param g - to cast it to a {@link Graphics2D} object, so it can be used to print something
     */
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

        giftManager.draw(g2);

        drawScore(g2);
        if (!running && !paused) drawDeathScreen(g2);
        if (showStartScreen) drawStartScreen(g2);

        g2.dispose();
    }


}
