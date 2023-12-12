package objects;

import Entity.Entity;
import Main.GamePanel;

public class Key extends Entity {
    public Key(GamePanel gp) {
        super(gp);

        name = "Key";
        downMove1 = setup("/objectsRes/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nKey";
    }
}
