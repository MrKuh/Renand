package at.htlkaindorf.strategy;

import java.awt.*;

/**
 * The {@code Animation} interface is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code Animation} interface contains Methods the need to be in an animation {@code Animation} class.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.10
 */
public interface Animation {
    /**
     * This function used to draw a frame of the animation.<br>
     */
    public void draw(Graphics2D g2, int x, int y);

}
