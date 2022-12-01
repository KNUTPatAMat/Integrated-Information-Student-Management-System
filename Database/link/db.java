import java.sql.Connection;
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

    protected void dbConnection() {
        String url = "jdbc:mysql://localhost:3306/system_java_db?serverTimezone=Asia/Seoul&useSSL=false";
        String userId = "root";
        String userpwd = "Secret Key"; // git .ignore
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

    protected String getUserData(int studentNumber, String dataIndex) {
        String sql = "SELECT * FROM userTB WHERE studentNumber = ?";
        String userData = new String();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNumber);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                userData = rs.getString(dataIndex);
            }
            else {
                userData = "Error";
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
        return userData;
    }
    protected String[] getUserDataRow(int studentNumber) {
        String sql = "SELECT * FROM userTB WHERE studentNumber = ?";
        String[] userDataArray = new String[8];
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNumber);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                userDataArray = new String[] {
                    rs.getString("id"),
                    rs.getString("studentNumber"),
                    rs.getString("name"),
                    rs.getString("userID"),
                    rs.getString("userPwd"),
                    rs.getString("birth"),
                    rs.getString("phone"),
                    rs.getString("sex")
                };
            }
            else {
                userDataArray = new String[] {"Error"};
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
        return userDataArray;
    }

    protected void insertUser(int studentNumber, String name, String userID, String userPwd, String birth, String phone, int sex) {
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

    // Type Int
    protected void updateUser(int studentNumber, String column, int data) {
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
    // Type String
    protected void updateUser(int studentNumber, String column, String data) {
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
        // userTable.insertUser(1111000, "james", "that", "that", "2000-01-01", "010-1234-5678", 0);
        System.out.println(userTable.getUserData(1111010, "name"));
        System.out.println(userTable.getUserData(1111010, "sex"));
        userTable.dbDisconnection();
    }
}