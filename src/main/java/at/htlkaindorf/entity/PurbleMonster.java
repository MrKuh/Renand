package at.htlkaindorf.entity;

import at.htlkaindorf.controller.KeyHandler;
import at.htlkaindorf.game.GamePanel;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static at.htlkaindorf.game.GamePanel.tileSize;

@Data
public class PurbleMonster extends Entity {
    private int flyIMG;
    private BufferedImage[] flyImages;
    private boolean up = false;

    public PurbleMonster(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues();
        getImage();
    }

    public void getImage(){
        try {
            flyImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/obstacle/purbleMonster4.png")),
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void tunnel(){
        Random rand = new Random();
        Player player = gamePanel.getPlayer();

        x -= speed;
        //y -= rand.nextInt(5 - (-5) + 1) + (-5);

        System.out.println(x);
        System.out.println(y);
        //x -= speed + rand.nextInt((int) speed);

        if(x < 500) {
            if (player.getY() >= y) {
                y += rand.nextInt((int) speed);
            } else {
                y -= rand.nextInt((int) speed);
            }
        }

        hitBox.x = (int) Math.round(tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(tileSize * 0.05) + y;

    }
    public void random(){
        Random rand = new Random();
        x -= speed;
        y -= rand.nextInt(5 - (-5) + 1) + (-5);
    }


    public void setDefaultValues() {
        Random rand = new Random();

        //Tunnel
        /*
        if(rand.nextBoolean()){
             y = 0;
        }else{
            y = gamePanel.getScreenHeight() -  gamePanel.tileSize;
        }
         */

        //Random
        x = gamePanel.getScreenWidth() + rand.nextInt(gamePanel.getScreenWidth());

        int high = (int) (gamePanel.getScreenHeight() - tileSize * 1.8);
        int low = 100;
        y = rand.nextInt(high - low + 1) + low;

        speed = 10.0;

        hitBox = new Rectangle();
        hitBox.x = (int) Math.round(tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(tileSize * 0.05) + y;
        hitBox.width = (int) Math.round(tileSize * 0.9);
        hitBox.height = (int) Math.round(tileSize * 0.9);

        flyIMG = 0;
        up = rand.nextBoolean();
    }
    public void update() {
        //Movement
        Random rand = new Random();

        x -= speed;
        if(y < gamePanel.tileSize){
            up = true;
        }
        if(y > gamePanel.getScreenHeight()){
            up = false;
        }

        if(y >= gamePanel.tileSize && !up){
            y -= 2;
        }
        if(y <= gamePanel.getScreenHeight() && up){
            y += 2;
        }

        hitBox.x = (int) Math.round(tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(tileSize * 0.05) + y;

        //animation
        spriteCounter++;
        if (spriteCounter > gamePanel.FPS / flyImages.length/ 1.5) {
            if (flyIMG < flyImages.length - 1) {
                flyIMG++;
            } else {
                flyIMG = 0;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {
            g2.drawImage(flyImages[flyIMG], x, y, tileSize, tileSize, null);
            //g2.draw(hitBox);
    }
}
