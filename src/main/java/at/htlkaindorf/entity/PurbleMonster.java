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
                    ImageIO.read(getClass().getResourceAsStream("/character/obstacle/purbleMonster1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/obstacle/purbleMonster2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/obstacle/purbleMonster3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/character/obstacle/purbleMonster4.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5.0;

        hitBox = new Rectangle();
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.31);
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.19);
        hitBox.width = (int) Math.round(gamePanel.tileSize * 0.44);
        hitBox.height = (int) Math.round(gamePanel.tileSize * 0.81);
    }
    public void update() {

    }

    public void draw(Graphics2D g2) {

    }


}
