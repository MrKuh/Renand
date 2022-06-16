package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.user.UserList;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Data
public class Player extends Entity {
    private KeyHandler keyHandler;

    private int runIMG;
    private int flyIMG;
    private BufferedImage[] runImages;
    private BufferedImage[] flyImages;

    //for xml
    private String xmlFile;
    //for xml
    private UserList userList;

    private boolean additionalHeart;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        this.additionalHeart = false;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = gamePanel.getScreenHeight() - 150;
        speed = 5.0;

        runIMG = 1;
        flyIMG = 0;

        hitBox = new Rectangle();
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.31) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.19) + y;
        hitBox.width = (int) Math.round(gamePanel.tileSize * 0.44);
        hitBox.height = (int) Math.round(gamePanel.tileSize * 0.81);
    }

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
            if(speed > 5){
                speed = 5;
            }

            //speed = 15;
            speed -= 1.3;
            if (speed < -17.0) {
                speed = -20;
            }
        } else {
            //speed = -10;
            speed += 0.7;
            if (speed > 15.0) {
                speed = 15;
            }
        }
        y += speed;

        if (y < 0) {
            y = 0;
            //scuffed wiggle animation
            speed = 5;
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

    public void draw(Graphics2D g2) {
        gamePanel.getObstacleManager().checkCollision(this, g2);
        if (keyHandler.isSpacePressed()) {
            g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        } else {
            g2.drawImage(runImages[runIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
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
