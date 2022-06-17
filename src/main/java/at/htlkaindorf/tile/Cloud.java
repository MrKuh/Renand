package at.htlkaindorf.tile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * The {@code Cloud} class is the beans class for the clouds in the Background<br>
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cloud {
    /**
     * This variable contains all images of the animation
     */
    private BufferedImage image;
    /**
     * This variable defines the height of cloud to full height
     */
    private double height;
    /**
     * This variable defines the xPosition
     */
    private double xPosition;
    /**
     * is used to define if it has collision or not
     */
    private boolean collision = false;
}
