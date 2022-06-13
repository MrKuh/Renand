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
        window.setPreferredSize(new Dimension(gp.getScreenWidth(), gp.getScreenHeight()));
        window.add(gp);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setUndecorated(false);
        window.pack();
        window.setVisible(true);

        //window.setAlwaysOnTop(true);
    }
    public void changeDisplay(int screenWidth, int screenHeight) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        panel.setBackground(Color.white);
        panel.setVisible(true);
        //window.add(panel);

        StartScreen startScreen = new StartScreen(screenWidth, screenHeight);

        window.getContentPane().removeAll();
        window.getContentPane().invalidate();

        window.setContentPane(startScreen);
        window.getContentPane().revalidate();
    }
}
