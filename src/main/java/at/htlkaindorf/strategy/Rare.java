package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.AdditionalHeart;
import at.htlkaindorf.strategy.actions.ScorePlus100;

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
        int random = rand.nextInt(1,3);
        System.out.println(random);
        switch (random) {
            case 1:
                setAction(new ScorePlus100(gamePanel));
                break;
            case 2:
                setAction(new AdditoinalHeart(gamePanel));
                break;
        }
        super.spawn();
    }
}
