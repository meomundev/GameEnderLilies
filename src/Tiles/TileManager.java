package Tiles;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        tile = new Tile[25];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        setup(0, "space", true);
        setup(1, "fountain", false);
        setup(2, "outPostLeftTop", false);
        setup(3, "outPostTop", false);
        setup(4, "outPostRightTop", false);
        setup(5, "outPostLeft", false);
        setup(6, "outPostRight", false);
        setup(7, "outPostLeftBot", false);
        setup(8, "outPostBot", false);
        setup(9, "outPostRightBot", false);
        setup(15, "glass", false);
        setup(20, "outPostLeftRight", false);
        setup(21, "outPostLeftRightTop", false);
// ve cau
        setup(10, "outPostTopBridge", false);
        setup(11, "outPostBotBridge", false);
        setup(12, "bridgeTop", false);
        setup(13, "bridge", false);
        setup(14, "bridgeBot", false);
// ve doc
        setup(16, "glassSwap", false);
        setup(17, "glassSwapRight", false);
        setup(18, "poison", false);
        setup(19, "poisonBot", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/tiles/" + imageName + ".png");

            if (inputStream != null) {
                tile[index] = new Tile();
                tile[index].image = ImageIO.read(inputStream);
                tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
                tile[index].collision = collision;
            } else {
                System.err.println("Could not load image: " + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/map.txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] number = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
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

            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
