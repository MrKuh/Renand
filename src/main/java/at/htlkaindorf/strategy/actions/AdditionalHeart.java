package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.collected.AdditionalHeartAnimation;

public class AdditionalHeart implements Action {
    private GamePanel gamePanel;
    public AdditionalHeart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public Animation use() {
        gamePanel.getPlayer().setAdditionalHeart(true);
        return new AdditionalHeartAnimation(gamePanel);
    }

}
