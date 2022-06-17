package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.AdditionalHeart;
import at.htlkaindorf.strategy.actions.ScorePlus100;
import at.htlkaindorf.strategy.actions.ScorePlus50;

import java.util.Random;

public class Rare extends Gift {
    public Rare(GamePanel gamePanel) {
        setGamePanel(gamePanel);

    }
    @Override
    public void setAction(Action action) {
        super.setAction(action);
    }

    @Override
    public void spawn(){
        Random rand = new Random();
        int random = rand.nextInt(3,4);
        System.out.println(random);
        switch (random) {
            case 1:
                setAction(new ScorePlus50(gamePanel));
                break;
            case 2:
                setAction(new ScorePlus100(gamePanel));
                break;
            case 3:
                setAction(new AdditionalHeart(gamePanel));
                break;
        }
        super.spawn();
    }
}
