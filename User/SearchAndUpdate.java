package User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Database.link.dbControl;


public class SearchAndUpdate {
    dbControl userTable = new dbControl();
    //전체학생 조회
    public String[][] searchAllUser(){
        userTable.dbConnection();

        String[] studentNumberArr = userTable.getGetUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNumberArr));
        String[][] studentDataArr = {{}};
        List<String[]> studentDataList = new ArrayList<>(Arrays.asList(studentDataArr));
        for (String string : studentNumList) {
            studentDataList.add(userTable.getGetUserDataRow(Integer.parseInt(string)));
        }
        int arrListSize = studentDataList.size();
        studentDataArr = studentDataList.toArray(new String[arrListSize][]);
        userTable.dbDisconnection();
        return studentDataArr;
    }

    //개인정보 조회
    public String[] SeachUser(int studentNumber){
        String[] userData;
        userTable.dbConnection();
        userData = userTable.getGetUserDataRow(studentNumber);
        userTable.dbDisconnection();
        return userData;
    }

    //개인정보 업데이트
    public boolean updateUser(int studentNumber, String column, int data){
        boolean result = false;
        userTable.dbConnection();
        String[] intDataArr = {"sex"};
        for (String string : intDataArr) {
            if (string.equals(column)){
                userTable.getUpdateUser(studentNumber, column, data);
                result = true;
            }
        }
        userTable.dbDisconnection();
        if (result) {
            System.out.println("is Int Data?\t...OK");
        } else {
            System.out.println("is Int Data?\t...ERROR");
        }
        return result;
    }

    public boolean updateUser(int studentNumber, String column, String data){
        boolean result = false;
        userTable.dbConnection();
        String[] intDataArr = {"studentName", "userPwd", "birth", "phone", "grade", "major"};
        for (String string : intDataArr) {
            if (string.equals(column)){
                userTable.getUpdateUser(studentNumber, column, data);
                result = true;
            }
        }
        if (result) {
            System.out.println("is String Data?\t...OK");
        } else {
            System.out.println("is String Data?\t...ERROR");
        }
        userTable.dbDisconnection();
        return result;
    }
    public static void main(String[] args) {
        SearchAndUpdate sau = new SearchAndUpdate();
        sau.updateUser(1826015, "phone", "010-5678-1234");
    }
}
