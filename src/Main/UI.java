package Main;

import Objects.Heart;
import Objects.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40;
    Graphics2D g2;
    BufferedImage heartFull, heartHalf, heartEmpty;

    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        SuperObject heart = new Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2; // Khởi tạo g2 ở đây

        // Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // Play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // Full life
        while (i < gp.lilies.maxLife / 2) {
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // Current life
        while (i < gp.lilies.life) {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gp.lilies.life) {
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        if (g2 == null) {
            System.out.println("g2 is null");
            return;
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String title = "Ender Lilies";
        int x = getXForCenterText(title);
        int y = gp.tileSize * 3;

        // Shadow of title
        g2.setColor(Color.GRAY);
        g2.drawString(title, x + 5, y + 5);

        // Title
        g2.setColor(Color.WHITE);
        g2.drawString(title, x, y);

        // Create a gray rectangle behind the character
        g2.setColor(Color.darkGray);
        g2.fillRect(gp.screenWidth / 2 - (gp.tileSize * 2) / 2, gp.tileSize * 4, gp.tileSize * 2, gp.tileSize * 2);

        // Player image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize;
        g2.drawImage(gp.lilies.right, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        g2.setColor(Color.white);

        String text1 = "Play game";
        x = getXForCenterText(text1);
        y += gp.tileSize * 3 + (gp.tileSize / 2);
        g2.drawString(text1, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        String text2 = "Quit";
        x = getXForCenterText(text1);
        y += gp.tileSize;
        g2.drawString(text2, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        if (commandNum == 2) {
            commandNum = 0;
        }
        if (commandNum == -1) {
            commandNum = 1;
        }
    }

    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
