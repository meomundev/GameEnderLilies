package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean keyPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //gan gia tri phim duoc nhan vao bien code

// title state
        if (gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
            }
            else if(code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
            }
            else if(code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }

        if(code == KeyEvent.VK_UP) {
            upPressed = true;
            keyPressed = true;
        }
        else if(code == KeyEvent.VK_DOWN) {
            downPressed = true;
            keyPressed = true;
        }
        else if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            keyPressed = true;
        }
        else if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            keyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {
            upPressed = false;
            keyPressed = false;
        }
        else if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
            keyPressed = false;
        }
        else if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
            keyPressed = false;
        }
        else if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            keyPressed = false;
        }
    }
}
