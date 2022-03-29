package at.htlkaindorf.entity;

import java.awt.image.BufferedImage;

public class Entity {
    protected int x, y;
    protected double speed;

    protected BufferedImage[] runImages;
    protected BufferedImage[] flyImages;
    protected int runIMG;
    protected int flyIMG;

    protected int spriteCounter = 0;
}
