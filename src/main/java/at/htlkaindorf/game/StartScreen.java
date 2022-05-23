package at.htlkaindorf.game;

import javax.swing.*;
import java.awt.*;

import static at.htlkaindorf.game.GamePanel.tileSize;

public class StartScreen extends JPanel {

    public StartScreen(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public StartScreen(LayoutManager layout) {
        super(layout);
    }

    public StartScreen(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public StartScreen(int screenWidth, int screenHeight) {
        this.setSize(screenWidth/3, screenHeight/3);
        this.setBackground(Color.decode("#0f0f0f0f"));
        this.setLayout(null);
        JLabel label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        this.add(label);
        JTextField username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        this.add(username);
        this.setVisible(true);

        JLabel password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        this.add(password1);

        JTextField password = new JPasswordField();
        password.setBounds(100, 75, 193, 28);
        this.add(password);


    }
}
