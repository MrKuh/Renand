package at.htlkaindorf.strategy;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.strategy.animations.QuestionAnimationCommon;
import at.htlkaindorf.strategy.animations.QuestionAnimationRare;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Random;
/**
 * This class {@code ObstacleManager} is used to manage the Gifts.<br>
 * It spawns, updates and draws a rare and a common gift.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.21
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GiftManager {
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    private GamePanel gamePanel;
    /**
     * Object of {@code Gift}. <br>
     * Used to store the common gift used by the {@code GiftManager}.
     */
    private Gift common;
    /**
     * Object of {@code Gift}. <br>
     * Used to store the rare gift used by the {@code GiftManager}.
     */
    private Gift rare;
    /**
     * Used to define the next amount of score that is needed to spawn another gift
     */
    private int nextSpawn;
    /**
     * Object of {@code Random} to access function of {@code Random}. <br>
     */
    private Random rand = new Random();

    public GiftManager(GamePanel gamePanel) {
        nextSpawn = rand.nextInt(5, 15);

        this.gamePanel = gamePanel;

        this.common = new Common(gamePanel);
        this.rare = new Rare(gamePanel);

        //beRare = rand.nextBoolean();

    }

    /**
     * This function is used to call the update function of the rare and common Gift.<br>
     * It spawns the rare or the common Gift if the score reaches the nextSpawn.
     */
    public void update() {
        //System.out.println(nextSpawn);
        if (gamePanel.getScore() >= nextSpawn) {
            //System.out.println(nextSpawn);
            nextSpawn += rand.nextInt(20, 51);
            //System.out.println(nextSpawn);

            int random = rand.nextInt(21);
            //System.out.println(random);

            if (random > 15) {
                rare.setAnimation(new QuestionAnimationRare(gamePanel));
                rare.spawn();
            } else {
                common.setAnimation(new QuestionAnimationCommon(gamePanel));
                common.spawn();
            }
        }

        rare.update();
        common.update();
    }

    /**
     * This function is used to call the draw function of the rare and common Gift.<br>
     */
    public void draw(Graphics2D g2) {
        common.draw(g2);
        rare.draw(g2);
    }


}
