package at.htlkaindorf.controller;

import at.htlkaindorf.game.GamePanel;
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


        switch (keyCode) {
            case KeyEvent.VK_A:
                gp.setRunWithEnemies(true);
                break;
            case KeyEvent.VK_ENTER:
                if (!gp.isRunning() && !gp.isPaused()) {
                    gp.setScore(0);
                    gp.resetTheGame();
                    gp.setRunWithEnemies(false);
                    gp.setShowStartScreen(true);
                    gp.getCollisionSound().stop();
                }
                gp.setRunning(true);
                gp.setPaused(false);
                break;
            case KeyEvent.VK_ESCAPE:
                //Launcher.changeScreen(gp.getScreenWidth(), gp.getScreenHeight());
                if(gp.isRunWithEnemies()){
                    if(gp.isPaused() && !gp.isRunning()){
                        gp.setPaused(false);
                        gp.setRunning(true);
                    }else if(!gp.isPaused() && gp.isRunning()){
                        gp.setPaused(true);
                        gp.setRunning(false);
                    }
                }

                break;
            case KeyEvent.VK_SPACE:
                if(!spacePressed) {
                    gp.getFlySound().setFramePosition(0);
                    gp.getFlySound().start();
                }

                spacePressed = true;

                gp.setRunWithEnemies(true);
                gp.setShowStartScreen(false);

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

            gp.getFlySound().stop();
            gp.getFlySound().setFramePosition(0);
        }
    }
}
