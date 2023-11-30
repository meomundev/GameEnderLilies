package Main;

import Entity.Entity;
import Objects.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Font arial_40;
    Graphics2D g2;
    BufferedImage heartFull, heartHalf, heartEmpty;

    public int commandNum = 0;
    public String currentDialogue = "";
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Entity heart = new Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
    }
    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
        }
// Point state
        if (gp.gameState == gp.pointState) {
            drawPointState();
        }
// Dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
        }
        if (gp.gameState == gp.inventoryState) {
            drawPlayerLife();
            drawInventory();
        }
        if (gp.gameState == gp.gameOverState) {
            drawPlayerLife();
            drawGameOver();
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
    public void drawMessage() {
        int messageX = gp.tileSize / 2;
        int messageY = gp.tileSize * 6;
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 28f));
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null && i < messageCounter.size()) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 100) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawPointState() {
        final int frameX = 0;
        final int frameY = 0;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        int tailX = (frameX + frameWidth) - 26;
        drawSubWindowForCharacterState2(0, 0, gp.tileSize * 18, gp.tileSize * 10);
        drawSubWindowForCharacterState(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        int textX = frameX + 26;
        int textY = gp.tileSize * 2 + 52;
        final int lineHeight = 46;

        g2.drawString("Point", gp.tileSize + 12, gp.tileSize + lineHeight);
        String value;
        value = String.valueOf(gp.lilies.point);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
    }
    public void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setColor(Color.white);
        g2.setFont(arial_40);
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 70, 70);
    }
    public void drawSubWindowForCharacterState(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 20, 20);
    }
    public void drawSubWindowForCharacterState2(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,0, 0);
    }
    public void drawSubWindowForGameOverState2(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 230);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,0, 0);
    }
    public void drawSubWindowForInventoryState(int x, int y, int width, int height) {
        Color fillColor = new Color(0, 0, 0);
//        Color borderColor = new Color(90, 90, 90);

        g2.setColor(fillColor);
        g2.fillRoundRect(x, y, width, height, 20, 20);

//        g2.setColor(borderColor);
//        g2.setStroke(new BasicStroke(4));
//        g2.drawRoundRect(x, y, width, height, 0, 0);
    }
    public void drawSubWindowForInventoryState2(int x, int y, int width, int height) {
        Color fillColor = new Color(30, 30, 30);

        g2.setColor(fillColor);
        g2.fillRoundRect(x, y, width, height, 10, 10);
    }
    public void drawSubWindowForInventoryState3(int x, int y, int width, int height) {
        Color fillColor = new Color(20, 20, 20);

        g2.setColor(fillColor);
        g2.fillRoundRect(x, y, width, height, 20, 20);
    }
    public void drawTitleScreen() {
        if (g2 == null) {
            System.out.println("g2 is null");
            return;
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String title = "Ender Lilies";
        int x = getXForCenterText(title);
        int y = gp.tileSize * 3 - gp.tileSize/2;

        // Shadow of title
        g2.setColor(Color.GRAY);
        g2.drawString(title, x + 5, y + 5);

        // Title
        g2.setColor(Color.WHITE);
        g2.drawString(title, x, y);

        // Create a gray rectangle behind the character
//        g2.setColor(Color.darkGray);
//        g2.fillRect(gp.screenWidth / 2 - (gp.tileSize * 2) / 2, gp.tileSize * 3, gp.tileSize * 2, gp.tileSize * 2);

        // Player image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize;
        g2.drawImage(gp.lilies.right, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        g2.setColor(Color.white);

        String text1 = "Play game";
        x = getXForCenterText(text1);
        y += gp.tileSize * 3 + (gp.tileSize / 2);
        if (commandNum == 0) {
            g2.setColor(new Color(240, 190, 90));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawString(text1, x, y);

        g2.setColor(Color.white);
        String text2 = "Point";
        x = getXForCenterText(text1);
        y += gp.tileSize;
        if (commandNum == 1) {
            g2.setColor(new Color(240, 190, 90));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawString(text2, x, y);
        g2.setColor(Color.white);
        String text3 = "Quit";
        x = getXForCenterText(text1);
        y += gp.tileSize;
        if (commandNum == 2) {
            g2.setColor(new Color(240, 190, 90));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawString(text3, x, y);
        if (commandNum == 3) {
            commandNum = 0;
        }
        if (commandNum == -1) {
            commandNum = 1;
        }
    }
    public void drawCharacterScreen() {
        final int frameX = 0;
        final int frameY = 0;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        int tailX = (frameX + frameWidth) - 26;
        drawSubWindowForCharacterState2(0, 0, gp.tileSize * 18, gp.tileSize * 10);
        drawSubWindowForCharacterState(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        int textX = frameX + 26;
        int textY = gp.tileSize * 2 + 52;
        final int lineHeight = 46;

        g2.drawString("STATUS", gp.tileSize + 12, gp.tileSize + lineHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,30F));
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Str", textX, textY);
        textY += lineHeight;
        g2.drawString("Dex", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += gp.tileSize;
        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("Weapon", textX, textY);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));
        textY += lineHeight / 2;
        g2.drawImage(gp.lilies.currentWeapon.downMove1, textX + 24, textY, null);

        textY = gp.tileSize * 2 + 52;
        String value;
        value = String.valueOf(gp.lilies.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.lilies.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += gp.tileSize;
        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        String text = "Shield";
        textX = getXForAlignToRightText(text, tailX);
        g2.drawString(text, textX, textY);
        textY += lineHeight / 2;
        g2.drawImage(gp.lilies.currentShield.downMove1, tailX - gp.tileSize - 12, textY, null);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,32F));
        g2.drawString("Press \"z\" to go back...", gp.tileSize * 13, gp.tileSize * 9 + gp.tileSize / 2);
    }
    public void drawInventory() {
        int frameX1 = gp.tileSize * 3;
        int frameY1 = gp.tileSize;
        int frameWidth1 = gp.tileSize * 8;
        int frameHeight1 = gp.tileSize * 8 - (gp.tileSize / 2);
        drawSubWindowForCharacterState2(0, 0, gp.tileSize * 18, gp.tileSize * 10);
        drawSubWindowForInventoryState(frameX1, frameY1, frameWidth1, frameHeight1);
        drawSubWindowForInventoryState2(frameX1 + gp.tileSize / 2, frameY1 + gp.tileSize * 2, frameWidth1 - gp.tileSize + 5, frameHeight1 - gp.tileSize * 3 + gp.tileSize / 2 + 5);

// title
        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.drawString("INVENTORY", gp.tileSize * 5 - gp.tileSize / 6, gp.tileSize * 2 + gp.tileSize / 4);
        g2.setColor(Color.WHITE);
// slot
        int slotStartX = frameX1 + gp.tileSize / 2 + 5;
        int slotStartY = frameY1 + gp.tileSize * 2 +5;
        int slotX = slotStartX;
        int slotY = slotStartY;
        int currentSlotX = slotStartX + (gp.tileSize * slotCol);
        int currentSlotY = slotStartY + (gp.tileSize * slotRow);
        int currentSlotWidth = gp.tileSize-5;
        int currentSlotHeight = gp.tileSize-5;

        for (int i = 0; i < gp.lilies.inventory.size(); i++) {
            if (gp.lilies.inventory.get(i) == gp.lilies.currentWeapon || gp.lilies.inventory.get(i) == gp.lilies.currentShield) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, currentSlotWidth, currentSlotHeight, 10, 10);
            }
            if (gp.lilies.inventory.get(i) != null && gp.lilies.inventory.get(i).downMove1 != null) {
                g2.drawImage(gp.lilies.inventory.get(i).downMove1, slotX - 3, slotY - 3, null);
                slotX += gp.tileSize;
                if (i == 6 || i == 13 || i == 20) {
                    slotX = slotStartX;
                    slotY += gp.tileSize;
                }
            }
        }

// description
        int frameX2 = gp.tileSize * 11;
        int frameY2 = gp.tileSize * 4;
        int frameWidth2 = gp.tileSize * 4;
        int frameHeight2 = gp.tileSize * 5 - (gp.tileSize / 2);
        int textX = frameX2 + gp.tileSize / 2;
        int textY = frameY2 + gp.tileSize;
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20f));
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.lilies.inventory.size()) {
            drawSubWindowForInventoryState3(frameX2, frameY2, frameWidth2, frameHeight2);
            for (String line : gp.lilies.inventory.get(itemIndex).description.split("\n")) {
                g2.setColor(Color.white);
                g2.drawString(line, textX, textY);
                textY += gp.tileSize / 2;
            }
        }

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(currentSlotX, currentSlotY, currentSlotWidth, currentSlotHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,32F));
        g2.drawString("Press \"z\" to go back...", gp.tileSize * 13, gp.tileSize * 9 + gp.tileSize / 2);
    }
    public int getItemIndexOnSlot() {
        return slotCol + (slotRow * 7);
    }
    public void drawGameOver() {
        drawSubWindowForGameOverState2(0, 0, gp.tileSize * 18, gp.tileSize * 10);

        int x;
        int y;
        String text = "Game Over";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        g2.setColor(Color.gray);
        x = getXForCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x- 4, y - 4);

        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenterText(text);
        y += gp.tileSize * 2;
        if (commandNum == 0) {
            g2.setColor(new Color(240, 190, 90));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        if (commandNum == 1) {
            g2.setColor(new Color(240, 190, 90));
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawString(text, x, y);
    }
    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
