package at.htlkaindorf.entity;

import at.htlkaindorf.game.GamePanel;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;



@Data
public class PurpleMonster extends Entity {
    private int flyIMG;

    private Random rand = new Random();
    private BufferedImage[] flyImages;
    private boolean up = false;

    public PurpleMonster(GamePanel gamePanel) {
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
    public void tunnel(Random rand){
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

        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

    }
    public void intiTunnel(Random rand) {

        if(rand.nextBoolean()){
            y = 0;
        }else{
            y = gamePanel.getScreenHeight() -  gamePanel.tileSize;
        }
        x = gamePanel.getScreenWidth() + rand.nextInt(gamePanel.getScreenWidth());
    }
    public void intiRandom(){
        x = gamePanel.getScreenWidth() + rand.nextInt(gamePanel.getScreenWidth());

        int high = (int) (gamePanel.getScreenHeight() - gamePanel.tileSize  * 1.8);
        int low = 100;
        y = rand.nextInt(high - low + 1) + low;

        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

    }
    public void random(){

        x -= speed;
        y -= rand.nextInt(5 - (-5) + 1) + (-5);
    }
    public void randomUpDown(){
        x -= speed;
        if(y < 0){
            up = true;
        }
        if(y > gamePanel.getScreenHeight()- gamePanel.tileSize * 1.8 ){
            up = false;
        }

        if(y >= 0 && !up){
            y -= 2;
        }
        if(y <= gamePanel.getScreenHeight()- gamePanel.tileSize * 1.8  && up){
            y += 2;
        }
        hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;
    }


    public void setDefaultValues() {
        //Tunnel
        //intiTunnel(rand);

        hitBox = new Rectangle();
        hitBox.width = (int) Math.round(gamePanel.tileSize  * 0.9);
        hitBox.height = (int) Math.round(gamePanel.tileSize  * 0.9);
        hitBox.x = (int) Math.round(gamePanel.tileSize * 0.05) + x;
        hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;
        //Random
        intiRandom();



        flyIMG = 0;
        up = rand.nextBoolean();
    }
    public void update() {
        speed = gamePanel.getGameSpeed() *2;
        //tunnel(rand);
        randomUpDown();
        //Movement
        /*


        x -= speed;
        if(y < 0){
            up = true;
        }
        if(y > gamePanel.getScreenHeight()- gamePanel.tileSize * 1.8 ){
            up = false;
        }

        if(y >= 0 && !up){
            y -= 2;
        }
        if(y <= gamePanel.getScreenHeight()- gamePanel.tileSize * 1.8  && up){
            y += 2;
        }

         */

        //hitBox.x = (int) Math.round(gamePanel.tileSize  * 0.05) + x;
        //hitBox.y = (int) Math.round(gamePanel.tileSize  * 0.05) + y;

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
            gamePanel.getObstacleManager().checkPurpleMonsterCollision(this, g2);
            g2.drawImage(flyImages[flyIMG], x, y, gamePanel.tileSize , gamePanel.tileSize , null);
            //g2.draw(hitBox);
    }

    public void changeDirection(Rectangle hit) {
        if(hit.y < hitBox.y){
            up = true;
        }else{
            up = false;
        }
    }
}
