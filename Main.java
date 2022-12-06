import User.*;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    SignIn signIn = new SignIn();
    LogIn logIn = new LogIn();
    SearchAndUpdate sau = new SearchAndUpdate();

    int userStudentNumber;

    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
          System.out.println("");
      }

    public void initMenu() {
        clearScreen();
        System.out.println("-----학적관리 시스템-----");
        System.out.println("1. \t로그인");
        System.out.println("2. \t회원가입");
        System.out.println("3. \t종료");
        int menuNum = scanner.nextInt();
        menuControler(menuNum);
    }

    protected void menuControler(int index) {
        if ((index >= 1) || (index <= 3)){
            switch (index) {
                case 1:
                    logInMenu();
                    break;
                case 2:
                    signInMenu();
                    break;
                case 3:
                    System.out.println("종료합니다");
                    break;
            }
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다.");
        }
    }

    protected void logInMenu(){
        clearScreen();
        System.out.println("-----로그인-----");
        System.out.print("학번 : \t");
        int studentNumber = scanner.nextInt();
        System.out.print("Pwd : \t");
        String userPwd = scanner.next();
        System.out.println();
        boolean isLogIn = logIn.logIn(studentNumber, userPwd);
        System.out.println();
        if (isLogIn) {
            userStudentNumber = studentNumber;
            System.out.println(userStudentNumber + " 님 환영합니다.");
        } else {
            System.out.println("로그인에 실패하였습니다. 초기화면으로 돌아갑니다.");
            initMenu();
        }
    }

    protected void signInMenu(){
        clearScreen();
        System.out.println("-----회원가입-----");
        System.out.print("이름 : \t\t\t");
        String studentName = scanner.next();
        System.out.print("학번 : \t\t\t");
        int studentNumber = scanner.nextInt();
        System.out.print("비밀번호 : \t\t");
        String pwd1 = scanner.next();
        System.out.print("비밀번호 확인 : \t");
        String pwd2 = scanner.next();
        System.out.print("생일 : \t\t\t");
        String birth = scanner.next();
        System.out.print("휴대전화 : \t\t");
        String phone = scanner.next();
        System.out.print("성별(남:0 여:1): \t");
        int sex = scanner.nextInt();
        System.out.print("학년 : \t\t\t");
        String grade = scanner.next();
        System.out.print("학과 : \t\t\t");
        String major = scanner.next();
        System.out.println();

        boolean isSignInDone = signIn.signInData(studentName, studentNumber, pwd1, pwd2, birth, phone, sex, grade, major);

        if (isSignInDone) {
            System.out.println("회원가입에 성공했습니다. 초기화면에서 로그인해주세요.");
            initMenu();
        } else {
            System.out.println("다시 입력해 주세요.");
            signInMenu();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.initMenu();

        
    }
    
}
