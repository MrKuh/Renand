package at.htlkaindorf.renand;

import at.htlkaindorf.game.Display;
import at.htlkaindorf.game.GamePanel;

public class Launcher {
    private static Display window;

    public Launcher() {
        this.window = new Display("Renand", new GamePanel());
    }

    public static void main(String[] args) {

        Launcher launcher = new Launcher();

    }

}
