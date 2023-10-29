package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //gan gia tri phim duoc nhan vao bien code

        if(code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if(code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        else if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        else if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
