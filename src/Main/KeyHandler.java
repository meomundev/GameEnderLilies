package Main;

import Entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    Entity entity;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean keyPressed = false;
    public boolean attackPressed = false;
    public boolean shootPressed = false;

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
        else if (gp.gameState == gp.playState) {
            if (!attackPressed) {
                if (code == KeyEvent.VK_UP) {
                    upPressed = true;
                } else if (code == KeyEvent.VK_DOWN) {
                    downPressed = true;
                } else if (code == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                } else if (code == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                } else if (code == KeyEvent.VK_X) {
                    attackPressed = true;
                } else if (code == KeyEvent.VK_C) {
                    shootPressed = true;
                }
            }
        }
        if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
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
        else if (code == KeyEvent.VK_X) {
            attackPressed = false;
            keyPressed = false;
        }
        else if (code == KeyEvent.VK_C) {
            shootPressed = false;
            keyPressed = false;
        }
    }
}
