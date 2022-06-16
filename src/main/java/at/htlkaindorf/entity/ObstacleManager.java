package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {

    private GamePanel gamePanel;
    private List<PurpleMonster> purpleMonsters;

    private PurpleMonster purpleMonster;

    private int purpleMonsterAmount = 4;
    private int added = 0;

    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.purpleMonsters = new ArrayList<PurpleMonster>();

        for (int i = 0; i < purpleMonsterAmount ; i++) {
            purpleMonsters.add(new PurpleMonster(gamePanel));
        }

    }

    public boolean checkCollision(Player player, Graphics2D g2){
        boolean hit = false;
        for (Entity obstacle : purpleMonsters){
            if(g2.hit(player.getHitBox(), obstacle.getHitBox(), true)){
                hit = true;
                gamePanel.collision();
                break;
            }
        }
        return hit;
    }
    public boolean checkPurpleMonsterCollision(PurpleMonster purpleMonster, Graphics2D g2){
        boolean hit = false;
        for (Entity obstacle : purpleMonsters){
            if(obstacle != purpleMonster){
                if(g2.hit(purpleMonster.getHitBox(), obstacle.getHitBox(), true)){
                    hit = true;
                    purpleMonster.changeDirection(obstacle.getHitBox());
                    break;
                }
            }
        }
        return hit;
    }

    public void draw(Graphics2D g2) {
         for (PurpleMonster obstacle : purpleMonsters){
            obstacle.draw(g2);
        }

    }

    public void UpdatePurpleMonster(){
        for (PurpleMonster purpleMonster : purpleMonsters){
            purpleMonster.update();
            if(purpleMonster.getX() <= -100){
                purpleMonster.intiRandom();
                gamePanel.setScore(gamePanel.getScore()+1);
            }
        }


        //Replace
        /*
        List<PurpleMonster> delete = new ArrayList<>();
        purpleMonsters.removeAll(delete);
        for (int i = 0; i < delete.size(); i++) {
            purpleMonsters.add(new PurpleMonster(gamePanel));
        }
         */

    }
    private void updatePurpleMonsterAmount() {
        if(gamePanel.getScore() / 10 - added > 1 && purpleMonsters.size() < 15){
            System.out.println("ADD");
            added++;
            purpleMonsters.add(new PurpleMonster(gamePanel));
        }
    }

    public void update() {
        updatePurpleMonsterAmount();
        UpdatePurpleMonster();
    }


}
