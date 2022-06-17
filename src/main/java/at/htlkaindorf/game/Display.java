package at.htlkaindorf.game;

import lombok.Data;

import javax.swing.*;

/**
 * This class {@code Display} contains a JFrame with the GamePanel.<br>
 * When the constructor is called, our game window opens and the game loop gets started.
 *
 * @author MrKuh
 * @author Bensi
 * @version 1.30
 */
@Data
public class Display {
    /**
     * This {@code JFrame} is the JFrame where the {@code GamePanel} opens.
     */
    private JFrame window;
    /**
     * This {@code String} defines the Title of the {@code JFrame} window.
     */
    private String title;
    /**
     * Just an object of {@code GamePanel}. <br>
     * It is needed if u want to call a function of {@code GamePanel}.
     */
    private GamePanel gamePanel;

    public Display(String title, GamePanel gamePanel) {
        this.title = title;
        this.gamePanel = gamePanel;
        this.gamePanel.setDisplay(this);
        createDisplay();
        this.gamePanel.startGameThread();
    }

    /**
     * This function creates the window {@code JFrame}. <br>
     * It adds the {@code GamePanel} to the window and sets basic settings of our JFrame.
     */
    private void createDisplay() {
        window = new JFrame(title);
        window.add(gamePanel);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        window.setUndecorated(false);
        window.pack();
        window.setVisible(true);

        //window.setAlwaysOnTop(true);
    }
}
