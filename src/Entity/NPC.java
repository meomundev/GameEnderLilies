package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPC extends Entity{
    public NPC(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }
    public void getImage() {
        upMove1 = setup("/npc/npcUp1New", gp.tileSize, gp.tileSize);
        upMove2 = setup("/npc/npcUp2New", gp.tileSize, gp.tileSize);
        downMove1 = setup("/npc/npcDown1New", gp.tileSize, gp.tileSize);
        downMove2 = setup("/npc/npcDown2New", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/npc/npcLeft1New", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/npc/npcLeft2New", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/npc/npcRight1New", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/npc/npcRight2New", gp.tileSize, gp.tileSize);
    }
    public void setDialogue() {
        dialogue[0] = "Hello, Little Knight!!\nWelcome to the Land Of The Dreams.";
        dialogue[1] = "I am Elder Bug";
        dialogue[2] = "This Island will make you happy";
        dialogue[3] = "Are you happy?";
    }
    public void speak() {
        super.speak();
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
