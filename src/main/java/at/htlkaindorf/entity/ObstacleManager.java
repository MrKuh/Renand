package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gamePanel;
    private List<Entity> obstacles;

    private PurbleMonster purbleMonster;

    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.obstacles = new ArrayList<Entity>();
        purbleMonster = new PurbleMonster(gamePanel);
        obstacles.add(purbleMonster);
    }

    public void addObstacle(Entity obstacle) {
        obstacles.add(obstacle);
    }

    public void checkCollision(Player player, Graphics2D collisionChecker){
        for (Entity obstacle : obstacles){
            if(collisionChecker.hit(player.getHitBox(), obstacle.getHitBox(), true)){
                gamePanel.resetTheGame();
                break;
            }

        }
    }


    public void draw(Graphics2D g2) {
        purbleMonster.draw(g2);

    }
}
