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

    protected Rectangle hitBox = new Rectangle();
    public void despawn() {
        spawned = false;
        x = -2000;
        y = -2000;

        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

    }


    public void spawn() {
        spawned = true;
        Random rand = new Random();
        x = gamePanel.getScreenWidth();

        int high = (int) (gamePanel.getScreenHeight() - gamePanel.tileSize  * 1.8);
        //System.out.println("HEight " +gamePanel.getScreenHeight());
        int low = 100;
        y = rand.nextInt(high - low + 1) + low;

        hitBox.width = (int) Math.round(gamePanel.tileSize  * 0.9);
        hitBox.height = (int) Math.round(gamePanel.tileSize  * 0.9);

        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

    }
    public void update() {
        //System.out.println(spawned);
        if(spawned){
            x -= speed;
            //System.out.println(x);
            //System.out.println(y);


        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;
        }
    }

    public void draw(Graphics2D g2){
        if(spawned) {
            //System.out.println("draw");
            collision(g2);
        }
        animation.draw(g2, x, y);

    }

    public void collision(Graphics2D g2){
        if(g2.hit(hitBox, gamePanel.getPlayer().getHitBox(), true)){
            despawn();
            activate();
        }
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
        setAnimation(action.use());
    }

}
