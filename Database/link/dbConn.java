import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class dbConn {
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";

    public void dbConnection() {
        String url = "jdbc:mysql://localhost:3306/system_java_db?serverTimezone=Asia/Seoul&useSSL=false";
        String userId = "root";
        String userpwd = "secret key"; // git .ignore

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

    public void selectStudentData(int studentNumber) {
        String sql = "select * from user where studentNumber = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNumber);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("student number: " + rs.getInt("studentNumber"));
                System.out.println("student name: " + rs.getString("name"));
                System.out.println("user ID: " + rs.getString("userID"));
                System.out.println("user password: " + rs.getString("userPwd"));
                System.out.println("birth: " + rs.getTimestamp("birth"));
                System.out.println("phone number: " + rs.getString("phone"));
                System.out.println("sex: " + rs.getInt("sex"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
    }

    public static void main(String[] args) {
        System.out.println("hello");
        dbConn con = new dbConn();
        con.dbConnection();
        con.selectStudentData(1111010);
        con.dbDisconnection();
    }
}