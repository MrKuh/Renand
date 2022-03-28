package at.htlkaindorf.entity;

import java.awt.image.BufferedImage;

public class Entity {
    protected int x, y;
    protected double speed;
    protected double gravity;

    protected BufferedImage[] runImages;
    protected BufferedImage[] flyImages;
    protected int obstacleIMG;

    protected int spriteCounter = 0;
}
