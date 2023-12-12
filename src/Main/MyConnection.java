package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/gameel";
        String user = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL!");
            return con;
        } catch (SQLException ex) {
            System.err.println("Failed to connect to MySQL:");
            ex.printStackTrace();
            return null;
        }
    }
}
