package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        getObstacleImage();
    }

    public void getObstacleImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/obstacle/Jetpack_Hindernis-1.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        //ground
        int y = gp.screenHeight - gp.tileSize;
        int x = 0;
        while(x <= gp.screenWidth){
            g2.drawImage(tiles[0].getImage(),x,y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
        }
    }
}
