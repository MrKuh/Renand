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
 * The {@code AdditionalScoreAnimation} class is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code AdditionalScoreAnimation} class is used for the animation of the additional Score action of a {@code Gift}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.40
 */
public class AdditionalScoreAnimation implements Animation {
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
    private int loops = 3;
    /**
     * This stores the amount that should be added to the score
     */
    private int scoreAmount;
    /**
     * This variable contains all images of the animation
     */
    private List<BufferedImage> images = new ArrayList<BufferedImage>();

    public AdditionalScoreAnimation(GamePanel gamePanel, int scoreAmount) {
        this.scoreAmount = scoreAmount;
        this.gamePanel = gamePanel;
        try {
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/star0.png")));
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/star1.png")));
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

            String text = "+ " + scoreAmount;
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            g2.drawString(text
                    , gamePanel.getScreenWidth() / 2 - metrics.stringWidth(text) / 2
                    , gamePanel.getScreenHeight() / 2 + gamePanel.tileSize);

        }
    }
}
