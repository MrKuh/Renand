package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;
    private int x = 0;

    private static final Random rand = new Random();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        getObstacleImage();
    }

    public void getObstacleImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/ground/gras1.png")));
            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/clouds/schiarchWolke.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        //ground
        if(x < -gp.tileSize){
            x += gp.tileSize;
        }
        int i = x;
        while(i <= gp.screenWidth+gp.tileSize){
            //          Image               x       y                            width          height      observer
            g2.drawImage(tiles[0].getImage(),i,gp.screenHeight - gp.tileSize, gp.tileSize, gp.tileSize, null);
            i += gp.tileSize;
        }
        //x -= gp.xspeed;

        //clouds
        i = x;
        while(i <= gp.screenWidth+gp.tileSize){
            //          Image               x       y                            width          height      observer
            g2.drawImage(tiles[1].getImage(),i,rand.nextInt((gp.screenHeight - gp.tileSize)/2), gp.tileSize, gp.tileSize, null);
            i += gp.tileSize;
        }
        x -= gp.xspeed;
    }
}
