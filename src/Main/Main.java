package Main;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();

        window.add(gamePanel);
        window.setTitle("Ender Lilies"); //dat ten cho tro choi
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //tat window khi nhan X
        window.setResizable(false); //khong cho chinh sua kich thuoc window
        window.setLocationRelativeTo(null); //de window vao giua khi chay
        window.setVisible(true); //dung de hien thi window
        window.pack(); //hien thi window vua du
    }
}