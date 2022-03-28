package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;
    private int y = gp.screenHeight - gp.tileSize;
    private int x = 0;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        getObstacleImage();
    }

    public void getObstacleImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/ground/gras1.png")));
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
            g2.drawImage(tiles[0].getImage(),i,y, gp.tileSize, gp.tileSize, null);
            i += gp.tileSize;
        }
        x -= 5;
    }
}
