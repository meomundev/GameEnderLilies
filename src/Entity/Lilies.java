package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Objects.FireBallBlue;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        attackArea.width = 48;
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues() {
        worldX= gp.tileSize * (gp.maxScreenCol/2) + (gp.tileSize * 3); // 72*(18/2) = 648
        worldY = gp.tileSize * (gp.maxScreenRow/2) + (gp.tileSize * 3) - 16; // 72*(10/2) = 360
        speed = 4;
        direction = "down";
// player status
        maxLife = 6;
        life = maxLife;
        projectile = new FireBallBlue(gp);
    }
    public void getPlayerImage() {
        up = setup("/player/upNew", gp.tileSize, gp.tileSize);
        upMove1 = setup("/player/upMove1New", gp.tileSize, gp.tileSize);
        upMove2 = setup("/player/upMove2New", gp.tileSize, gp.tileSize);
        down = setup("/player/downNew", gp.tileSize, gp.tileSize);
        downMove1 = setup("/player/downMove1New", gp.tileSize, gp.tileSize);
        downMove2 = setup("/player/downMove2New", gp.tileSize, gp.tileSize);
        left = setup("/player/leftNew", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/player/leftMove1New", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/player/leftMove2New", gp.tileSize, gp.tileSize);
        right = setup("/player/rightNew", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/player/rightMove1New", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/player/rightMove2New", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
        upAttack1 = setup("/player/upAttack1New", gp.tileSize, gp.tileSize * 2);
        upAttack2 = setup("/player/upAttack2New", gp.tileSize, gp.tileSize * 2);
        downAttack1 = setup("/player/downAttack1New", gp.tileSize, gp.tileSize * 2);
        downAttack2 = setup("/player/downAttack2New", gp.tileSize, gp.tileSize * 2);
        leftAttack1 = setup("/player/leftAttack1New", gp.tileSize * 2, gp.tileSize);
        leftAttack2 = setup("/player/leftAttack2New", gp.tileSize * 2, gp.tileSize);
        rightAttack1 = setup("/player/rightAttack1New", gp.tileSize * 2, gp.tileSize);
        rightAttack2 = setup("/player/rightAttack2New", gp.tileSize * 2, gp.tileSize);
    }
    public void update() {
        if (kh.attackPressed) {
            attacking = true;
            kh.attackPressed = false;
        }

        if (attacking) {
            attacking();
        }
        if (kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed) {
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
            int npcIndex = gp.collisionCheck.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
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
        if (kh.shootPressed && !projectile.alive && shootCounter == 50) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shootCounter = 0;
        }
        if (shootCounter < 50) {
            shootCounter++;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 15) {
            spriteNum = 1;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case " down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.collisionCheck.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        } else if (spriteCounter <= 30) {
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case " down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.collisionCheck.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        else {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void interactNPC(int i) {
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
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
    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible && gp.monster[i] != null) {
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int temScreenX = screenX;
        int temScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if (!attacking) {
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
                else {
                    temScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = upAttack1;
                    }
                    if (spriteNum == 2) {
                        image = upAttack2;
                    }
                }
            }
            case "down" -> {
                if (!attacking) {
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
                else {
                    if (spriteNum == 1) {
                        image = downAttack1;
                    }
                    if (spriteNum == 2) {
                        image = downAttack2;
                    }
                }
            }
            case "left" -> {
                if (!attacking) {
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
                else {
                    temScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = leftAttack1;
                    }
                    if (spriteNum == 2) {
                        image = leftAttack2;
                    }
                }
            }
            case "right" -> {
                if (!attacking) {
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
                else {
                    if (spriteNum == 1) {
                        image = rightAttack1;
                    }
                    if (spriteNum == 2) {
                        image = rightAttack2;
                    }
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
            if (invincibleCounterMod < 6 || (invincibleCounterMod >= 12 && invincibleCounterMod < 18) ||
                    (invincibleCounterMod >= 24 && invincibleCounterMod < 30) || (invincibleCounterMod >= 36 && invincibleCounterMod < 42) ||
                    (invincibleCounterMod >= 48 && invincibleCounterMod < 54)) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            } else {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
        g2.drawImage(image, temScreenX, temScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
