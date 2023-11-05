package Main;

import Entity.NPC;
import Monster.BatMonster;
import Objects.Door;
import Objects.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.object[0] = new Key();
        gp.object[0].worldX = gp.tileSize * 32;
        gp.object[0].worldY = gp.tileSize * 15;

        gp.object[1] = new Door();
        gp.object[1].worldX = gp.tileSize * 24;
        gp.object[1].worldY = gp.tileSize * 14;
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

        gp.monster[0] = new BatMonster(gp);
        gp.monster[0].worldX = gp.tileSize * 18;
        gp.monster[0].worldY = gp.tileSize * 20;

        gp.monster[1] = new BatMonster(gp);
        gp.monster[1].worldX = gp.tileSize * 19;
        gp.monster[1].worldY = gp.tileSize * 20;

        // Thêm các quái vật vào danh sách entityList
        gp.entityList.add(gp.monster[0]);
        gp.entityList.add(gp.monster[1]);
    }
}
