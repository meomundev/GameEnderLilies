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
            titleState(code);
        }
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        else if (gp.gameState == gp.inventoryState) {
            inventoryState(code);
        }
    }
    public void titleState(int code) {
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
    public void playState(int code) {
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
            } else if (code == KeyEvent.VK_A) {
                gp.gameState = gp.characterState;
            } else if (code == KeyEvent.VK_I) {
                gp.gameState = gp.inventoryState;
            }
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_A) {
            gp.gameState = gp.playState;
        }
    }
    public void inventoryState(int code) {
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_I) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 4) {
                gp.ui.slotRow++;
            }
        } else if (code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
            }
        } else if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
            }
        } else if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotCol != 6) {
                gp.ui.slotCol++;
            }
        } else if (code == KeyEvent.VK_ENTER) {
            gp.lilies.selectItem();
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
