package at.htlkaindorf.display;

import lombok.Data;

import javax.swing.*;

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
        //window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        //window.setResizable(false);
        window.setSize(gp.getScreenWidth(), gp.getScreenHeight());
        window.setUndecorated(false);
        window.pack();
        window.setVisible(true);
        //window.setAlwaysOnTop(true);
    }
}
