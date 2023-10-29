package Main;

import Entity.Lilies;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 24; //size nguyen ban 24x24 pixels
    final int scale = 3; //ti le
    public final int tileSize = originalTileSize * scale; //size that 72x72 pixels
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol; // 24*3*18 = 1296
    public final int screenHeight = tileSize * maxScreenRow; // 24*3*10 = 720

// World
    public final int maxWorldCol = maxScreenCol * 3;
    public final int maxWorldRow = maxScreenRow * 3;

    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

// FPS of game
    int FPS = 60;

// player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    KeyHandler keyHandler = new KeyHandler();
    public Lilies lilies = new Lilies(this, keyHandler);
    public UI ui = new UI(this);

// game state
    public int gameState;
    public final int titleState = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //kich thuoc uu tien
        this.setBackground(Color.black); //set mau nen
        this.setDoubleBuffered(true); //tao ra bộ đệm thu 2 de tranh hien tuong nhap nhay giup game muot ma hon
        this.addKeyListener(keyHandler); //them xu li phim
        this.setFocusable(true); //nhan su tap trung cua ban phim, nhu la nhan enter, nhan tab
    }

    public void setUpGame () {
        gameState = titleState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //bat dau thuc thi ma trong phuong thuc run
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; //khoang thoi gian giua cac khung hinh 0.016666s(1000000000 nano giay = 1 giay)
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
        lilies.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //dao bao viec ve mac dinh duoc thuc hien

        Graphics2D g2 = (Graphics2D)g;

// title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        tileManager.draw(g2); // de dong nay truoc lilies de no duoc ve ra truoc
        lilies.draw(g2);
        ui.draw(g2);

        g2.dispose(); //loai bo graphics2D
    }
}
