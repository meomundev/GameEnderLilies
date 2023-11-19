package Objects;

import Entity.Entity;
import Main.GamePanel;

public class Key extends Entity {
    public Key(GamePanel gp) {
        super(gp);

        name = "Key";
        downMove1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nKey";
    }
}
