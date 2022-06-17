package at.htlkaindorf.strategy.actions;

import at.htlkaindorf.strategy.Action;
import at.htlkaindorf.strategy.Animation;
import at.htlkaindorf.strategy.animations.NixAnimation;

/**
 * The {@code NixAction} class is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code NixAction} class is the default action of the {@code Gift}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.10
 */
public class NixAction implements Action {
    @Override
    public Animation use() {
        return new NixAnimation();
    }
}
