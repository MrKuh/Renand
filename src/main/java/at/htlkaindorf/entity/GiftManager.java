package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.Common;
import at.htlkaindorf.strategy.Gift;

import java.awt.*;
import java.util.ArrayList;
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

        //beRare = rand.nextBoolean();

    }





    /*public void UpdatePurpleMonster(){

        for (PurpleMonster purpleMonster : common){
            purpleMonster.update();
            if(purpleMonster.getX() <= -100){
                purpleMonster.intiRandom();
                gamePanel.setScore(gamePanel.getScore()+1);
            }
        }

    }

    public void update() {
        UpdatePurpleMonster();

        if(gamePanel.getScore()){

        }
    }*/


}
