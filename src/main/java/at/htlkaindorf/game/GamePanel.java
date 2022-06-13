package at.htlkaindorf.game;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.entity.ObstacleManager;
import at.htlkaindorf.entity.Player;
import at.htlkaindorf.tile.TileManager;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.Data;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    private boolean runWithEnemies = false;

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
        this.setSize(screenWidth, screenHeight);
        this.setBackground(Color.decode("#0f0f0f0f"));
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.setLayout(null);
        JLabel label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        this.add(label);
        JTextField username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        this.add(username);
        this.setVisible(true);

        JLabel password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        this.add(password1);

        JTextField password = new JPasswordField();
        password.setBounds(100, 75, 193, 28);
        this.add(password);
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
                    //score++;
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
        if(runWithEnemies) {
            obstacleManager.update();
        }
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
                scores = (ArrayList<Integer>) scores.stream().distinct().collect(Collectors.toList());
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
                String pressText = "Press ENTER to restart the game.";
                space += 30;
                g2.drawString(pressText,((int) (screenWidth/2.5)) - (deathScreen[0].getWidth()), screenHeight/5*4);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void drawMenu(Graphics2D g2){

        try {
            BufferedImage[] startScreen = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/gameover/start.png"))
            };
            int width = startScreen[0].getWidth() /4;
            int height = startScreen[0].getHeight() /4;
            g2.drawImage(startScreen[0], screenWidth/2- width/2 , height, width , height, null);

            int buttonWidth = 250;
            int buttonHeight = 50;
            g2.drawRect(screenWidth/2-buttonWidth/2, screenHeight/2 - buttonHeight/2, buttonWidth, buttonHeight);
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            String text = "Start Game";
            g2.drawString( text ,screenWidth/2 - metrics.stringWidth(text) / 2, screenHeight/2 -metrics.getHeight()/2);




        } catch (IOException e) {
            throw new RuntimeException(e);
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
        drawMenu(g2);



        g2.dispose();
    }


}
