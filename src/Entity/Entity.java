package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String name;
    public BufferedImage up, upMove1, upMove2, down, downMove1, downMove2, left, leftMove1, leftMove2, right, rightMove1, rightMove2;
    public BufferedImage downAttack1, downAttack2, upAttack1, upAttack2, leftAttack1, leftAttack2, rightAttack1, rightAttack2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int type; // player = 0; npc = 1; monster = 2;
    public int typeLilies = 0;
    public int typeNPC = 1;
    public int typeMonster = 2;
    public int typeSword = 3;
    public int typeShield = 4;
    public int typeConsumable = 5;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    boolean attacking = false;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int point;
    public Entity currentWeapon;
    public Entity currentShield;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int maxLife;
    public int life;
    public int useCost;
    public Projectile projectile;
    public int shootCounter = 0;
    String[] dialogue = new String[20];
    int dialogueIndex = 0;
    boolean hpOn = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    public BufferedImage image, image2, image3;
    public boolean collision = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {

    }
    public void damageReaction() {

    }
    public void damagePlayer(int attack) {
        if (!gp.lilies.invincible) {
            int damage = attack - gp.lilies.defense;
            if (damage <= 0) {
                damage = 0;
            }
            gp.lilies.life -= damage;
            gp.lilies.invincible = true;
        }
    }
    public void speak() {
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;
        if (dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        switch (gp.lilies.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*2) {
            changeAlpha(g2, 1f);
        } else if (dyingCounter <= i*3) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*4) {
            changeAlpha(g2, 1f);
        } else if (dyingCounter <= i*5) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*6) {
            changeAlpha(g2, 1f);
        } else if (dyingCounter <= i*7) {
            changeAlpha(g2, 0f);
        } else if (dyingCounter <= i*8) {
            changeAlpha(g2, 1f);
        } else {
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionCheck.checkTile(this);
        gp.collisionCheck.checkObject(this, false);
        gp.collisionCheck.checkEntity(this, gp.monster);
        boolean contactLilies = gp.collisionCheck.checkPlayer(this);

        if (this.type == typeMonster && contactLilies) {
            damagePlayer(attack);
        }

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shootCounter < 100) {
            shootCounter++;
        }
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            if (image == null) {
                System.err.println("Error loading image: " + imagePath);
                return null;
            }
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.lilies.worldX + gp.lilies.screenX;
        int screenY = worldY - gp.lilies.worldY + gp.lilies.screenY;
        if (direction != null) {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = upMove1;
                    } else if (spriteNum == 2) {
                        image = upMove2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = downMove1;
                    } else if (spriteNum == 2) {
                        image = downMove2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = leftMove1;
                    } else if (spriteNum == 2) {
                        image = leftMove2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = rightMove1;
                    } else if (spriteNum == 2) {
                        image = rightMove2;
                    }
                }
            }
// HP monster
            if (type == 2 && hpOn) {
                double oneHP = (double) gp.tileSize / maxLife;
                double monsterHP = oneHP * life;

                g2.setColor(new Color(80, 80, 80));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 9);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) monsterHP, 8);
            }
// thoi gian invicible
            if (invincible) {
                int invincibleCounterMod = invincibleCounter % 60;
                hpOn = true;
                if (invincibleCounterMod < 6 ||
                        (invincibleCounterMod >= 12 && invincibleCounterMod < 18) ||
                        (invincibleCounterMod >= 24 && invincibleCounterMod < 30) ||
                        (invincibleCounterMod >= 36 && invincibleCounterMod < 42) ||
                        (invincibleCounterMod >= 48 && invincibleCounterMod < 54)) {
                    changeAlpha(g2, 0.3f);
                } else {
                    changeAlpha(g2, 1f);
                }
                if (dying) {
                    dyingAnimation(g2);
                }
            }
            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }
}
