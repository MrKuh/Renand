package at.htlkaindorf.tile;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class Obstacle {
    private BufferedImage image;
    private boolean collision = true;

}
