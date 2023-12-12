package Entity;

import Main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp) {
        super(gp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    public void update() {
        if (user == gp.lilies) {
            int monsterIndex = gp.collisionCheck.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.lilies.damageMonster(monsterIndex);
                alive = false;
            }
        }
        //contact to monster
        if (user != gp.lilies) {
            boolean contactPlayer = gp.collisionCheck.checkPlayer(this);
            if (!gp.lilies.invincible && contactPlayer) {
                damagePlayer(attack);
            }
        }
        switch (direction) {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        life--;
        if (life <= 0) {
            alive = false;
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
}
