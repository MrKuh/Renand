package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.display.GamePanel;
import lombok.Data;

import java.awt.*;

@Data
public class Player extends Entity{
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    private int playerGravity;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if(keyHandler.isSpacePressed()){
            y -= speed;
            playerGravity = 3;
            speed *= 1.05;
        }else{
            y += playerGravity;
            playerGravity *= 1.05;
            speed = 5;
        }
        if(y < 0){
            y = 0;
        }
        if(y > gamePanel.screenHeight - gamePanel.titleSize){
            y = gamePanel.screenHeight - gamePanel.titleSize;
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);
    }
}
