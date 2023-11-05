package Objects;

import Main.GamePanel;
import Main.UtilityTool;

import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.lilies.worldX + gp.lilies.screenX;
        int screenY = worldY - gp.lilies.worldY + gp.lilies.screenY;

// khong cho di chuyen man hinh khi ra ngoai ria
        if (gp.lilies.screenX > gp.lilies.worldX) {
            screenX = worldX;
        }
        if (gp.lilies.screenY > gp.lilies.worldY) {
            screenY = worldY;
        }
        int rightOffset = gp.screenWidth - gp.lilies.screenX;
        if (rightOffset > gp.worldWidth - gp.lilies.worldX) {
            screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - gp.lilies.screenY;
        if (bottomOffset > gp.worldHeight - gp.lilies.worldY) {
            screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
