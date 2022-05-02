package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gamePanel;
    private List<PurbleMonster> obstacles;

    private PurbleMonster purbleMonster;

    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.obstacles = new ArrayList<PurbleMonster>();

        for (int i = 0; i < 10 ; i++) {
            obstacles.add(new PurbleMonster(gamePanel));//obstacles bekommt ein ba
        }

    }

    public boolean checkCollision(Player player, Graphics2D collisionChecker){
        boolean hit = false;
        for (Entity obstacle : obstacles){
            if(collisionChecker.hit(player.getHitBox(), obstacle.getHitBox(), true)){
                hit = true;
                gamePanel.resetTheGame();
                break;
            }
        }
        return hit;
    }


    public void draw(Graphics2D g2) {
        for (PurbleMonster obstacle : obstacles){
            obstacle.draw(g2);
        }


    }

    public void update() {
        for (PurbleMonster obstacle : obstacles){
            obstacle.update();
            if(obstacle.getX() <= -100){
                obstacles.remove(obstacle);
                obstacles.add(new PurbleMonster(gamePanel));
            }


        }
    }
}
