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
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/map/mapTest.txt", 0);
        loadMap("/map/map2.txt", 1);
    }

    public void getTileImage() {
// ve nuoc
        setup(0, "water1", true);
        setup(1, "fountain", false);
        setup(2, "waterWithGroundTop", true);
        setup(3, "waterWithGroundBot", true);
        setup(4, "waterWithGroundLeft", true);
        setup(5, "waterWithGroundRight", true);
        setup(6, "waterWithGroundTopLeftSmall", true);
        setup(7, "waterWithGroundTopRightSmall", true);
        setup(8, "waterWithGroundBotLeft", true);
        setup(9, "waterWithGroundBotRight", true);
        setup(11, "waterWithGroundBotLeftSmall", true);
        setup(12, "waterWithGroundBotRightSmall", true);
        setup(15, "waterWithGroundTopUpdate", true);
        setup(16, "waterWithGroundBotUpdate", true);
        setup(17, "waterWithRock", true);
        setup(21, "waterWithGroundTopLeft", true);
        setup(22, "waterWithGroundTopRight", true);
        setup(23, "highGroundGlassTop", true);
        setup(24, "highGroundGlassTopRight", true);
        setup(25, "highGroundGlassTopWaterBot", true);
        setup(26, "highGroundGlassTopAndBot", true);
        setup(27, "highGroundGlassBot", true);
        setup(28, "highGround", true);
        setup(29, "highGroundFar", true);
        setup(30, "highGroundGlassTopFar", true);
        setup(31, "highGroundGlassBotFar", true);
        setup(32, "highGroundGlassTopLeftBig", true);
        setup(33, "highGroundGlassRightLeftBig", true);
        setup(34, "highGroundGlassLeftBig", true);
        setup(35, "highGroundGlassBotRightLeftBig", true);
        setup(36, "highGroundGlassTopSmallLeftBig", true);
        setup(37, "highGroundGlassLeftFar", true);
        setup(38, "highGroundGlassLeftBotSmallFar", true);
        setup(39, "highGroundGlassLeftTopSmallFar", true);
        setup(40, "highGroundWaterBot", true);
        setup(41, "highGroundWaterBotRight", true);
        setup(42, "highGroundWaterRight", true);
        setup(43, "highGroundGlassTopSmallLeftBigWaterRight", true);
        setup(44, "highGroundGlassTopSmallWaterRight", true);
        setup(45, "stair", false);
// ve dat
        setup(10, "ground", false);
// ve cau
        setup(20, "bridge", false);
        setup(13, "bridge", false);
        setup(14, "bridgeBot", false);
// ve doc
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
    public void loadMap(String fileMap, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(fileMap);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] number = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[map][col][row] = num;
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
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

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

            if (worldX + gp.tileSize > gp.lilies.worldX - gp.lilies.screenX && worldX - gp.tileSize < gp.lilies.worldX + gp.lilies.screenX && worldY + gp.tileSize > gp.lilies.worldY - gp.lilies.screenY && worldY - gp.tileSize < gp.lilies.worldY + gp.lilies.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
