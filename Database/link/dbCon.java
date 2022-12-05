package Database.link;

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
    private String databaseName = "StudentManagement";

    // Connection Control
    public void dbConnection() {
        final String url = "jdbc:mysql://localhost:3306/"+databaseName+"?serverTimezone=Asia/Seoul&useSSL=false";
        final String userId = "root";
        final String userpwd = "Ansrudgh1!"; // git .ignore
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userId, userpwd);
            // state = conn.createStatement();
            System.out.println("Connection Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dbDisconnection() {
        try {
            conn.close();
            System.out.println("Disconnection Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}