package Monster;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class BugMonster extends Entity {
    GamePanel gp;
    public BugMonster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        direction = "up";
        name = "BugMonster";
        speed = 4;
        maxLife = 5;
        life = maxLife;

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
        up = setup("/monster/batUp1", gp.tileSize, gp.tileSize);
        upMove1 = setup("/monster/bugUp1", gp.tileSize, gp.tileSize);
        upMove2 = setup("/monster/bugUp2", gp.tileSize, gp.tileSize);
        downMove1 = setup("/monster/bugDown1", gp.tileSize, gp.tileSize);
        downMove2 = setup("/monster/bugDown2", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/monster/bugLeft1", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/monster/bugLeft2", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/monster/bugRight1", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/monster/bugRight2", gp.tileSize, gp.tileSize);
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
}
