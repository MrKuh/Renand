package at.htlkaindorf.display;

import at.htlkaindorf.controller.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private static final int originalTitleSize = 16;
    private static final int scale = 4;

    private static final int titleSize = originalTitleSize *scale;
    private static final int maxScreenCol= 16;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = titleSize * maxScreenCol;
    private static final int screenHeight = titleSize * maxScreenRow;

    //FPS
    private static int FPS = 60;

    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;

    //Player data
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 20;
    private int playerGravity = 8;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime- lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(keyH.isSpacePressed()){
            playerY -= playerSpeed;
        }
        if(playerY < 0){
            playerY = 0;
        }
        if(playerY > screenHeight - titleSize){
            playerY = screenHeight - titleSize;
        }
        playerY += playerGravity;


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, titleSize, titleSize);
        g2.dispose();
    }


}
