package objects;

import Entity.Projectile;
import Main.GamePanel;

public class FireBallBlue extends Projectile {
    GamePanel gp;
    public FireBallBlue(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "FireBallBlue";
        speed = 10;
        maxLife = 40;
        life = maxLife;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() {
        upMove1 = setup("/projectile/FireBallBlue/fireballUp1", gp.tileSize, gp.tileSize);
        upMove2 = setup("/projectile/FireBallBlue/fireballUp2", gp.tileSize, gp.tileSize);
        downMove1 = setup("/projectile/FireBallBlue/fireballDown1", gp.tileSize, gp.tileSize);
        downMove2 = setup("/projectile/FireBallBlue/fireballDown2", gp.tileSize, gp.tileSize);
        leftMove1 = setup("/projectile/FireBallBlue/fireballLeft1", gp.tileSize, gp.tileSize);
        leftMove2 = setup("/projectile/FireBallBlue/fireballLeft2", gp.tileSize, gp.tileSize);
        rightMove1 = setup("/projectile/FireBallBlue/fireballRight1", gp.tileSize, gp.tileSize);
        rightMove2 = setup("/projectile/FireBallBlue/fireballRight2", gp.tileSize, gp.tileSize);
    }
}
