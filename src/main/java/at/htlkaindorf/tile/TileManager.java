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
        int amount = (gp.getScreenWidth() + gp.tileSize) / widthClouds;
        double xCord = 0.0;

        try {
            for (int i = 0; i < amount; i++) {
                if(i != 0){
                    xCord = (clouds.get(i-1).getXPosition() * gp.getScreenWidth() + widthClouds)/gp.getScreenWidth();
                }
                clouds.add(new Cloud(
                        ImageIO.read(getClass().getResourceAsStream("/cloud/schiarchWolke.png")),
                        rand.nextDouble((gp.getScreenHeight() - gp.tileSize) / 3.0)/(gp.getScreenHeight() - gp.tileSize),
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
        while (i <= gp.getScreenWidth() + gp.tileSize) {
            g2.drawImage(tiles[0].getImage(), i, gp.getScreenHeight() - gp.tileSize, gp.tileSize, gp.tileSize, null);
            i += gp.tileSize;
        }
        x -= gp.xspeed;

        /*i = x;
        int counter = 0;
        while (i <= gp.getScreenWidth( + gp.tileSize && counter < clouds.size()) {
            //if(clouds)
            g2.drawImage(clouds.get(counter).getImage(), i, (int)(clouds.get(counter).getHeight() * (gp.getScreenHeight( - gp.tileSize)), gp.tileSize * 4, gp.tileSize * 4, null);
            i += widthClouds;
            counter++;
        }*/

        //System.out.println(clouds.size());
        for(int k = 0;k<clouds.size();k++){
            if((clouds.get(k).getXPosition()*gp.getScreenWidth())<(gp.tileSize*(-1))){
                System.out.println("Hallo");
                clouds.add(clouds.remove(0));
                clouds.get(clouds.size()-1).setXPosition((gp.getScreenWidth() + gp.tileSize)/gp.getScreenWidth());
            }
            g2.drawImage(clouds.get(k).getImage(), (int)(clouds.get(k).getXPosition()*gp.getScreenWidth()), (int)(clouds.get(k).getHeight() * (gp.getScreenHeight() - gp.tileSize)), gp.tileSize, gp.tileSize, null);
            clouds.get(k).setXPosition((clouds.get(k).getXPosition()*gp.getScreenWidth() - gp.xspeed)/gp.getScreenWidth());
        }
    }

    public void setCloudsAfterResize(){
        int amount = (this.gp.getScreenWidth() + this.gp.tileSize) / 200;

        ArrayList<Cloud> newClouds = new ArrayList<>();
        double xCord = 0.0;

        try {
            for (int i = 0; i < amount; i++) {
                if (i < clouds.size()) {
                    newClouds.add(clouds.get(i));
                }else {
                    if(i != 0){
                        xCord = (clouds.get(i-1).getXPosition() * gp.getScreenWidth() + widthClouds)/gp.getScreenWidth();
                    }
                    newClouds.add(new Cloud(
                            ImageIO.read(getClass().getResourceAsStream("/cloud/schiarchWolke.png")),
                            rand.nextDouble((gp.getScreenHeight() - gp.tileSize) / 3.0)/(gp.getScreenHeight() - gp.tileSize),
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
