import java.util.Scanner;

import User.*;

public class Main {
   
    Scanner scanner = new Scanner(System.in);
    SignIn signIn = new SignIn();
    LogIn logIn = new LogIn();
    SearchAndUpdate sau = new SearchAndUpdate();

    int userStudentNumber;
    
    // CLI Area====================================================================================================================
    // 초기화면
    public void initMenu() {
       
        clearScreen();
        userStudentNumber = 0;
        System.out.println("-----학적관리 시스템-----");
        System.out.println("1. \t로그인");
        System.out.println("2. \t회원가입");
        System.out.println("3. \t종료");
        int menuNum = scanner.nextInt();
        menuControler(menuNum);
    }
    //로그인 화면
    protected void logInMenu(){
        clearScreen();
        System.out.println("-----로그인-----");
        System.out.print("학번 : \t");
        int studentNumber = scanner.nextInt();
        System.out.print("Pwd : \t");
        String userPwd = scanner.next();
        System.out.println();
        logInMenuControler(studentNumber, userPwd);
    }
    //회원가입 화면
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
        signInMenuControler(studentName, studentNumber, pwd1, pwd2, birth, phone, sex, grade, major);
    }
    //Admin Menu 화면
    protected void adminMenu() {
        clearScreen();
        System.out.println("-----Admin Menu-----");
        System.out.println();
        System.out.println("1. \t전체학생 조회");
        System.out.println("2. \t학생 조회");
        System.out.println("3. \t회원정보 수정");
        System.out.println("4. \t로그아웃");
        System.out.println("5. \t종료");
        int menuNum = scanner.nextInt();
        adminMenuControler(menuNum);
    }
    //User Menu 화면
    protected void userMenu() {
        clearScreen();
        System.out.println("-----User Menu-----");
        System.out.println("계정 : "+userStudentNumber);
        System.out.println("-------------------");
        System.out.println();
        System.out.println("1. \t정보 조회");
        System.out.println("2. \t정보 수정");
        System.out.println("3. \t로그아웃");
        System.out.println("4. \t종료");
        int menuNum = scanner.nextInt();
        userMenuControler(menuNum);
    }

    // 전체학생 출력화면
    protected void viewAlluserMenu() {
        clearScreen();
        viewAllUser();
    }

    // ControlArea====================================================================================================================

    // clear
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
            System.out.println("");
    }

    // menu 선택 흐름제어
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

    //login 성공시 흐름 제어
    protected void logInMenuControler(int studentNumber, String userPwd){
        boolean isLogIn = logIn.logIn(studentNumber, userPwd);
        System.out.println();
        if (isLogIn) {
            userStudentNumber = studentNumber;
            if (userStudentNumber == 1) {
                System.out.println("Admin 님 환영합니다.");
                adminMenu();
            } else {
                System.out.println(userStudentNumber + " 님 환영합니다.");
                userMenu();
            }
        } else {
            System.out.println("로그인에 실패하였습니다. 초기화면으로 돌아갑니다.");
            initMenu();
        }
    }

    //회원가입 흐름제어
    protected void signInMenuControler(String studentName, int studentNumber, String pwd1, String pwd2, String birth, String phone, int sex, String grade, String major){
        boolean isSignInDone = signIn.signInData(studentName, studentNumber, pwd1, pwd2, birth, phone, sex, grade, major);

        if (isSignInDone) {
            System.out.println("회원가입에 성공했습니다. 초기화면에서 로그인해주세요.");
            initMenu();
        } else {
            System.out.println("다시 입력해 주세요.");
            signInMenu();
        }
    }

    protected void searchUser(int studentNum) {
        clearScreen();
        String[] studentData = sau.SeachUser(studentNum);
        System.out.println("이름 : \t\t" + studentData[1]);
        System.out.println("학번 : \t\t" + studentData[2]);
        System.out.println("비밀번호 : \t" + studentData[3]);
        System.out.println("생일 : \t\t" + studentData[4]);
        System.out.println("전화번호 : \t" + studentData[5]);
        System.out.println("성별 : \t\t" + studentData[6]);
        System.out.println("학년 : \t\t" + studentData[7]);
        System.out.println("학과 : \t\t" + studentData[8]);
        System.out.println();
    }

    protected void userUpdateUser(int studentNum){
        clearScreen();
        String[] intDataArr = {"id","sex"};
        String[] strDataArr = {"studentName", "userPwd", "birth", "phone", "grade", "major"};
        for (String string : intDataArr){
            System.out.println(string);
        }
        for (String string : strDataArr){
            System.out.println(string);
        }
        System.out.println("수정하시려는 항목을 입력하세요");
        String column = scanner.next();
        for (String string : intDataArr) {
            if (string.equals(column)){
                System.out.println("수정하시려는 내용을 입력하세요");
                int data = scanner.nextInt();
                sau.updateUser(studentNum, column, data);
            }
        }
        for (String string : strDataArr) {
            if (string.equals(column)){
                System.out.println("수정하시려는 내용을 입력하세요");
                String data = scanner.next();
                sau.updateUser(studentNum, column, data);
            }
        }
        System.out.println(column+" 정보를 변경하였습니다.");
    }

    protected void adminSearchUser(){
        clearScreen();
        System.out.println("찾으시려는 학번을 입력하세요.");
        int studentNum = scanner.nextInt();
        searchUser(studentNum);
    }
    
    protected void adminUpdateUser(){
        clearScreen();
        System.out.println("수정하시려는 학번을 입력하세요");
        int studentNum = scanner.nextInt();
        userUpdateUser(studentNum);
    }

    //
    protected void adminMenuControler(int index) {
        if ((index >= 1) || (index <= 5)){
            switch (index) {
                case 1: //전체학생 출력
                    viewAlluserMenu();
                    System.out.println("\n1. \t나가기");
                    int menuNum = scanner.nextInt();
                    if (menuNum == 1){
                        adminMenu();
                    }
                    break;
                case 2: //선택학생 출력
                    adminSearchUser();
                    System.out.println("\n1. \t나가기");
                    menuNum = scanner.nextInt();
                    if (menuNum == 1){
                        adminMenu();
                    }
                    break;
                case 3: // 정보 수정
                    adminUpdateUser();
                    System.out.println("\n1. \t나가기");
                    menuNum = scanner.nextInt();
                    if (menuNum == 1){
                        adminMenu();
                    }
                    break;
                case 4:
                    initMenu();
                    break;
                case 5:
                    System.out.println("종료합니다");
                    break;
            }
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다.");
        }
    }

    protected void userMenuControler(int index) {
        if ((index >= 1) || (index <= 4)){
            switch (index) {
                case 1: //정보조회
                    searchUser(userStudentNumber);
                    System.out.println("\n1. \t나가기");
                    int menuNum = scanner.nextInt();
                    if (menuNum == 1){
                        userMenu();
                    }
                    break;
                case 2: //정보수정
                    userUpdateUser(userStudentNumber);
                    menuNum = scanner.nextInt();
                    System.out.println("\n1. \t나가기");
                    if (menuNum == 1){
                        userMenu();
                    }
                    break;
                case 3: //로그아웃
                    initMenu();
                    break;
                case 4:
                    System.out.println("종료합니다");
                    break;
            }
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다.");
        }
    }

    // 전체학생 출력
    protected void viewAllUser() {
        String[][] allUserArr = sau.searchAllUser();
        int allUserArrSize = allUserArr.length;
        for (int data=1; data<allUserArrSize; data++) {
            System.out.println("이름 : \t\t" + allUserArr[data][1]);
            System.out.println("학번 : \t\t" + allUserArr[data][2]);
            System.out.println("비밀번호 : \t" + allUserArr[data][3]);
            System.out.println("생일 : \t\t" + allUserArr[data][4]);
            System.out.println("전화번호 : \t" + allUserArr[data][5]);
            System.out.println("성별 : \t\t" + allUserArr[data][6]);
            System.out.println("학년 : \t\t" + allUserArr[data][7]);
            System.out.println("학과 : \t\t" + allUserArr[data][8]);
            System.out.println();
            
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        // main.setFrame(main);
        main.initMenu();
    }
}