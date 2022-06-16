package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnimation implements Animation {


    private GamePanel gamePanel;
    private int spriteCounter = 0;
    private int image = 0;
    private List<BufferedImage> images = new ArrayList<BufferedImage>();

    public QuestionAnimation(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/collectable_item.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2 , int x, int y) {
        //animation
        spriteCounter++;
        if (spriteCounter > gamePanel.FPS / images.size()/ 1.5) {
            if (image < images.size() - 1) {
                image++;
            } else {
                image = 0;
            }
            spriteCounter = 0;
        }
        g2.drawImage(images.get(image), x, y, gamePanel.tileSize , gamePanel.tileSize , null);
    }
}
