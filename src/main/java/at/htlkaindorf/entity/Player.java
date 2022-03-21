package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.display.GamePanel;

public class Player extends Entity{
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    public void setDefaultValue(String value) {
        x = 100;
        y = 100;
        speed = 4;
    }
}
