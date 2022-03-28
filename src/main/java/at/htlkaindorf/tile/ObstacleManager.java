package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObstacleManager {
    private GamePanel gp;
    private Obstacle[] obstacles;

    public ObstacleManager(GamePanel gp) {
        this.gp = gp;

        obstacles = new Obstacle[10];

        getObstacleImage();
    }

    public void getObstacleImage(){
        try{
            obstacles[0] = new Obstacle();
            obstacles[0].setImage(ImageIO.read(getClass().getResourceAsStream("")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
