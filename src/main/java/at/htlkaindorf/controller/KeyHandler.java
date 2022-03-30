package at.htlkaindorf.controller;

import at.htlkaindorf.display.GamePanel;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data

public class KeyHandler implements KeyListener {

    private boolean spacePressed;
    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
            case KeyEvent.VK_F11:
                gp.setFullScreen(!gp.isFullScreen());
                gp.getDisplay().getWindow().setVisible(false);
                gp.getDisplay().getWindow().dispose();
                gp.getDisplay().getWindow().setUndecorated(gp.isFullScreen());
                if (gp.isFullScreen()) {
                    gp.screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                    gp.screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

                    gp.getDisplay().getWindow().setSize(gp.screenWidth, gp.screenHeight);
                    gp.getDisplay().getWindow().validate();
                } else {
                    gp.screenWidth = gp.tileSize * gp.maxScreenCol;
                    gp.screenHeight = gp.tileSize * gp.maxScreenRow;

                    gp.getDisplay().getWindow().setSize(gp.screenWidth, gp.screenHeight);
                    gp.getDisplay().getWindow().pack();
                }

                gp.getDisplay().getWindow().setVisible(true);

                gp.getTileManager().setCloudsAfterResize();

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
