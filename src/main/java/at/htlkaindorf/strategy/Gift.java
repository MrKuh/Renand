package at.htlkaindorf.strategy;


import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.NixAction;
import at.htlkaindorf.strategy.animations.NixAnimation;

import java.awt.*;
import java.util.Random;

/**
 * The {@code Gift} class is used for the implementation of a Gift with the Strategy Pattern.<br>
 * The {@code Gift} class is used for the display of a collectable Gift that start an action<br>
 * on collision with a {@code Player}.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.40
 */
public abstract class Gift {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    protected GamePanel gamePanel = null;
    /**
     * Stores the current action of the {@code Gift}.
     */
    protected Action action = new NixAction();
    /**
     * Stores the current animation of the {@code Gift}.
     */
    protected Animation animation = new NixAnimation();
    /**
     * Defines whether the Gift is spawned and displayed or not.
     */
    protected boolean spawned = false;
    /**
     * These variables are used to identify the current Position in a 2D coordinate system.
     */
    protected int x, y;
    /**
     * This variable is used to define the speed of the Gift
     */
    protected double speed = 5;
    /**
     * This variable is used to define the hitbox of the Gift
     */
    protected Rectangle hitBox = new Rectangle();

    /**
     * This function is used to despawn a Gift when it gets collected and waits to get spawned again.
     */
    public void despawn() {
        spawned = false;
        x = -2000;
        y = -2000;

        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.05) + y;

    }

    /**
     * This function is used to spawn a Gift.<br>
     * It sets the x coordinate to the end of the screen and generates a random y coordinate.
     */
    public void spawn() {
        spawned = true;
        Random rand = new Random();
        x = gamePanel.getScreenWidth();

        int high = (int) (gamePanel.getScreenHeight() - gamePanel.tileSize * 1.8);
        int low = 100;
        y = rand.nextInt(high - low + 1) + low;

        hitBox.width = (int) Math.round(gamePanel.tileSize * 0.9);
        hitBox.height = (int) Math.round(gamePanel.tileSize * 0.9);

        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize * 0.05) + y;
    }

    /**
     * This function is used to move the Gift.<br>
     * It updates the hitbox when the Gift moves.
     */
    public void update() {
        speed = gamePanel.getGameSpeed();
        if (spawned) {
            x -= speed;

            hitBox.x = (int) Math.round(gamePanel.tileSize * 0.05) + x;
            hitBox.y = (int) Math.round(gamePanel.tileSize * 0.05) + y;
        }
    }

    /**
     * This function is used to display the current Animation of the Gift.<br>
     * This function is used to run the collision check.
     */
    public void draw(Graphics2D g2) {
        if (spawned) {
            collision(g2);
        }
        animation.draw(g2, x, y);

    }

    /**
     * This function is used to check if the gift touches the player.<br>
     * This function despawns the gift and starts its current action.
     */
    public void collision(Graphics2D g2) {
        if (g2.hit(hitBox, gamePanel.getPlayer().getHitBox(), true)) {
            despawn();
            activate();
        }
    }


    public void setAction(Action action) {
        this.action = action;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * This function uses the current action.
     */
    public void activate() {
        setAnimation(action.use());
    }

}
