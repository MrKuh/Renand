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
        gravity = 3.0;
        obstacleIMG = 0;
    }

    public void getPlayerImage() {
        try {
            runImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_0.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/run/sprite_3.png"))
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
        if (keyHandler.isSpacePressed()) {
            y -= speed;
            gravity = 3;
            speed *= 1.05;
        } else {
            y += gravity;
            gravity *= 1.05;
            speed = 5;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > gamePanel.screenHeight - gamePanel.titleSize) {
            y = gamePanel.screenHeight - gamePanel.titleSize;
        }

        //obstacle img set
        if (keyHandler.isSpacePressed()) {
            //Fly
            spriteCounter++;
            if (spriteCounter > gamePanel.FPS / flyImages.length/4) {
                if (obstacleIMG < flyImages.length - 1) {
                    obstacleIMG++;
                } else {
                    obstacleIMG = 6;
                }
                spriteCounter = 0;
            }
        }else{
            //Run
            spriteCounter++;
            if (spriteCounter > gamePanel.FPS / runImages.length /4) {
                if (obstacleIMG < runImages.length - 1) {
                    obstacleIMG++;
                } else {
                    obstacleIMG = 0;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);
        if (keyHandler.isSpacePressed()) {
            g2.drawImage(flyImages[obstacleIMG], x, y, gamePanel.titleSize, gamePanel.titleSize, null);
        }else{
            g2.drawImage(runImages[obstacleIMG], x, y, gamePanel.titleSize, gamePanel.titleSize, null);
        }
    }
}
