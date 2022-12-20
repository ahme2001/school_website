package com.school.sprint1.DButil;

import com.school.sprint1.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDB {
    private Connection connection;

    public TeacherDB() {
        connection = DButil.getConnection();
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(Teacher_Id) from TEACHER ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
        return count;
    }
    public boolean addTeacher(String values){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into TEACHER values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Teacher getTeacher(String ID){
        Teacher teacher = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from TEACHER where Teacher_Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                teacher = new Teacher();
//                teacher.setTeacher_id(ID);
                teacher.setExperience(resultSet.getString(2));
                teacher.setSub(resultSet.getString(3));

            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return teacher;
    }
}
