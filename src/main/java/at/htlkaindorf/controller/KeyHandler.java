package at.htlkaindorf.controller;

import lombok.Data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
public class KeyHandler implements KeyListener {

    private boolean spacePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }
}
