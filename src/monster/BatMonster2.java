package monster;

import Entity.Entity;
import Main.GamePanel;
import objects.ProjectileDemon2;

import java.awt.*;
import java.util.Random;

public class BatMonster2 extends Entity {
    GamePanel gp;
    public BatMonster2(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = typeMonster;
        direction = "up";
        name = "Bat Monster";
        speed = 2;
        maxLife = 6;
        life = maxLife;
        attack = 12;
        defense = 2;
        exp = 2;
        point = 1;
        projectile = new ProjectileDemon2(gp);

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize / 3;
        solidArea.y = gp.tileSize / 4;
        solidArea.width = gp.tileSize - (solidArea.x * 2);
        solidArea.height = gp.tileSize - (solidArea.y * 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        up = setup("/monsterRes/batUp1", gp.tileSize, gp.tileSize);
        upMove1 = setup("/monsterRes/batUp1", gp.tileSize, gp.tileSize);
        upMove2 = setup("/monsterRes/batUp2", gp.tileSize, gp.tileSize);
        downMove1 = setup("/monsterRes/batDown1", gp.tileSize, gp.tileSize);
        downMove2 = setup("/monsterRes/batDown2", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/monsterRes/batLeft1", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/monsterRes/batLeft2", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/monsterRes/batRight1", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/monsterRes/batRight2", gp.tileSize, gp.tileSize);
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
        for (int i = 0; i <= 100; i++) {
            if (i == 100 && shootCounter == 90) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shootCounter = 0;
                i = 0;
            }
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.lilies.direction;
    }
}
