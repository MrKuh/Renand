package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.AdditionalHeart;
import at.htlkaindorf.strategy.actions.ScorePlus100;

import java.util.Random;

/**
 * The {@code Rare} class is used for the implementation of a Gift with the Strategy Pattern.<br>
 * The {@code Rare} class is used to spawn a Rare Gift<br>
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.15
 */
public class Rare extends Gift {
    public Rare(GamePanel gamePanel) {
        setGamePanel(gamePanel);

    }

    @Override
    public void setAction(Action action) {
        super.setAction(action);
    }

    /**
     * This function is used to spawn a Gift.<br>
     * It sets the x coordinate to the end of the screen and generates a random y coordinate.
     * <p>
     * It sets a random action.
     */
    @Override
    public void spawn() {
        Random rand = new Random();
        int random = rand.nextInt(1, 3);
        System.out.println(random);
        switch (random) {
            case 1:
                setAction(new ScorePlus100(gamePanel));
                break;
            case 2:
                setAction(new AdditionalHeart((gamePanel)));
                break;
        }
        super.spawn();
    }
}
