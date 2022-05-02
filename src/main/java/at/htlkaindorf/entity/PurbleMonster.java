package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PurbleMonster extends Entity {
    private int flyIMG;
    private BufferedImage[] flyImages;

    public PurbleMonster(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        getImage();
    }

    public void getImage(){
        try {
            flyImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster4.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 20            ;
        speed = 5.0;

        hitBox = new Rectangle();
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.05) + y;
        hitBox.width = (int) Math.round(gamePanel.tileSize * 0.9);
        hitBox.height = (int) Math.round(gamePanel.tileSize * 0.9);

        flyIMG = 1;
    }
    public void update() {

    }

    public void draw(Graphics2D g2) {
            g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.draw(hitBox);

    }


}
