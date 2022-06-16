package at.htlkaindorf.strategy;

import at.htlkaindorf.entity.PurpleMonster;
import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.animations.AdditionalHeartAnimation;

import java.awt.*;
import java.util.Random;

public class GiftManager {
    private GamePanel gamePanel;
    private Gift common;
    private Gift rare;

    private boolean beRare;

    private PurpleMonster purpleMonster;

    private Random rand = new Random();

    public GiftManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.common = new Common(gamePanel);
        this.rare = new Common(gamePanel);



        rare.setAnimation(new AdditionalHeartAnimation(gamePanel));
        rare.spawn();

        //beRare = rand.nextBoolean();

    }

    public void update() {
        System.out.println("update");
        common.update();
        rare.update();
    }
    public void draw(Graphics2D g2) {
        System.out.println("DRAW");
        common.draw(g2);
        rare.draw(g2);
    }



}
