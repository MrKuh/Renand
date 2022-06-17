package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.NixAnimation;

public class NixAction implements Action {
    @Override
    public Animation use() {
        return new NixAnimation();
    }
}
