import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class dbConn {
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    String driver = "com.mysql.cj.jdbc.Driver";

    public void dbConnection() {
        String url = "";
        String userId = "";
        String userpwd = "";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userId, userpwd);
            state = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class dbDataInput {

    }

    class dbDataOut {

    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}