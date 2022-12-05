import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Database.link.dbControl;

public class SignIn {
    protected String[] signInDataArr;

    dbControl dbControl = new dbControl();

    protected void signInData(int studentNumber, String name, String userID, String userPwd1, String userPwd2, String birth, String phone, int sex, String grade, String major){
        String sexStr ;
        if (sex == 0){
            sexStr = "male";
        } else {
            sexStr = "female";
        }
        signInDataArr = new String[] {Integer.toString(studentNumber), name, userPwd1, userPwd2, birth, phone, sexStr, grade, major};
    }

    protected boolean isSamePwd(){
        if (signInDataArr[3] == signInDataArr[4]) {
            System.out.println("비밀번호 일치");
            return true;
        } else {
            System.out.println("비밀번호 불일치");
            return false;
        }
    }

    //학번 중복 유무
    protected boolean isExitStudentNumber(){
        String[] studentNum = dbControl.getUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNum));
        if (studentNumList.indexOf(signInDataArr[0])>=0) { //studentNumList내부에 parameter studentNumber값 존재
            System.out.println("학번 중복");
            return false;
        } else {
            System.out.println("학번 미중복");
            return true;
        }
    }
}
