package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;

/**
 * This {@code Entity} class is used to give {@code PurpleMonster} it's required instances through inheritance.
 * @author MrKuh
 * @author Bensi
 * @version 1.1
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Entity {
    /**
     * These variables are used to identify the current Position in a 2D coordinate system
     */
    protected int x, y;
    /**
     * This variable defines the speed of an {@code Entity} object.<br>
     * This speed decreases the x constant so the {@code Entity} will fly to the {@code Player}.
     */
    protected double speed;
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    protected GamePanel gamePanel;
    /**
     * This variable is used to control the animation speed of an {@code Entity}.
     */
    protected int spriteCounter = 0;
    /**
     * This {@code Rectangle} object is used to define the HitBox of the {@code Entity}
     */
    protected Rectangle hitBox;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
