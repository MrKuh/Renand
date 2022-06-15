package at.htlkaindorf.game;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Display {
    private static final int KEY_F11 = 122;

    private JFrame window;
    private String title;
    private GamePanel gp;

    public Display(String title, GamePanel gamePanel) {
        this.title = title;
        this.gp = gamePanel;
        this.gp.setDisplay(this);
        createDisplay();
        this.gp.startGameThread();
    }

    private void createDisplay() {
        window = new JFrame(title);
        window.add(gp);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(gp.getScreenWidth(), gp.getScreenHeight());
        window.setUndecorated(false);
        window.pack();
        window.setVisible(true);

        //window.setAlwaysOnTop(true);
    }
}
