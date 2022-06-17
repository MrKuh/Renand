package at.htlkaindorf.tile;

import lombok.Data;

import java.awt.image.BufferedImage;
/**
 * The {@code Tile} class is the beans class for the tiles of the ground<br>
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.10
 */
@Data
public class Tile {
    /**
     * This variable contains all images of the animation
     */
    private BufferedImage image;
    /**
     * is used to define if it has collision or not
     */
    private boolean collision = false;

}
