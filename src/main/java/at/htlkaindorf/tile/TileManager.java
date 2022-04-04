package at.htlkaindorf.tile;

import at.htlkaindorf.display.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TileManager {

    private static final Random rand = new Random();

    private int widthClouds = 250;
    private int cloudScale = 2;
    private int lastCloud = 1;
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

    public void setCloudContent() {
        int amount = (gp.getScreenWidth() + gp.tileSize) / widthClouds;
        double xCord = 0.0;

        for (int i = 0; i < amount; i++) {
            if (i != 0) {
                xCord = (clouds.get(i - 1).getXPosition() * gp.getScreenWidth() + widthClouds) / gp.getScreenWidth();
            }
            clouds.add(new Cloud(
                    getRandomCloudImage(),
                    rand.nextDouble((gp.getScreenHeight() - gp.tileSize) / 5.0) / (gp.getScreenHeight() - gp.tileSize),
                    xCord,
                    false)
            );
            //xCord += widthClouds;
        }
    }

    private BufferedImage getRandomCloudImage() {
        try {
            //return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_0.png"));
            int s = rand.nextInt(7);

            if (s == lastCloud) {
                return getRandomCloudImage();
            }

            lastCloud = s;
            switch (s) {
                case 0:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_0.png"));
                case 1:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_1.png"));
                case 2:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_2.png"));
                case 3:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_3.png"));
                case 4:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_4.png"));
                case 5:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_5.png"));
                case 6:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_6.png"));
                case 7:
                    return ImageIO.read(getClass().getResourceAsStream("/clouds/sprite_7.png"));
                default:
                    return ImageIO.read(getClass().getResourceAsStream("/cloud/schiarchWolke.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        for (int k = 0; k < clouds.size(); k++) {
            if ((clouds.get(k).getXPosition() * gp.getScreenWidth()) < (gp.tileSize * (-1) * cloudScale)) {
                clouds.add(clouds.remove(0));
                clouds.get(clouds.size() - 1).setXPosition((gp.getScreenWidth() + gp.tileSize) / gp.getScreenWidth());
                clouds.get(clouds.size() - 1).setHeight(rand.nextDouble((gp.getScreenHeight() - gp.tileSize) / 5.0) / (gp.getScreenHeight() - gp.tileSize));
                clouds.get(clouds.size() - 1).setImage(getRandomCloudImage());
            }
            g2.drawImage(clouds.get(k).getImage(), (int) (clouds.get(k).getXPosition() * gp.getScreenWidth()), (int) (clouds.get(k).getHeight() * (gp.getScreenHeight() - gp.tileSize)), gp.tileSize * cloudScale, gp.tileSize * cloudScale, null);
            clouds.get(k).setXPosition((clouds.get(k).getXPosition() * gp.getScreenWidth() - gp.xspeed) / gp.getScreenWidth());
        }
    }

    public synchronized void setCloudsAfterResize() {
        clouds.clear();
        setCloudContent();
        /*
        int amount = (this.gp.getScreenWidth() + this.gp.tileSize) / widthClouds;

        ArrayList<Cloud> newClouds = new ArrayList<>();
        double xCord = 0.0;

        for (int i = 0; i < amount; i++) {
            if (i < clouds.size()) {
                newClouds.add(clouds.get(i));
            } else {
                if (i != 0) {
                    System.out.println(clouds.size());
                    System.out.println(i);
                    xCord = (clouds.get(i - 2).getXPosition() * gp.getScreenWidth() + widthClouds) / gp.getScreenWidth();
                }
                newClouds.add(new Cloud(
                        getRandomCloudImage(),
                        rand.nextDouble((gp.getScreenHeight() - gp.tileSize) / 5.0) / (gp.getScreenHeight() - gp.tileSize),
                        xCord,
                        false)
                );
            }
        }
        clouds = newClouds;

         */
    }
}
