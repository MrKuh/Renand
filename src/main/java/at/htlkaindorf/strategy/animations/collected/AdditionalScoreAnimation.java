package at.htlkaindorf.strategy.animations.collected;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdditionalScoreAnimation implements Animation {

    private GamePanel gamePanel;
    private int spriteCounter = 0;
    private int image = 0;
    private int loops = 3;
    private int scoreAmount;
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
