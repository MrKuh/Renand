package at.htlkaindorf.strategy;


import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.NixAction;
import at.htlkaindorf.strategy.animations.NixAnimation;

import java.awt.*;
import java.util.Random;

public abstract class Gift {
    protected GamePanel gamePanel = null;
    protected Action action = new NixAction();
    protected Animation animation = new NixAnimation();

    protected boolean spawned = false;

    protected int x, y;

    protected double speed = 5;

    protected int spriteCounter = 0;

    protected Rectangle hitBox;


    public void spawn() {
        spawned = true;
        Random rand = new Random();
        x = gamePanel.getScreenWidth() + rand.nextInt(gamePanel.getScreenWidth());

        int high = (int) (gamePanel.getScreenHeight() - gamePanel.tileSize  * 1.8);
        int low = 100;
        y = rand.nextInt(high - low + 1) + low;

        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

    }
    public void update() {
        if(spawned){
            x -= speed;
        }
    }

    public void draw(Graphics2D g2){
        //animation.draw(g2);
    }


    public void setAction(Action action) {
        this.action = action;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void activate(){
        action.activate();
    }

    public void animate(){
        animation.use();
    }
}
