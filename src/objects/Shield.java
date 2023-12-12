package objects;

import Entity.Entity;
import Main.GamePanel;

public class Shield extends Entity {
    public Shield(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Wooden Shield";
        downMove1 = setup("/objectsRes/shield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nDefense: 1";
    }
}