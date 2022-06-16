package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class QuestionAnimation implements Animation {

    public QuestionAnimation() {
        try {
            images.add(ImageIO.read(getClass().getResourceAsStream("/collectable/collectable_item.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void use() {

    }
}
