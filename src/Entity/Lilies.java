package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Lilies extends Entity{
    KeyHandler kh;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    public int standCounter = 0;

    public Lilies(GamePanel gp, KeyHandler kh) {
        super(gp);
        this.kh = kh;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // 1296/2-(72/2) = 612
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // 720/2-(72/2) = 324

        solidArea = new Rectangle(24, 36, gp.tileSize - 48, gp.tileSize - 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX= gp.tileSize * (gp.maxScreenCol/2); // 72*(18/2) = 648
        worldY = gp.tileSize * (gp.maxScreenRow/2); // 72*(10/2) = 360
        speed = 4;
        direction = "right";
// player status
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage() {
        up = setup("/player/up");
        upMove1 = setup("/player/upMove1");
        upMove2 = setup("/player/upMove2");
        down = setup("/player/down");
        downMove1 = setup("/player/downMove1");
        downMove2 = setup("/player/downMove2");
        left = setup("/player/left");
        leftMove1 = setup("/player/leftMove1");
        leftMove2 = setup("/player/leftMove2");
        right = setup("/player/right");
        rightMove1 = setup("/player/rightMove1");
        rightMove2 = setup("/player/rightMove2");
    }

    public void update() {
        if (kh.upPressed == true || kh.downPressed == true || kh.leftPressed == true || kh.rightPressed == true) {
            if(kh.upPressed) {
                direction = "up";
            }
            else if(kh.downPressed) {
                direction = "down";
            }
            else if(kh.leftPressed) {
                direction = "left";
            }
            else {
                direction = "right";
            }
// check tile collision
            collisionOn = false;
            gp.collisionCheck.checkTile(this);
// check object collision
            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);
// check npc collision
// check monster collision
            int monsterIndex = gp.collisionCheck.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
// neu collision = false thi co the di chuyen
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
// Trong phương thức update:
            spriteCounter++;
            if (spriteCounter > 20) {
                // Nếu có phím nào đó được nhấn
                if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
                    if (kh.keyPressed) {
                        // Nếu đã nhấn trước đó, chuyển giữa 2 và 3
                        if (spriteNum == 2) {
                            spriteNum = 3;
                        } else {
                            spriteNum = 2;
                        }
                    } else {
                        // Nếu không được nhấn trước đó, bắt đầu từ 2
                        spriteNum = 2;
                        kh.keyPressed = true;
                    }
                } else {
                    // Không có phím nào được nhấn, đặt lại thành 1 và đánh dấu là không có phím nào được nhấn
                    spriteNum = 1;
                    kh.keyPressed = false;
                }
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.object[i].name;
            switch (objectName) {
                case "Key" -> {
                    hasKey++;
                    gp.object[i] = null;
                    System.out.println("Key: " + hasKey);
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.object[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                }
            }
        }
    }
    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up;
                }
                if (spriteNum == 2) {
                    image = upMove1;
                }
                if (spriteNum == 3) {
                    image = upMove2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = downMove1;
                }
                if (spriteNum == 3) {
                    image = downMove2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left;
                }
                if (spriteNum == 2) {
                    image = leftMove1;
                }
                if (spriteNum == 3) {
                    image = leftMove2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right;
                }
                if (spriteNum == 2) {
                    image = rightMove1;
                }
                if (spriteNum == 3) {
                    image = rightMove2;
                }
            }
        }

        int x = screenX;
        int y = screenY;
        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
        if (invincible) {
            int invincibleCounterMod = invincibleCounter % 60;
            if (invincibleCounterMod < 10 || (invincibleCounterMod >= 20 && invincibleCounterMod < 30) || (invincibleCounterMod >= 40 && invincibleCounterMod < 50)) {
                // Khi invincibleCounterMod là 0-9, 20-29, 40-49 (rõ)
                // Sử dụng độ trong suốt 1f (hoàn toàn rõ)
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            } else {
                // Khi invincibleCounterMod là 10-19, 30-39, 50-59 (mờ)
                // Sử dụng độ trong suốt 0.3f (mờ)
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
        g2.drawImage(image, x, y, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
