package Main;

import Entity.Entity;
import Entity.Lilies;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 24; //size nguyen ban 24x24 pixels
    final int scale = 3; //ti le
    public final int tileSize = originalTileSize * scale; //size that 72x72 pixels
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol; // 1296
    public final int screenHeight = tileSize * maxScreenRow; // 720

// World
    public final int maxWorldCol = maxScreenCol * 3; // 36
    public final int maxWorldRow = maxScreenRow * 3; // 20

    public final int worldWidth = maxWorldCol * tileSize; // 2592
    public final int worldHeight = maxWorldRow * tileSize; // 1440
    public final int maxMap = 10;
    public int currentMap = 0;

// FPS of game
    int FPS = 60;
    Thread gameThread;
// khoi tao
    public TileManager tileManager = new TileManager(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    KeyHandler keyHandler = new KeyHandler();
    public UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);
// entity
    public Lilies lilies = new Lilies(this, keyHandler);
    public Entity[][] object = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monster = new Entity[maxMap][20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();
// game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int dialogueState = 2;
    public final int characterState = 3;
    public final int inventoryState = 4;
    public final int gameOverState = 6;
    public final int pointState = 7;
    public final int dialogueStateNextMap = 8;
    public final int gameWin = 9;
    public final int gameMenu = 10;
    public final int gameTutorial = 11;

    public GamePanel() {
        keyHandler.gp = this;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich thuoc uu tien
        this.setBackground(Color.black); //set mau nen
        this.setDoubleBuffered(true); //tao ra bộ đệm thu 2 de tranh hien tuong nhap nhay giup game muot ma hon
        this.addKeyListener(keyHandler); //them xu li phim
        this.setFocusable(true); //nhan su tap trung cua ban phim, nhu la nhan enter, nhan tab
    }

    public void retry() {
        lilies.setDefaultPositions();
        if (currentMap == 0) {
            lilies.setDefaultValues();
            aSetter.setMonster();
            aSetter.setNPC();
        } else if (currentMap == 1) {
            lilies.resetMonsterIsDeadMap2();
            lilies.setDefaultValuesNextMap();
            aSetter.setMonster();
        }
    }
    public void restart() {
        currentMap = 0;
        lilies.setDefaultValues();
        lilies.setDefaultPositions();
        lilies.resetLife();
        lilies.resetMonsterIsDeadMap1();
        aSetter.setMonster();
        aSetter.setNPC();
    }
    public void setUpGame () {
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObjects();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //bat dau thuc thi ma trong phuong thuc run
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; //khoang thoi gian giua cac khung hinh 0.016666s(1000000000 nano giay = 1 giay)
        double delta = 0; //thoi gian con lai truoc khi cap nhat khung hinh tiep theo
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) { //khi game thread ton tai se thuc hien [1.Update: vi tri charater] [2.Draw: ve man hinh khi nhan vat da update]
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            if(delta >= 1) {
                update();
                repaint(); //dung de goi phuong thuc paintComponent
                delta--;
            }
            lastTime = currentTime;
        }
    }
    public void update() {
        if (gameState == playState) {
// update npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
// update lilies
            lilies.update();
// update quai vat
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive) {
                        monster[currentMap][i].update();
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    } else if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        }
        else if (gameState == pointState) {
            ui.draw(g2);
        }
        else if (gameState == playState) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            if (this.object != null) {
                for (int i = 0; i < object[1].length; i++) {
                    if (object[currentMap][i] != null) {
                        entityList.add(object[currentMap][i]);
                    }
                }
            }
            for (Entity value : projectileList) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    if (o1 == null && o2 == null) {
                        return 0;
                    } else if (o1 == null) {
                        return 1;
                    } else if (o2 == null) {
                        return -1;
                    }

                    if (o1.worldX < o2.worldX) {
                        return -1;
                    } else if (o1.worldX > o2.worldX) {
                        return 1;
                    } else {
                        return Integer.compare(o1.worldY, o2.worldY);
                    }
                }
            });

            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            entityList.clear();
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == dialogueState) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == dialogueStateNextMap) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == characterState) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == inventoryState) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == gameOverState) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == gameWin) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == gameMenu) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        else if (gameState == gameTutorial) {
            tileManager.draw(g2);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < object[1].length; i++) {
                if (object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            lilies.draw(g2);
            ui.draw(g2);
        }
        g2.dispose();
    }
}
