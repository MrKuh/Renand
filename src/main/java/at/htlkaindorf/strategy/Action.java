package at.htlkaindorf.strategy;

/**
 * The {@code Action} interface is used for the implementation of the {@code Gift} with the Strategy Pattern.<br>
 * The {@code Action} interface contains Methods the need to be in an action {@code Action} class.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.10
 */
public interface Action {
    /**
     * This function contains everything the action does.<br>
     */
    public Animation use();
}
