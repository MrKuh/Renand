package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.game.GamePanel;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class includes everything for the {@code Player} object.<br>
 * It contains the animation, handles the key inputs and moves the player up and down.
 * @author Mrkuh
 * @author Bensi
 * @version 1.53
 */
@Data
public class Player extends Entity {
    /**
     * This variable is used to get information of the {@code KeyHandler}, which Button has been clicked.
     */
    private KeyHandler keyHandler;
    /**
     * This variable defines which the current running Picture is.
     */
    private int runIMG;
    /**
     * This variable defines which the current flying Picture is.
     */
    private int flyIMG;
    /**
     * This variable contains all running images.
     */
    private BufferedImage[] runImages;
    /**
     * This variable contains all flying images
     */
    private BufferedImage[] flyImages;

    //player speed related variables
    /**
     * This variable defines the maximum speed that a {@code Player} can fly up.
     */
    private double maxUpSpeed;
    /**
     * This variable defines the maximum speed that a {@code Player} can fly down.
     */
    private double maxDownSpeed;
    /**
     * This variable defines the speed that will be added to the {@code Player}-speed every frame the space bar is pressed.
     */
    private double flyUpSpeed;
    /**
     * This variable defines the speed that will be added to the {@code Player}-speed every frame the space bar is not pressed.
     */
    private double flyDownSpeed;
    /**
     * This variable lets the {@code Player} fly up faster.
     */
    private double maxDownWhenUpSpeed;
    /**
     * This {@link Boolean} tells the {@code GamePanel} if an additional heart is present.
     */
    private boolean additionalHeart;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        this.additionalHeart = false;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * This function sets the values to its default.<br>
     * Only needed by constructor.
     */
    public void setDefaultValues() {
        x = 100;
        y = gamePanel.getScreenHeight() - 150;
        speed = 5.0;

        maxUpSpeed = -20.0;
        maxDownSpeed = 12.0;
        flyUpSpeed = -1.3;
        flyDownSpeed = 0.6;
        maxDownWhenUpSpeed = 5.0;


        runIMG = 1;
        flyIMG = 0;

        hitBox = new Rectangle();
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.31) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.19) + y;
        hitBox.width = (int) Math.round(gamePanel.tileSize * 0.44);
        hitBox.height = (int) Math.round(gamePanel.tileSize * 0.81);
    }

    /**
     * This function sets the run and fly images.
     */
    public void getPlayerImage() {
        try {
            runImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_0.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_00.png"))
            };
            flyImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_00.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_01.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_02.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_03.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_04.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_05.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_06.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_07.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_08.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_09.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_10.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/fly/sprite_11.png"))
            };

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the {@code update()} function. It changes the {@code speed} variable, <br>
     * the current fly image, the current run image and the hitBox of the player.
     */
    public void update() {
        //old function
        /*if (keyHandler.isSpacePressed()) {
            y -= speed;
            gravity = 3;
            speed *= 1.05;
        } else {
            y += gravity;
            gravity *= 1.05;
            speed = 5;
        }*/

        if (keyHandler.isSpacePressed()) {
            if(speed > maxDownWhenUpSpeed){
                speed = maxDownWhenUpSpeed;
            }

            //speed = 15;
            speed += flyUpSpeed;
            if (speed < maxUpSpeed) {
                speed = maxUpSpeed;
            }
        } else {
            //speed = -10;
            speed += flyDownSpeed;
            if (speed > maxDownSpeed) {
                speed = maxDownSpeed;
            }
        }
        y += speed;

        if (y < 0) {
            y = 0;
            //scuffed wiggle animation
            speed = maxDownWhenUpSpeed;
        }
        if (y > gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8) {
            y = (int) (gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8);
        }

        spriteCounter++;
        //character img set
        if (keyHandler.isSpacePressed()) {
            runIMG = runImages.length - 1;
            //Fly
            if (spriteCounter > gamePanel.FPS / flyImages.length / 2) {
                if (flyIMG < flyImages.length - 1) {
                    flyIMG++;
                } else {
                    flyIMG = 6;
                }
                spriteCounter = 0;
            }
        }else if(y == (int) (gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8)){
            flyIMG = 0;
            //Run
            if (spriteCounter > gamePanel.FPS / runImages.length / 4) {
                if (runIMG < runImages.length - 2) {
                    runIMG++;
                } else {
                    runIMG = 0;
                }
                spriteCounter = 0;
            }
        }
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.31) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.19) + y;
    }

    /**
     * This function draws the {@code Player} object and adds the walk-sound.
     * @param g2 - the {@code Graphics2D} object for printing
     */
    public void draw(Graphics2D g2) {
        gamePanel.getObstacleManager().checkCollision(this, g2);
        if (keyHandler.isSpacePressed()) {
            g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        } else {

            g2.drawImage(runImages[runIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if(y == (int) (gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8)
                && !gamePanel.getWalkSound().isActive()
                && gamePanel.isRunning()) {
            gamePanel.getWalkSound().loop(Clip.LOOP_CONTINUOUSLY);
        }
        if(y != (int) (gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8) && gamePanel.getWalkSound().isActive()){
            gamePanel.getWalkSound().stop();
        }

        //g2.draw(hitBox);
        //Collision
        /*
        if(!gamePanel.getObstacleManager().checkCollision(this, g2)){
            if (keyHandler.isSpacePressed()) {
                g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            } else {
                g2.drawImage(runImages[runIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            //g2.draw(hitBox);
        }

         */
    }
}
