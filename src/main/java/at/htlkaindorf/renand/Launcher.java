package at.htlkaindorf.renand;

import at.htlkaindorf.display.Display;
import at.htlkaindorf.display.GamePanel;

public class Launcher{

    public static void main(String[] args) {
        new Display("Renand", new GamePanel());
    }
}
