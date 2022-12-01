import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class db {
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    PreparedStatement pstmt = null;

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

    public void selectStudent(int studentNumber) {
        String sql = "SELECT * FROM userTB WHERE studentNumber = ?";
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
                System.out.println("birth: " + rs.getDate("birth"));
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

    public void insertUser(int studentNumber, String name, String userID, String userPwd, String birth, String phone, int sex) {
        String sql = "INSERT INTO userTB(studentNumber, name, userID, userPwd, birth, phone, sex) VALUES (?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNumber);
            pstmt.setString(2, name);
            pstmt.setString(3, userID);
            pstmt.setString(4, userPwd);
            pstmt.setDate(5, java.sql.Date.valueOf(birth));
            pstmt.setString(6, phone);
            pstmt.setInt(7, sex);
            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateUser(int studentNumber, String column, int data) {
        String sql = "UPDATE user SET ?=? WHERE studentNumber=?";
        pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, column);
            pstmt.setInt(2, data);
            pstmt.setInt(3, studentNumber);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateUser(int studentNumber, String column, String data) {
        String sql = "UPDATE user SET ?=? WHERE studentNumber=?";
        pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, column);
            pstmt.setString(2, data);
            pstmt.setInt(3, studentNumber);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // System.out.println("hello");
        // test code
        db userTable = new db();
        userTable.dbConnection();
        // userTable.selectStudent(1111010);
        userTable.insertUser(1111000, "james", "that", "that", "2000-01-01", "010-1234-5678", 0);
        userTable.selectStudent(1111000);
        userTable.dbDisconnection();
    }
}