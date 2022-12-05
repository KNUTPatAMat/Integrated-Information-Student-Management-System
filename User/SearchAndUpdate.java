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
        // for (String[] strings : studentDataList) {
        //     for (String strings2 : strings) {
        //         System.out.println(strings2);
        //     }
        // }
        userTable.dbDisconnection();
        return studentDataArr;
            
    }



    //개인정보 조회

    //개인정보 업데이트

}
