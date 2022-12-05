package Database.link;

public class dbControl extends dbCon {
    // Get Data
    protected int getDataNum() {
        String sql = "SELECT COUNT(*) FROM userTB";
        int dataNumber = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                dataNumber = rs.getInt(1);
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
        return dataNumber;
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
    public String[] getUserDataRow(int studentNumber) {
        String sql = "SELECT * FROM userTB WHERE studentNumber = ?";
        String[] userDataArray = new String[8];
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNumber);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                userDataArray = new String[] {
                    rs.getString("id"),
                    rs.getString("studentName"),
                    rs.getString("studentNumber"),
                    rs.getString("userPwd"),
                    rs.getString("birth"),
                    rs.getString("phone"),
                    rs.getString("sex"),
                    rs.getString("grade"),
                    rs.getString("major")
                };
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
    public String[] getUserDataColumn(String index) {
        int dataNum = this.getDataNum();
        int point = 0;
        String sql = "SELECT * FROM userTB";
        String[] userDataArray = new String[dataNum];
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            point = 0;
            while (rs.next()) {
                userDataArray[point++] = rs.getString(index);
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
    // Update Data
    protected void updateUser(int studentNumber, String column, int data) { // Type Int
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
    protected void updateUser(int studentNumber, String column, String data) { // Type String
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
    // Insert Data
    protected void insertUser(String studentName,int studentNumber, String userID, String userPwd, String birth, String phone, int sex, String grade, String major) {
        String sql = "INSERT INTO userTB(studentName, studentNumber, userPwd, birth, phone, sex, grade, major) VALUES (?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentName);
            pstmt.setInt(2, studentNumber);
            pstmt.setString(3, userPwd);
            pstmt.setDate(4, java.sql.Date.valueOf(birth));
            pstmt.setString(5, phone);
            pstmt.setInt(6, sex);
            pstmt.setString(7, grade);
            pstmt.setString(8, major);
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

    public static void main(String[] args) {
        // System.out.println("hello");
        // test code
        dbControl userTable = new dbControl();
        userTable.dbConnection();

        String[] abc = userTable.getUserDataRow(1826015);
        for (String string : abc) {
            System.out.println(string);
        }

        System.out.println(userTable.getDataNum());
        String[] x = userTable.getUserDataColumn("studentName");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        userTable.dbDisconnection();
    }
}
