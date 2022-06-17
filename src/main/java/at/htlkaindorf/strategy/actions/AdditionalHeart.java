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
        if(gamePanel.getPlayer().isAdditionalHeart()){
            return new ScorePlus100(gamePanel).use();
        }else{
            gamePanel.getPlayer().setAdditionalHeart(true);
            return new AdditionalHeartAnimation(gamePanel);
        }
    }

}
