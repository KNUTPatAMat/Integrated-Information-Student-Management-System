package User;
import Database.link.dbControl;

public class StudentManager {

	dbControl dbCont = new dbControl();
    
	public static void main(String[] args) {
		
		Student hong = new Student(20123487, "홍길동", "hong1234", "ghdrlfehd", "2000-01-01", "010-1234-1234", 1, "2", "영극영화학과");
	}
}