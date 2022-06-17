package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.collected.AdditionalScoreAnimation;

import java.util.Random;

public class ScorePlus25 implements Action {
    private GamePanel gamePanel;
    private int scoreAmount = 25;
    public ScorePlus25(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public Animation use() {
        gamePanel.setScore(gamePanel.getScore() + scoreAmount);

        int nextSpawn = gamePanel.getGiftManager().getNextSpawn();
        if(nextSpawn <= gamePanel.getScore()){
            Random rand = new Random();
            nextSpawn = gamePanel.getScore() + rand.nextInt(15,25);
            gamePanel.getGiftManager().setNextSpawn(nextSpawn);
        }
        System.out.println(nextSpawn);

        return new AdditionalScoreAnimation(gamePanel, scoreAmount);

    }

}
