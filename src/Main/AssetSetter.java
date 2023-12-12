package Main;

import Entity.NPC;
import monster.BatMonster;
import monster.BatMonster2;
import monster.BugMonster;
import monster.BugMonster2;
import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        int mapNum = 0;
        int i = 0;
        gp.object[mapNum][i] = new Door(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 25;
        gp.object[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.object[mapNum][i] = new Door(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 28;
        gp.object[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        gp.object[mapNum][i] = new Key(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 32;
        gp.object[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.object[mapNum][i] = new Key(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 39;
        gp.object[mapNum][i].worldY = gp.tileSize * 21;

        i++;
        gp.object[mapNum][i] = new Sword2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 44;
        gp.object[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        mapNum++;
        gp.object[mapNum][i] = new Door(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 28;
        gp.object[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.object[mapNum][i] = new Key(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 39;
        gp.object[mapNum][i].worldY = gp.tileSize * 22;

        i++;
        gp.object[mapNum][i] = new Shield2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 9;
        gp.object[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.entityList.add(gp.object[mapNum][i]);
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 9;
        gp.npc[mapNum][i].worldY = gp.tileSize * 22;
    }

    public void setMonster() {
        if (gp.monster == null) {
            System.out.println("The monster array is null.");
            return;
        }
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 29;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 29;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;

        mapNum++;
        gp.monster[mapNum][i] = new BatMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BatMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BatMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 26;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BugMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BugMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BatMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;
        i++;
        gp.monster[mapNum][i] = new BatMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;
        i++;
        gp.monster[mapNum][i] = new BugMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.monster[mapNum][i] = new BugMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.monster[mapNum][i] = new BugMonster2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 26;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        // Thêm các quái vật vào danh sách entityList
        gp.entityList.add(gp.monster[mapNum][i]);
    }
}
