package database.link;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class dbCon {
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    PreparedStatement pstmt = null;

    // Connection Control
    protected void dbConnection() {
        String url = "jdbc:mysql://localhost:3306/system_java_db?serverTimezone=Asia/Seoul&useSSL=false";
        String userId = "root";
        String userpwd = ""; // git .ignore
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userId, userpwd);
            // state = conn.createStatement();
            System.out.println("Connection Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void dbDisconnection() {
        try {
            conn.close();
            System.out.println("Disconnection Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}