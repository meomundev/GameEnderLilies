package objects;

import Entity.Projectile;
import Main.GamePanel;

public class ProjectileDemon extends Projectile {
    GamePanel gp;
    public ProjectileDemon(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Projectile Bat";
        speed = 10;
        attack = 3;
        maxLife = 60;
        life = maxLife;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() {
        upMove1 = setup("/projectile/ProjectileDemon/projectileDemonUp1", gp.tileSize, gp.tileSize);
        upMove2 = setup("/projectile/ProjectileDemon/projectileDemonUp2", gp.tileSize, gp.tileSize);
        downMove1 = setup("/projectile/ProjectileDemon/projectileDemonDown1", gp.tileSize, gp.tileSize);
        downMove2 = setup("/projectile/ProjectileDemon/projectileDemonDown2", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/projectile/ProjectileDemon/projectileDemonLeft1", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/projectile/ProjectileDemon/projectileDemonLeft2", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/projectile/ProjectileDemon/projectileDemonRight1", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/projectile/ProjectileDemon/projectileDemonRight2", gp.tileSize, gp.tileSize);
    }
}
