package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.display.GamePanel;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Data
public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;



    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5.0;

        runIMG = 1;
        flyIMG = 0;
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
            //speed = 15;
            speed -= 1.5;
            if (speed < -20.0) {
                speed = -20;
            }
        } else {
            //speed = -10;
            speed += 0.8;
            if (speed > 17.0) {
                speed = 17;
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
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);
        if (keyHandler.isSpacePressed()) {
            g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        } else {
            g2.drawImage(runImages[runIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
