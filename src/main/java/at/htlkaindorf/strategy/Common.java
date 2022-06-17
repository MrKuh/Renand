package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.actions.ScorePlus25;
import at.htlkaindorf.strategy.actions.ScorePlus50;

import java.util.Random;

public class Common extends Gift {
    public Common(GamePanel gamePanel) {
        setGamePanel(gamePanel);
    }

    @Override
    public void setAction(Action action) {
        super.setAction(action);
    }
    @Override
    public void spawn(){
        Random rand = new Random();
        int random = rand.nextInt(1,2);
        System.out.println(random);
        switch (random) {
            case 1:
                setAction(new ScorePlus25(gamePanel));
                break;
            case 2:
                setAction(new ScorePlus50(gamePanel));
                break;
        }
        super.spawn();
    }
}
