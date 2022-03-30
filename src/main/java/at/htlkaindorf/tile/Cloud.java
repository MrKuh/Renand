package at.htlkaindorf.tile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cloud {
    private BufferedImage image;
    private double height; //relative height of cloud to full height
    private double xPosition;
    private boolean collision = false;
}
