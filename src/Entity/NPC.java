package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC extends Entity{
    public NPC(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
    }
    public void getImage() {
        upMove1 = setup("/npc/npcUp1");
        upMove2 = setup("/npc/npcUp2");
        downMove1 = setup("/npc/npcDown1");
        downMove2 = setup("/npc/npcDown2");
        leftMove1 = setup("/npc/npcLeft1");
        leftMove2 = setup("/npc/npcLeft2");
        rightMove1 = setup("/npc/npcRight1");
        rightMove2 = setup("/npc/npcRight2");
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
