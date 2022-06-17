package at.htlkaindorf.strategy;

import at.htlkaindorf.entity.PurpleMonster;
import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.animations.QuestionAnimationCommon;
import at.htlkaindorf.strategy.animations.QuestionAnimationRare;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Random;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GiftManager {
    private GamePanel gamePanel;
    private Gift common;
    private Gift rare;

    private int nextSpawn;

    private PurpleMonster purpleMonster;

    private Random rand = new Random();

    public GiftManager(GamePanel gamePanel) {
        nextSpawn = rand.nextInt(5,15);

        this.gamePanel = gamePanel;

        this.common = new Common(gamePanel);
        this.rare = new Rare(gamePanel);

        //beRare = rand.nextBoolean();

    }

    public void update() {
        //System.out.println(nextSpawn);
        if(gamePanel.getScore() >= nextSpawn){
            //System.out.println(nextSpawn);
            nextSpawn += rand.nextInt(20,51);
            //System.out.println(nextSpawn);

            int random = rand.nextInt(21);
            //System.out.println(random);

            if(random > 15){
                rare.setAnimation(new QuestionAnimationRare(gamePanel));
                rare.spawn();
            }else{
                common.setAnimation(new QuestionAnimationCommon(gamePanel));
                common.spawn();
            }
        }

        rare.update();
        common.update();
    }
    public void draw(Graphics2D g2) {
        common.draw(g2);
        rare.draw(g2);
    }



}
