package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TileManager {

    private static final Random rand = new Random();

    private int widthClouds = 200;
    private ArrayList<Cloud> clouds;

    private GamePanel gp;
    private Tile[] tiles;
    private int x = 0;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];
        clouds = new ArrayList<>();

        setCloudContent();

        getObstacleImage();
    }

    public void setCloudContent(){
        int amount = (gp.screenWidth + gp.tileSize) / widthClouds;
        double xCord = 0.0;

        try {
            for (int i = 0; i < amount; i++) {
                if(i != 0){
                    xCord = (clouds.get(i-1).getXPosition() * gp.screenWidth + widthClouds)/gp.screenWidth;
                }
                clouds.add(new Cloud(
                        ImageIO.read(getClass().getResourceAsStream("/cloud/schiarchWolke.png")),
                        rand.nextDouble((gp.screenHeight - gp.tileSize) / 3.0)/(gp.screenHeight - gp.tileSize),
                        xCord,
                        false)
                );
                //xCord += widthClouds;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getObstacleImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/ground/gras1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //ground
        if (x < -gp.tileSize) {
            x += gp.tileSize;
        }
        int i = x;
        while (i <= gp.screenWidth + gp.tileSize) {
            g2.drawImage(tiles[0].getImage(), i, gp.screenHeight - gp.tileSize, gp.tileSize, gp.tileSize, null);
            i += gp.tileSize;
        }
        x -= gp.xspeed;

        /*i = x;
        int counter = 0;
        while (i <= gp.screenWidth + gp.tileSize && counter < clouds.size()) {
            //if(clouds)
            g2.drawImage(clouds.get(counter).getImage(), i, (int)(clouds.get(counter).getHeight() * (gp.screenHeight - gp.tileSize)), gp.tileSize * 4, gp.tileSize * 4, null);
            i += widthClouds;
            counter++;
        }*/

        //System.out.println(clouds.size());
        for(int k = 0;k<clouds.size();k++){
            if((clouds.get(k).getXPosition()*gp.screenWidth)<(gp.tileSize*(-1))){
                System.out.println("Hallo");
                clouds.add(clouds.remove(0));
                clouds.get(clouds.size()-1).setXPosition((gp.screenWidth + gp.tileSize)/gp.screenWidth);
            }
            g2.drawImage(clouds.get(k).getImage(), (int)(clouds.get(k).getXPosition()*gp.screenWidth), (int)(clouds.get(k).getHeight() * (gp.screenHeight - gp.tileSize)), gp.tileSize, gp.tileSize, null);
            clouds.get(k).setXPosition((clouds.get(k).getXPosition()*gp.screenWidth - gp.xspeed)/gp.screenWidth);
        }
    }

    public void setCloudsAfterResize(){
        int amount = (this.gp.screenWidth + this.gp.tileSize) / 200;

        ArrayList<Cloud> newClouds = new ArrayList<>();
        double xCord = 0.0;

        try {
            for (int i = 0; i < amount; i++) {
                if(i<clouds.size()){
                    newClouds.add(clouds.get(i));
                }else {
                    if(i != 0){
                        xCord = (clouds.get(i-1).getXPosition() * gp.screenWidth + widthClouds)/gp.screenWidth;
                    }
                    newClouds.add(new Cloud(
                            ImageIO.read(getClass().getResourceAsStream("/cloud/schiarchWolke.png")),
                            rand.nextDouble((gp.screenHeight - gp.tileSize) / 3.0)/(gp.screenHeight - gp.tileSize),
                            xCord,
                            false)
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        clouds = newClouds;
    }
}
