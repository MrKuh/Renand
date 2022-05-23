package at.htlkaindorf.controller;

import at.htlkaindorf.game.GamePanel;
import at.htlkaindorf.renand.Launcher;
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
            case KeyEvent.VK_A:
                gp.setRunWithEnemies(true);
                break;
            case KeyEvent.VK_ENTER:
                gp.setScore(0);
                if (gp.isRunning()) {
                    gp.setRunWithEnemies(false);
                }
                gp.setRunning(true);
                break;
            case KeyEvent.VK_ESCAPE:
                Launcher.changeScreen(gp.getScreenWidth(), gp.getScreenHeight());
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
            case KeyEvent.VK_F11:
                gp.getDisplay().getWindow().setLocation(0, 0);
                gp.setFullScreen(!gp.isFullScreen());
                gp.getDisplay().getWindow().setVisible(false);
                gp.getDisplay().getWindow().dispose();
                gp.getDisplay().getWindow().setUndecorated(gp.isFullScreen());
                if (gp.isFullScreen()) {
                    gp.setScreenWidth((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
                    gp.setScreenHeight((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

                    gp.getDisplay().getWindow().setSize(gp.getScreenWidth(), gp.getScreenHeight());
                    gp.getDisplay().getWindow().validate();
                } else {
                    gp.setScreenWidth(gp.tileSize * gp.maxScreenCol);
                    gp.setScreenHeight(gp.tileSize * gp.maxScreenRow);

                    gp.getDisplay().getWindow().setSize(gp.getScreenWidth(), gp.getScreenHeight());
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
