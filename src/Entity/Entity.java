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
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionCheck.checkTile(this);
        gp.collisionCheck.checkEntity(this, gp.monster);

        int newWorldX = worldX;
        int newWorldY = worldY;

        if (!collisionOn) {
            switch (direction) {
                case "up" -> newWorldY -= speed;
                case "down" -> newWorldY += speed;
                case "left" -> newWorldX -= speed;
                case "right" -> newWorldX += speed;
            }
        }

        // Kiểm tra xem quái vật có vượt ra khỏi giới hạn màn hình không
        if (newWorldX >= 0 && newWorldX <= gp.worldWidth && newWorldY >= 0 && newWorldY <= gp.worldHeight) {
            // Nếu quái vật vẫn ở trong giới hạn màn hình, cập nhật tọa độ mới
            worldX = newWorldX;
            worldY = newWorldY;
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
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
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
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
