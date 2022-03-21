package at.htlkaindorf.renand;

import at.htlkaindorf.display.Display;
import at.htlkaindorf.display.GamePanel;

public class Launcher{

    //window size
    private static final int originalTitleSize = 16;
    private static final int scale = 4;

    private static final int titleSize = originalTitleSize *scale;
    private static final int maxScreenCol= 16;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = titleSize * maxScreenCol;
    private static final int screenHeight = titleSize * maxScreenRow;




    public static void main(String[] args) {
        new Display("Renand", new GamePanel());
    }

}
