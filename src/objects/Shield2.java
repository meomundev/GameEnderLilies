package objects;

import Entity.Entity;
import Main.GamePanel;

public class Shield2 extends Entity {
    public Shield2(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Steel Shield";
        downMove1 = setup("/objectsRes/shield2", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nDefense: 2";
    }
}