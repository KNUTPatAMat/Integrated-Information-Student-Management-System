import com.mysql.cj.conf.DatabaseUrlContainer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Database.link.dbControl;

public class Student {

    dbControl dbC = new dbControl();

    // Student class 생성 시 userTB에 새로운 row 생성 (회원가입 시)ㅓ
    public Student(int studentNumber, String name, String userID, String userPwd, String birth, String phone, int sex, String grade, String major) {
        dbC.insertUser(studentNumber, name, userID, userPwd, birth, phone, sex, grade, major);
    }
    
    //회원가입

    //parameter로 받는 값 모두 list로 변환
    protected void signInData(int studentNumber, String name, String userID, String userPwd1, String userPwd2, String birth, String phone, int sex, String grade, String major)

    public void signIn(int studentNumber, String name, String userID, String userPwd1, String userPwd2, String birth, String phone, int sex, String grade, String major){
        if (isSamePwd(userPwd1, userPwd2)) {
            if (isExitStudentNumber(studentNumber)){
                dbC.insertUser(studentNumber, name, userID, userPwd2, birth, phone, sex, grade, major);
            } else {
                System.out.println("이미 존재하는 학번입니다.");
            }
        } else {
            System.out.println("패스워드가 일치하지 않습니다.");
        }
    }

    //비밀번호확인 일치여부
    protected boolean isSamePwd(String pwd1, String pwd2){
        if (pwd1 == pwd2) {
            return true;
        } else {
            return false;
        }
    }

    //학번 중복 유무
    protected boolean isExitStudentNumber(int studentNumber){
        String studentNumberStr = Integer.toString(studentNumber);
        String[] studentNum = dbC.getUserDataColumn("studentNumber");
        List<String> studentNumList = new ArrayList<>(Arrays.asList(studentNum));
        if (studentNumList.indexOf(studentNumberStr)>=0) { //studentNumList내부에 parameter studentNumber값 존재
            return false;
        } else {
            return true;
        }
    }

    //아이디 조회 후 아이디 패스워드 맞는지 확인
    public boolean login(int studentNumber, String userPwd){
        String[] id =  dbC.getUserDataColumn("studentNumber");
        String[] pwd = dbC.getUserDataColumn("userPwd");
        List<String> idList = new ArrayList<>(Arrays.asList(id));
        List<String> pwdList = new ArrayList<>(Arrays.asList(pwd));
        if (idList.contains(userID)) {
            if () {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void updateInfo(int i, String info) {
        switch (i) {
        case 1:		// 이름 수정
            setName(info);
            break;
    
        case 2:		// 나이 수정
            setAge(Integer.parseInt(info));
            break;
            
        case 3:		// 학번 수정
            setStudentNum(Integer.parseInt(info));
            break;
            
        case 4:		// 전공 수정
            setMajor(info);
            break;
            
        }
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
}