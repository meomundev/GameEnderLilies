package Main;

import Entity.Entity;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck (GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        if (entity.direction != null) {
            int entityLeft = entity.worldX + entity.solidArea.x;
            int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
            int entityTop = entity.worldY + entity.solidArea.y;
            int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

            int entityLeftCol = entityLeft / gp.tileSize;
            int entityRightCol = entityRight / gp.tileSize;
            int entityTopRow = entityTop / gp.tileSize;
            int entityBottomRow = entityBottom / gp.tileSize;

            int tileNum1, tileNum2;

            switch (entity.direction) {
                case "up" -> {
                    entityTopRow = (entityTop - entity.speed) / gp.tileSize;
                    // Kiểm tra xem tileNum1 và tileNum2 có hợp lệ không
                    if (isTileValid(entityLeftCol, entityTopRow) && isTileValid(entityRightCol, entityTopRow)) {
                        tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                        tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                        if (isTileValid(tileNum1) && isTileValid(tileNum2)) {
                            if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
                case "down" -> {
                    entityBottomRow = (entityBottom + entity.speed) / gp.tileSize;
                    if (isTileValid(entityLeftCol, entityBottomRow) && isTileValid(entityRightCol, entityBottomRow)) {
                        tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                        tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                        if (isTileValid(tileNum1) && isTileValid(tileNum2)) {
                            if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
                case "left" -> {
                    entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
                    if (isTileValid(entityLeftCol, entityTopRow) && isTileValid(entityLeftCol, entityBottomRow)) {
                        tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                        tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                        if (isTileValid(tileNum1) && isTileValid(tileNum2)) {
                            if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
                case "right" -> {
                    entityRightCol = (entityRight + entity.speed) / gp.tileSize;
                    if (isTileValid(entityRightCol, entityTopRow) && isTileValid(entityRightCol, entityBottomRow)) {
                        tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                        tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                        if (isTileValid(tileNum1) && isTileValid(tileNum2)) {
                            if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
            }
        }
    }

    // Phương thức kiểm tra xem một ô có hợp lệ không
    private boolean isTileValid(int tileNum) {
        return tileNum >= 0 && tileNum < gp.tileManager.tile.length;
    }

    // Phương thức kiểm tra xem một tọa độ ô có hợp lệ không
    private boolean isTileValid(int col, int row) {
        return col >= 0 && col < gp.tileManager.mapTileNum.length &&
                row >= 0 && row < gp.tileManager.mapTileNum[0].length;
    }
    public int checkObject(Entity entity, boolean lilies) {
        int index = 999;
        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                    if (gp.object[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (lilies) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity) {
        boolean contactLilies = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        gp.lilies.solidArea.x = gp.lilies.worldX + gp.lilies.solidArea.x;
        gp.lilies.solidArea.y = gp.lilies.worldY + gp.lilies.solidArea.y;

        switch (entity.direction) {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
        if (entity.solidArea.intersects(gp.lilies.solidArea)) {
            entity.collisionOn = true;
            contactLilies = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.lilies.solidArea.x = gp.lilies.solidAreaDefaultX;
        gp.lilies.solidArea.y = gp.lilies.solidAreaDefaultY;

        return contactLilies;
    }
}
