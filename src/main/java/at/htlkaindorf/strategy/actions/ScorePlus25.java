package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.collected.AdditionalScoreAnimation;

import java.util.Random;

/**
 * The {@code ScorePlus25} class is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code ScorePlus25} class is used to increase the Score by 25 and to correct the nextSpawn variable of {@code GiftManager}.<br>
 * The {@code ScorePlus25} class is used to create and return a {@code AdditionalScoreAnimation}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.15
 */
public class ScorePlus25 implements Action {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    private GamePanel gamePanel;
    /**
     * This variable defines the amount of score that's going to be added to the score of the {@code GamePanel}.
     */
    private int scoreAmount = 25;

    public ScorePlus25(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public Animation use() {
        gamePanel.setScore(gamePanel.getScore() + scoreAmount);

        int nextSpawn = gamePanel.getGiftManager().getNextSpawn();
        if (nextSpawn <= gamePanel.getScore()) {
            Random rand = new Random();
            nextSpawn = gamePanel.getScore() + rand.nextInt(15, 25);
            gamePanel.getGiftManager().setNextSpawn(nextSpawn);
        }

        return new AdditionalScoreAnimation(gamePanel, scoreAmount);

    }

}
