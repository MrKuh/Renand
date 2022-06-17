package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.collected.AdditionalHeartAnimation;
/**
 * The {@code AdditionalHeart} class is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code AdditionalHeart} class is used to enable the extra Heart of the {@code Player} and<br>
 * to get the {@code AdditionalHeartAnimation}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.40
 */
public class AdditionalHeart implements Action {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
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
