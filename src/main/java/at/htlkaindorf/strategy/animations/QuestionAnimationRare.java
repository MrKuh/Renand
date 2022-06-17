package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnimationRare implements Animation {
    /**
     * This {@code Rectangle} object is used to define the HitBox of the {@code Entity}
     */
    private GamePanel gamePanel;
    /**
     * This variable is used to control the animation speed.
     */
    private int spriteCounter = 0;

    /**
     * This variable defines the index currently displayed image.
     */
    private int image = 0;
    /**
     * This variable contains all images of the animation
     */
    private List<BufferedImage> images = new ArrayList<BufferedImage>();

    public QuestionAnimationRare(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/questionBlock/rare/rareQuestionBlock0.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/questionBlock/rare/rareQuestionBlock1.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/questionBlock/rare/rareQuestionBlock2.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/questionBlock/rare/rareQuestionBlock3.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/questionBlock/rare/rareQuestionBlock4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        //animation
        spriteCounter++;
        if (spriteCounter > gamePanel.FPS / images.size() / 1.5) {
            if (image < images.size() - 1) {
                image++;
            } else {
                image = 0;
            }
            spriteCounter = 0;
        }
        g2.drawImage(images.get(image), x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
