package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.ScorePlus25;
import at.htlkaindorf.strategy.actions.ScorePlus50;

import java.util.Random;

/**
 * The {@code Common} class is used for the implementation of a Gift with the Strategy Pattern.<br>
 * The {@code Common} class is used to spawn a Common Gift<br>
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.15
 */
public class Common extends Gift {
    public Common(GamePanel gamePanel) {
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
                setAction(new ScorePlus25(gamePanel));
                break;
            case 2:
                setAction(new ScorePlus50(gamePanel));
                break;
        }
        super.spawn();
    }
}
