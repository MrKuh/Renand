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
            obstacleImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/Jetpack_Hindernis-1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/Jetpack_Hindernis-2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/Jetpack_Hindernis-3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/Jetpack_Hindernis-4.png"))
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
        spriteCounter++;
        if (spriteCounter > gamePanel.FPS / obstacleImages.length) {
            if (obstacleIMG < obstacleImages.length-1) {
                obstacleIMG++;
            } else {
                obstacleIMG = 0;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);

        g2.drawImage(obstacleImages[obstacleIMG], x, y, gamePanel.titleSize, gamePanel.titleSize, null);
    }
}
