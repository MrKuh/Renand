package at.htlkaindorf.display;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.Player;
import at.htlkaindorf.tile.TileManager;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class GamePanel extends JPanel implements Runnable {
    //window size
    public static final int originalTitleSize = 16;
    public static final int scale = 6;

    public static final int tileSize = originalTitleSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 9;
    public static int screenWidth = tileSize * maxScreenCol;//(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int screenHeight = tileSize * maxScreenRow;//(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //fullscreen trigger needed
    private boolean fullScreen;
    private Display display;

    //FPS
    public static int FPS = 60;
    //Key Handler
    private KeyHandler keyH = new KeyHandler(this);
    //gameThread
    private Thread gameThread;

    //Player data
    private int playerX = 100;
    private int playerY = 100;
    private double playerSpeed = 12.0;
    private double playerGravity = 8.0;

    private Player player = new Player(this, keyH);

    //World Speed
    public static double xspeed = 6.0;

    //Tile
    private TileManager tileManager = new TileManager(this);


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#0088ff"));
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
                update();
                repaint();
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();
    }


}
