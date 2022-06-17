package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.collected.AdditionalScoreAnimation;

public class ScorePlus100 implements Action {
    private GamePanel gamePanel;
    private int scoreAmount = 100;
    public ScorePlus100(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public Animation use() {
        gamePanel.setScore(gamePanel.getScore() + scoreAmount);

        int nextSpawn = gamePanel.getGiftManager().getNextSpawn();
        gamePanel.getGiftManager().setNextSpawn(nextSpawn + scoreAmount);

        return new AdditionalScoreAnimation(gamePanel, scoreAmount);

    }
}
