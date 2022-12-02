package database.link;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class dbCon {
    protected Connection conn = null;
    protected Statement state = null;
    protected ResultSet rs = null;
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected PreparedStatement pstmt = null;

    // Connection Control
    public void dbConnection() {
        final String url = "jdbc:mysql://localhost:3306/system_java_db?serverTimezone=Asia/Seoul&useSSL=false";
        final String userId = "root";
        final String userpwd = "seokjin0827!@"; // git .ignore
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