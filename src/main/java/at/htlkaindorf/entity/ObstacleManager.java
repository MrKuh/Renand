package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gamePanel;
    private List<PurbleMonster> obstacles;

    private PurbleMonster purbleMonster;

    private int purbleMonsterAmount = 10;

    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.obstacles = new ArrayList<PurbleMonster>();

        for (int i = 0; i < 10 ; i++) {
            obstacles.add(new PurbleMonster(gamePanel)); //obstacles bekommt ein ba
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

    public void UpdatePurbleMonster(){
        List<PurbleMonster> delete = new ArrayList<>();

        for (PurbleMonster obstacle : obstacles){
            obstacle.update();
            if(obstacle.getX() <= -100){
                delete.add(obstacle);
            }
        }

        obstacles.removeAll(delete);

        for (int i = 0; i < delete.size(); i++) {
            obstacles.add(new PurbleMonster(gamePanel));
        }
    }

    public void update() {
        UpdatePurbleMonster();
    }
}
