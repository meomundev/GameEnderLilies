package Objects;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Entity {
    public Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down = setup("/objects/door", gp.tileSize, gp.tileSize);
        downMove1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
