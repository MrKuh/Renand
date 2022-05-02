package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class Entity {
    protected int x, y;
    protected double speed;

    protected GamePanel gamePanel;

    protected int spriteCounter = 0;

    protected Rectangle hitBox;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
