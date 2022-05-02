package at.htlkaindorf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
@Data
@AllArgsConstructor
public class Entity {
    protected int x, y;
    protected double speed;

    protected BufferedImage[] runImages;
    protected BufferedImage[] flyImages;
    protected int runIMG;
    protected int flyIMG;

    protected int spriteCounter = 0;

    protected Rectangle hitBox;
    protected boolean collision;
}
