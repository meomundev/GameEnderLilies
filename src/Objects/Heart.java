package Objects;

import Entity.Entity;
import Main.GamePanel;

public class Heart extends Entity {
    public Heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/objects/heartFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heartHalf", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heartEmpty", gp.tileSize, gp.tileSize);
    }
}
