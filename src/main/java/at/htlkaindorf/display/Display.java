package at.htlkaindorf.display;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
public class Display {

    private static final int KEY_F11 = 122;

    private JFrame window;
    private String title;
    private GamePanel gamePanel;

    public Display(String title, GamePanel gamePanel) {
        this.title = title;
        this.gamePanel = gamePanel;
        this.gamePanel.setDisplay(this);
        createDisplay();
        this.gamePanel.startGameThread();
    }

    private void createDisplay() {
        //fullScreen = true;
        window = new JFrame(title);
        window.add(gamePanel);
        //window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        //window.setResizable(false);
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.setUndecorated(true);
        window.setVisible(true);
        window.setAlwaysOnTop(true);
    }
}
