package monster;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class BugMonster extends Entity {
    GamePanel gp;
    public BugMonster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = typeMonster;
        direction = "up";
        name = "Bug Monster";
        speed = 6;
        maxLife = 6;
        life = maxLife;
        exp = 2;
        attack = 3;
        defense = 0;
        point = 1;

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
        upMove1 = setup("/monsterRes/bugUp1", gp.tileSize, gp.tileSize);
        upMove2 = setup("/monsterRes/bugUp2", gp.tileSize, gp.tileSize);
        downMove1 = setup("/monsterRes/bugDown1", gp.tileSize, gp.tileSize);
        downMove2 = setup("/monsterRes/bugDown2", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/monsterRes/bugLeft1", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/monsterRes/bugLeft2", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/monsterRes/bugRight1", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/monsterRes/bugRight2", gp.tileSize, gp.tileSize);
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
    }
    public void damageReaction() {
        actionLockCounter = 0;
        if (Objects.equals(gp.lilies.direction, "up")) {
            direction = "down";
        } else if (Objects.equals(gp.lilies.direction, "down")) {
            direction = "up";
        } else if (Objects.equals(gp.lilies.direction, "left")) {
            direction = "right";
        } else if (Objects.equals(gp.lilies.direction, "right")) {
            direction = "left";
        }
    }
}
