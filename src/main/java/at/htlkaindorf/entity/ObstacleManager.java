package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class {@code ObstacleManager} is used to manage everything of the purple monsters.<br>
 * It recognises when the {@code Player} hits a monster, draws the monsters and updates the monsters.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.21
 */
public class ObstacleManager {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    private GamePanel gamePanel;
    /**
     * This is a {@code List} of {@code PurpleMonster}s and it contains all enemies.
     */
    private List<PurpleMonster> purpleMonsters;
    /**
     * This instance defines the amount of {@code PurpleMonster}s.
     */
    private int purpleMonsterAmount = 4;
    /**
     * This variable contains the amount of {@code PurpleMonster}s <br>
     * that were added after time to the {@code purpleMonsterAmount}.
     */
    private int added = 0;


    public ObstacleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.purpleMonsters = new ArrayList<PurpleMonster>();

        for (int i = 0; i < purpleMonsterAmount; i++) {
            purpleMonsters.add(new PurpleMonster(gamePanel));
        }

    }

    /**
     * This function checks if a {@code PurpleMonster} has hit the {@code Player}.
     *
     * @param player - the {@code Player} object to check if it is hit
     * @param g2     - the {@code Graphics2D} object
     * @return boolean hit -> if it is hit or not
     */
    public boolean checkCollision(Player player, Graphics2D g2) {
        boolean hit = false;
        for (PurpleMonster purpleMonster : purpleMonsters) {
            if (g2.hit(player.getHitBox(), purpleMonster.getHitBox(), true)) {
                if (player.isAdditionalHeart()) {
                    purpleMonster.intiRandom();
                    player.setAdditionalHeart(false);
                    break;
                } else {
                    hit = true;
                    gamePanel.collision();
                    break;
                }

            }
        }
        return hit;
    }

    /**
     * This function checks if two {@code PurpleMonster}s hit and changes the directory of one of them, <br>
     * so they cannot cross each other.
     *
     * @param purpleMonster - one specific {@code PurpleMonster} to check if it is hit
     * @param g2            - the {@code Graphics2D} object
     * @return boolean hit -> if two hit each other or not
     */
    public boolean checkPurpleMonsterCollision(PurpleMonster purpleMonster, Graphics2D g2) {
        boolean hit = false;
        for (Entity obstacle : purpleMonsters) {
            if (obstacle != purpleMonster) {
                if (g2.hit(purpleMonster.getHitBox(), obstacle.getHitBox(), true)) {
                    hit = true;
                    purpleMonster.changeDirection(obstacle.getHitBox());
                    break;
                }
            }
        }
        return hit;
    }

    /**
     * This function draws the {@code PurpleMonster}s.
     *
     * @param g2 - the {@code Graphics2D} object
     */
    public void draw(Graphics2D g2) {
        for (PurpleMonster obstacle : purpleMonsters) {
            obstacle.draw(g2);
        }

    }

    /**
     * This function updates the {@code PurpleMonster}s by calling the {@code update()} function of {@code PurpleMonster} <br>
     * and adds something to the score of {@code GamePanel} if a {@code PurpleMonster} moves out of the field of view.
     */
    public void updatePurpleMonster() {
        for (PurpleMonster purpleMonster : purpleMonsters) {
            purpleMonster.update();
            if (purpleMonster.getX() <= -100) {
                purpleMonster.intiRandom();
                gamePanel.setScore(gamePanel.getScore() + 1);
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

    /**
     * This function adds new {@code PurpleMonster}s, so it becomes more difficult.
     */
    private void updatePurpleMonsterAmount() {
        if (gamePanel.getScore() / 100 - added > 1 && purpleMonsters.size() < 10) {
            System.out.println("ADD");
            added++;
            purpleMonsters.add(new PurpleMonster(gamePanel));
        }
    }

    /**
     * This function just calls {code updatePurpleMonsterAmount} and {@code updatePurpleMonster}.
     */
    public void update() {
        updatePurpleMonsterAmount();
        updatePurpleMonster();
    }


}
