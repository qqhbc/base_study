package jdbc.util;

import Thread.chapter4.ParkCount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtils {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/bas";
    static final String USER = "root";
    static final String PASSWORD = "123456";
    public static Connection getConnection() {
        try {
            //加载驱动
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("连接出错啦！");
        }
    }
}
