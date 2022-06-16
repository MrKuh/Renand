package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;

public class Common extends Gift {
    public Common(GamePanel gamePanel) {
        setGamePanel(gamePanel);
    }

    @Override
    public void setAction(Action action) {
        super.setAction(action);
    }
}
