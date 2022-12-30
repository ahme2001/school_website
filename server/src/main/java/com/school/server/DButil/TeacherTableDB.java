package com.school.server.DButil;

import com.school.server.gson.Day_Lecture;
import com.school.server.gson.Lectures;
import com.school.server.gson.TeacherTableSetting;
import com.school.server.model.TeacherTable;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TeacherTableDB {

    private Connection connection;

    public TeacherTableDB() {
        connection = DButil.getConnection();
    }

    public TeacherTable[] getTeacherTable(String teacherId){
        TeacherTable[] teacherTables = new TeacherTable[6];
        int index =0;

        try {
            String sql = "select * from teacher_dialy_table where Teacher_Id = " + teacherId + " ORDER BY\n" +
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
                TeacherTable tablePerDay = new TeacherTable();
                tablePerDay.setTeacher_id(teacherId);
                tablePerDay.setDay(resultSet.getString(2));
                tablePerDay.setLec1(resultSet.getString(3));
                tablePerDay.setLec2(resultSet.getString(4));
                tablePerDay.setLec3(resultSet.getString(5));
                tablePerDay.setLec4(resultSet.getString(6));
                tablePerDay.setLec5(resultSet.getString(7));
                tablePerDay.setLec6(resultSet.getString(8));
                teacherTables[index++] = tablePerDay;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return teacherTables;
    }


    public boolean insertTable(TeacherTableSetting teacherTableSetting){
        for(int i=0;i<6;i++){
            try {
                TeacherTable teacherTable = getDayTable(teacherTableSetting,i);
                PreparedStatement statement = connection.prepareStatement("insert into teacher_dialy_table values(" + teacherTable + ")");
                statement.execute();

            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    private TeacherTable getDayTable(TeacherTableSetting teacherTableSetting, int dayNum){
        String teacherId = teacherTableSetting.getTeacherId();
        Day_Lecture[] days = teacherTableSetting.getDays();
        String day = days[dayNum].getDay();
        Lectures lectures = days[dayNum].getLectures();
        String lec1 = lectures.getLec1();
        String lec2 = lectures.getLec2();
        String lec3 = lectures.getLec3();
        String lec4 = lectures.getLec4();
        String lec5 = lectures.getLec5();
        String lec6 = lectures.getLec6();

        return new TeacherTable(teacherId,day,lec1,lec2,lec3,lec4,lec5,lec6);
    }
}
