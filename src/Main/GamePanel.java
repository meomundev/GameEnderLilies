package Main;

import Entity.Entity;
import Entity.Lilies;
import Objects.Heart;
import Objects.SuperObject;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

// FPS of game
    int FPS = 60;


    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    KeyHandler keyHandler = new KeyHandler();
    public UI ui = new UI(this);
    AssetSetter aSetter = new AssetSetter(this);
// entity
    public Lilies lilies = new Lilies(this, keyHandler);
    public SuperObject[] object = new SuperObject[10];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    public ArrayList<Entity> entityList = new ArrayList<>();
// game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;

    public GamePanel() {
        keyHandler.gp = this;
        tileManager = new TileManager(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich thuoc uu tien
        this.setBackground(Color.black); //set mau nen
        this.setDoubleBuffered(true); //tao ra bộ đệm thu 2 de tranh hien tuong nhap nhay giup game muot ma hon
        this.addKeyListener(keyHandler); //them xu li phim
        this.setFocusable(true); //nhan su tap trung cua ban phim, nhu la nhan enter, nhan tab
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
        double drawInterval = (double) 1000000000 /FPS; //khoang thoi gian giua cac khung hinh 0.016666s(1000000000 nano giay = 1 giay)
        double delta = 0; //thoi gian con lai truoc khi cap nhat khung hinh tiep theo
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) { //khi game thread ton tai se thuc hien [1.Update: vi tri charater] [2.Draw: ve man hinh khi nhan vat da update]
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint(); //dung de goi phuong thuc paintComponent
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
// update npc
            for (Entity value : npc) {
                if (value != null) {
                    value.update();
                }
            }
// update lilies
            lilies.update();
// update quai vat
            for (Entity entity : monster) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else if (gameState == playState) {  // Đảm bảo rằng game ở trạng thái playState
            tileManager.draw(g2);
            for (SuperObject superObject : object) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            lilies.draw(g2);
            for (Entity value : npc) {
                if (value != null) {
                    value.draw(g2);
                }
            }
            entityList.clear();

            ui.draw(g2);
        }
        g2.dispose();
    }
}
