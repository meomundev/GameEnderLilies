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
        int i = 0;
        gp.object[i] = new Door(gp);
        gp.object[i].worldX = gp.tileSize * 11;
        gp.object[i].worldY = gp.tileSize * 8;
        i++;
        gp.object[i] = new Key(gp);
        gp.object[i].worldX = gp.tileSize * 10;
        gp.object[i].worldY = gp.tileSize * 6;
        gp.entityList.add(gp.object[i]);
        i++;
        gp.object[i] = new Shield2(gp);
        gp.object[i].worldX = gp.tileSize * 11;
        gp.object[i].worldY = gp.tileSize * 6;
        i++;
        gp.object[i] = new Sword2(gp);
        gp.object[i].worldX = gp.tileSize * 12;
        gp.object[i].worldY = gp.tileSize * 6;
    }

    public void setNPC() {
        gp.npc[0] = new NPC(gp);
        gp.npc[0].worldX = gp.tileSize * 16;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster() {
        if (gp.monster == null) {
            System.out.println("The monster array is null.");
            return; // Dừng việc thực hiện tiếp nếu mảng là null.
        }
        int i = 0;
        gp.monster[i] = new BatMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 11;
        gp.monster[i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[i] = new BatMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 33;
        gp.monster[i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[i] = new BatMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[i] = new BatMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 32;
        gp.monster[i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[i] = new BugMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 31;
        gp.monster[i].worldY = gp.tileSize * 11;
        i++;
        gp.monster[i] = new BugMonster(gp);
        gp.monster[i].worldX = gp.tileSize * 35;
        gp.monster[i].worldY = gp.tileSize * 11;

        // Thêm các quái vật vào danh sách entityList
        gp.entityList.add(gp.monster[i]);
    }
}
