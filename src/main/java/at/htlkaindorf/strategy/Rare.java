package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;

public class Rare extends Gift {
    public Rare(GamePanel gamePanel) {
        setGamePanel(gamePanel);


    }

    @Override
    public void setAction(Action action) {
        super.setAction(action);
    }
}
