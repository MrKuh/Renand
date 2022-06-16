package at.htlkaindorf.strategy;


import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.NixAction;
import at.htlkaindorf.strategy.animations.NixAnimation;

import java.awt.*;

public abstract class Gift {
    protected GamePanel gamePanel = null;
    protected Action action = new NixAction();
    protected Animation animation = new NixAnimation();

    protected boolean spawned = false;

    protected int x, y;

    protected double speed;

    protected int spriteCounter = 0;

    protected Rectangle hitBox;


    public void spawn() {
        spawned = true;
    }
    public void update() {
        if(spawned){

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
        action.activate();
    }

    public void animate(){
        animation.use();
    }
}
