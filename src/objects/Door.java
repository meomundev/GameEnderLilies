package objects;

import Entity.Entity;
import Main.GamePanel;

public class Door extends Entity {
    public Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down = setup("/objectsRes/door", gp.tileSize, gp.tileSize);
        downMove1 = setup("/objectsRes/door", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
