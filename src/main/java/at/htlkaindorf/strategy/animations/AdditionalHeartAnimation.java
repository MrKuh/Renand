package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AdditionalHeartAnimation implements Animation {

    public AdditionalHeartAnimation() {
            try {
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart0.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart1.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart2.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart3.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart4.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart5.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart6.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart7.png")));
                images.add(ImageIO.read(getClass().getResourceAsStream("/additional_heart/additional_heart8.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void use() {

    }
}
