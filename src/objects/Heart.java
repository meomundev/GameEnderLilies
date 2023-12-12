package objects;

import Entity.Entity;
import Main.GamePanel;

public class Heart extends Entity {
    public Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/objectsRes/heartFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objectsRes/heartHalf", gp.tileSize, gp.tileSize);
        image3 = setup("/objectsRes/heartEmpty", gp.tileSize, gp.tileSize);
    }
}
