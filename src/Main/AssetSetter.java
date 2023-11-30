package Main;

import Entity.NPC;
import Monster.BatMonster;
import Monster.BugMonster;
import Objects.Door;
import Objects.Key;
import Objects.Shield2;
import Objects.Sword2;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        int mapNum = 0;
        int i = 0;
        gp.object[mapNum][i] = new Door(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 11;
        gp.object[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.object[mapNum][i] = new Key(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 10;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.object[mapNum][i] = new Shield2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 11;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;
        i++;
        gp.object[mapNum][i] = new Sword2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 12;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;
        i++;

        mapNum++;
        gp.object[mapNum][i] = new Door(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 11;
        gp.object[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.object[mapNum][i] = new Key(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 10;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.object[mapNum][i] = new Shield2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 11;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;
        i++;
        gp.object[mapNum][i] = new Sword2(gp);
        gp.object[mapNum][i].worldX = gp.tileSize * 12;
        gp.object[mapNum][i].worldY = gp.tileSize * 6;

        gp.entityList.add(gp.object[mapNum][i]);
    }

    public void setNPC() {
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 16;
        gp.npc[mapNum][0].worldY = gp.tileSize * 21;
    }

    public void setMonster() {
        if (gp.monster == null) {
            System.out.println("The monster array is null.");
            return; // Dừng việc thực hiện tiếp nếu mảng là null.
        }
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;

        mapNum++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[mapNum][i] = new BatMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[mapNum][i] = new BugMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        // Thêm các quái vật vào danh sách entityList
        gp.entityList.add(gp.monster[mapNum][i]);
    }
}
