package at.htlkaindorf.strategy.animations.collected;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code AdditionalHeartAnimation} class is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code AdditionalHeartAnimation} class is used for the animation of the additional Heart action of a {@code Gift}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.40
 */
public class AdditionalHeartAnimation implements Animation {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
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
     * This variable defines the number of loops the animation should be displayed
     */
    private int loops = 2;
    /**
     * This variable contains all images of the animation
     */
    private List<BufferedImage> images = new ArrayList<BufferedImage>();

    public AdditionalHeartAnimation(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart0.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart1.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart2.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart3.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart4.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart5.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart6.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart7.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/additional_heart/aditional_heart8.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        if (loops > 0) {
            //animation
            spriteCounter++;
            if (spriteCounter > gamePanel.FPS / images.size() / 1.5) {
                if (image < images.size() - 1) {
                    image++;
                } else {
                    image = 0;
                    loops--;
                }
                spriteCounter = 0;
            }
            g2.drawImage(images.get(image)
                    , gamePanel.getScreenWidth() / 2 - gamePanel.tileSize / 2
                    , gamePanel.getScreenHeight() / 2 - gamePanel.tileSize / 2
                    , gamePanel.tileSize
                    , gamePanel.tileSize
                    , null);
        }
    }


}
