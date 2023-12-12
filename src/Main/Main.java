package Main;

import Main.User.LogIn;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = MyConnection.getConnection();

        if (connection != null) {
            LogIn logIn = new LogIn(null);
            if (logIn.loginSuccessful()) {
                JFrame window = new JFrame();
                GamePanel gamePanel = new GamePanel();
                gamePanel.setUpGame();
                gamePanel.startGameThread();

                window.add(gamePanel);
                window.setTitle("Ender Lilies"); // dat ten cho tro choi
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // tat window khi nhan X
                window.setResizable(false); // khong cho chinh sua kich thuoc window
                window.setVisible(true); // dung de hien thi window
                window.pack(); // hien thi window vua du
            }
        }
    }
}
