package User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Database.link.dbControl;

public class LogIn {
    dbControl userTable = new dbControl();
    //학번 존재하는지 확인
    protected boolean isExitStudentNumber(int studentNumber){
        String[] studentNum = userTable.getGetUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNum));
        if ((studentNumList.indexOf(Integer.toString(studentNumber)))>=0) { //studentNumList내부에 parameter studentNumber값 존재
            System.out.println("학번 존재");
            return true;
        } else {
            System.out.println("학번 없음");
            return false;
        }
    }
    
    protected boolean isRightPwd(int studentNumber, String pwd){
        String studentInfo = userTable.getGetUserData(studentNumber, "userPwd");
        if (studentInfo.equals(pwd)){
            System.out.println("비밀번호 일치");
            return true;
        } else {
            System.out.println("비밀번호 불일치");
            return false;
        }
    }

    public boolean logIn(int studentNumber, String pwd){
        userTable.dbConnection();
        boolean result = false;
        if (isExitStudentNumber(studentNumber)) {
            if (isRightPwd(studentNumber, pwd)){
                result = true;
            }
        }
        if (result) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }
        userTable.dbDisconnection();
        return result;
    }
    public static void main(String[] args) {
        LogIn login = new LogIn();
        login.logIn(1826015, "Ansrudgh1!");
    }
}
