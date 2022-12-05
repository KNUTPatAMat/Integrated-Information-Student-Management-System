package User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Database.link.dbControl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignIn {
    dbControl userTable = new dbControl();
    public String[] signInDataArr;

    public boolean signInData(String name, int studentNumber, String userPwd1, String userPwd2, String birth, String phone, int sex, String grade, String major){
        userTable.dbConnection();
        boolean result = false;
        String sexStr ;
        if (sex == 0){
            sexStr = "male";
        } else {
            sexStr = "female";
        }
        signInDataArr = new String[] {Integer.toString(studentNumber), name, userPwd1, userPwd2, birth, phone, sexStr, grade, major};
        
        if (isDataFull()) {
            if (isExitStudentNumber()) {
                if (pwdPattern()) {
                    userTable.getInsertUser(name, studentNumber, userPwd1, birth, phone, sex, grade, major);
                    System.out.println("회원가입 성공");
                    result = true;
                } else {
                    System.out.println("회원가입 실패");
                }
            } else {
                System.out.println("회원가입 실패");
            }
        } else {
            System.out.println("회원가입 실패");
        }
        userTable.dbDisconnection();
        return result;
    }
    //전체 값 입력했는지 ?
    protected boolean isDataFull() {
        boolean result = true;
        for (String string : signInDataArr) {
            if (string == "Null"){
                result = false;
            }
        }
        return result;
    }
    //학번 중복 유무
    protected boolean isExitStudentNumber(){
        String[] studentNum = userTable.getGetUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNum));
        if ((studentNumList.indexOf(signInDataArr[0]))>=0) { //studentNumList내부에 parameter studentNumber값 존재
            System.out.println("학번 중복");
            return false;
        } else {
            System.out.println("학번 미중복");
            return true;
        }
    }
    //비밀번호 확인을 제대로 입력했는지 ?
    protected boolean isSamePwd(){
        if (signInDataArr[2] == signInDataArr[3]) {
            System.out.println("비밀번호 일치");
            return true;
        } else {
            System.out.println("비밀번호 불일치");
            return false;
        }
    }
    //패스워드 패턴에 부합하는지 + 비밀번호 확인
    protected boolean pwdPattern() {
        String pass = signInDataArr[2];
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher = passPattern1.matcher(pass);
        if(!passMatcher.find()) {
            System.out.println("영문 숫자 특수기호 조합 8자리 이상이 아닙니다.");
            return false;
        } else {
            if (isSamePwd()) {
                return true;
            } else {
                return false;
            }
        }
    }
    public static void main(String[] args) {
        SignIn signIn = new SignIn();
        signIn.signInData("문경호", 1826015, "Ansrudgh1!", "Ansrudgh1!", "1999-08-17", "010-8975-3966", 0, "2", "컴퓨터공학과");
        signIn.signInData("문경호", 1826015, "Ansrudgh1!", "Ansrudgh1!", "1999-08-17", "010-8975-3966", 0, "2", "컴퓨터공학과");
        signIn.signInData("허인영", 1234567, "Ansrudgh", "Ansrudgh", "1999-08-17", "010-8975-3966", 0, "2", "컴퓨터공학과");
        signIn.signInData("허인영", 1234567, "Gjdlsdud1!", "Gjdlsdud1!", "1999-08-17", "010-8975-3966", 0, "2", "컴퓨터공학과");
        signIn.signInData("안기모", 5678909, "Ansrudgh1!@#", "Ansrudgh1!", "1999-08-17", "010-8975-3966", 0, "2", "컴퓨터공학과");
    }
}
