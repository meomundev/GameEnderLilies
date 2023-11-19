package Objects;

import Entity.Entity;
import Main.GamePanel;

public class Sword2 extends Entity {
    public Sword2(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Ice Sword";
        downMove1 = setup("/objects/sword2", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]\nAttack: 2";
    }
}