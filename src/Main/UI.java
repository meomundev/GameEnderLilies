package Main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Font arial_40;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {
// title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
//        g2.drawString();
    }

    public void drawTitleScreen() {
    }
}
