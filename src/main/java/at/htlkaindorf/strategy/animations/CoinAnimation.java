package at.htlkaindorf.strategy.animations;

import at.htlkaindorf.strategy.Animation;

import javax.imageio.ImageIO;
import java.io.IOException;

public class CoinAnimation implements Animation {

    public CoinAnimation() {
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
