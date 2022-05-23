package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gamePanel;
    private List<PurpleMonster> obstacles;

    private PurpleMonster purpleMonster;

    private int purpleMonsterAmount = 10;


    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.obstacles = new ArrayList<PurpleMonster>();

        for (int i = 0; i < purpleMonsterAmount ; i++) {
            obstacles.add(new PurpleMonster(gamePanel)); //obstacles bekommt ein ba
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
         for (PurpleMonster obstacle : obstacles){
            obstacle.draw(g2);
        }

    }

    public void UpdatePurpleMonster(){
        List<PurpleMonster> delete = new ArrayList<>();

        for (PurpleMonster obstacle : obstacles){
            obstacle.update();
            if(obstacle.getX() <= -100){
                delete.add(obstacle);
                gamePanel.setScore(gamePanel.getScore()+1);
            }
        }

        obstacles.removeAll(delete);

        for (int i = 0; i < delete.size(); i++) {
            obstacles.add(new PurpleMonster(gamePanel));
        }
    }

    public void update() {
        UpdatePurpleMonster();
    }
}
