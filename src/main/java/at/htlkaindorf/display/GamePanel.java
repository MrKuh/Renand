package at.htlkaindorf.display;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.Player;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class GamePanel extends JPanel implements Runnable {
    //window size
    private static final int originalTitleSize = 16;
    private static final int scale = 4;

    public static final int titleSize = originalTitleSize * scale;
    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = titleSize * maxScreenCol;
    public static final int screenHeight = titleSize * maxScreenRow;

    //FPS
    public static int FPS = 60;
    //Key Handler
    private KeyHandler keyH = new KeyHandler();
    //gameThread
    private Thread gameThread;

    //Player data
    private int playerX = 100;
    private int playerY = 100;
    private double playerSpeed = 12.0;
    private double playerGravity = 8.0;

    private Player player = new Player(this, keyH);


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        player.draw(g2);

        g2.dispose();
    }


}
