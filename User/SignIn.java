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
                    result = true;
                }
            }
        }
        userTable.dbDisconnection();
        if (result) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
        return result;
    }
    //전체 값 입력했는지 ?
    protected boolean isDataFull() {
        boolean result = true;
        for (String string : signInDataArr) {
            if (string.equals("Null")){
                result = false;
            }
        }
        if (result) {
            System.out.println("전체 Data 입력\t\t...OK");
        }else{
            System.out.println("전체 Data 입력\t\t...ERROR");
        }
        return result;
    }
    //학번 중복 유무
    protected boolean isExitStudentNumber(){
        boolean result = false;
        String[] studentNum = userTable.getGetUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNum));
        if ((studentNumList.indexOf(signInDataArr[0]))>=0) { //studentNumList내부에 parameter studentNumber값 존재
            System.out.println("학번 미중복\t\t...ERROR");
        } else {
            System.out.println("학번 미중복\t\t...OK");
            result =  true;
        }
        return result;
    }
    //비밀번호 확인을 제대로 입력했는지 ?
    protected boolean isSamePwd(){
        if (signInDataArr[2].equals(signInDataArr[3])) {
            System.out.println("비밀번호 일치\t\t...OK");
            return true;
        } else {
            System.out.println("비밀번호 일치\t\t...ERROR");
            return false;
        }
    }
    //패스워드 패턴에 부합하는지 + 비밀번호 확인
    protected boolean pwdPattern() {
        boolean result = false;
        String pass = signInDataArr[2];
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher = passPattern1.matcher(pass);
        if(!passMatcher.find()) {
            System.out.println("비밀번호 생성 규칙\t...ERROR");
        } else {
            System.out.println("비밀번호 생성 규칙\t...OK");
            if (isSamePwd()) {
                result = true;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SignIn si = new SignIn();
        si.signInData("문경호",1826015,"Ansrudgh1!","Ansrudgh1!","1999-08-17","010-8975-3966",0,"2","컴퓨터공학과");
    }
}
