package at.htlkaindorf.display;

import javax.swing.*;

public class Display {

    private JFrame window;
    private String title;
    private GamePanel gamePanel;

    public Display(String title, GamePanel gamePanel) {
        this.title = title;
        this.gamePanel = gamePanel;
        createDisplay();
        gamePanel.startGameThread();
    }

    private void createDisplay(){
        window = new JFrame(title);
        window.add(gamePanel);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }
}
