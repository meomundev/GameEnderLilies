package Monster;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class BatMonster extends Entity {
    public BatMonster(GamePanel gp) {
        super(gp);

        direction = "up";
        name = "BatMonster";
        speed = 2;
        maxLife = 2;
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
        up = setup("/monster/batUp1");
        upMove1 = setup("/monster/batUp1");
        upMove2 = setup("/monster/batUp2");
        downMove1 = setup("/monster/batDown1");
        downMove2 = setup("/monster/batDown2");
        leftMove1 = setup("/monster/batLeft1");
        leftMove2 = setup("/monster/batLeft2");
        rightMove1 = setup("/monster/batRight1");
        rightMove2 = setup("/monster/batRight2");
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
