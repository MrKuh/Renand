package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AdditionalHeartAnimation implements Animation {

    public AdditionalHeartAnimation() {
            try {
                images.add(ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster1.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster2.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster3.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster4.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void use() {

    }
}
