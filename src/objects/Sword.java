package objects;

import Entity.Entity;
import Main.GamePanel;

public class Sword extends Entity {
    public Sword(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Sword";
        downMove1 = setup("/objectsRes/sword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAttack: 1";
    }
}
