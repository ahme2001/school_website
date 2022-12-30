package com.school.server.DButil;

import com.school.server.gson.ClassTableSetting;
import com.school.server.gson.Day_Lecture;
import com.school.server.gson.Lectures;
import com.school.server.model.ClassTable;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClassTableDB {
    private Connection connection;

    public ClassTableDB() {
        connection = DButil.getConnection();
    }

    public ClassTable[] getClassTable(String classId){
        ClassTable[] classTable = new ClassTable[6];
        int index =0;

        try {
            String sql = "select * from class_dialy_table where Class_Id = " + classId + " ORDER BY\n" +
                    "     CASE\n" +
                    "          WHEN Day = 'Saturday' THEN 1\n" +
                    "          WHEN Day = 'Sunday' THEN 2\n" +
                    "          WHEN Day = 'Monday' THEN 3\n" +
                    "          WHEN Day = 'Tuesday' THEN 4\n" +
                    "          WHEN Day = 'Wednesday' THEN 5\n" +
                    "          WHEN Day = 'Thursday' THEN 6\n" +
                    "     END ASC";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ClassTable classPerDay = new ClassTable();
                classPerDay.setClassId(classId);
                classPerDay.setTermId(resultSet.getString(2));
                classPerDay.setDay(resultSet.getString(3));
                classPerDay.setLec1(resultSet.getString(4));
                classPerDay.setLec2(resultSet.getString(5));
                classPerDay.setLec3(resultSet.getString(6));
                classPerDay.setLec4(resultSet.getString(7));
                classPerDay.setLec5(resultSet.getString(8));
                classPerDay.setLec6(resultSet.getString(9));
                classTable[index++] = classPerDay;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return classTable;
    }

    public boolean insertTable(ClassTableSetting classTableSetting){
        for(int i=0;i<6;i++){
            try {
                ClassTable classTable = getDayTable(classTableSetting,i);
                PreparedStatement statement = connection.prepareStatement("insert into class_dialy_table values(" + classTable + ")");
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    private ClassTable getDayTable(ClassTableSetting classTableSetting,int dayNum){
        String classId = classTableSetting.getClassId();
        String termId = classTableSetting.getTermId();
        Day_Lecture[] days = classTableSetting.getDays();
        String day = days[dayNum].getDay();
        Lectures lectures = days[dayNum].getLectures();
        String lec1 = lectures.getLec1();
        String lec2 = lectures.getLec2();
        String lec3 = lectures.getLec3();
        String lec4 = lectures.getLec4();
        String lec5 = lectures.getLec5();
        String lec6 = lectures.getLec6();

        return new ClassTable(classId,termId,day,lec1,lec2,lec3,lec4,lec5,lec6);

    }

}
