package com.school.sprint1.DButil;

import com.school.sprint1.model.Staff;
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
    public Teacher getInfo(String ID){
        Teacher teacher = new Teacher();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from PERSON where Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            teacher.setAddress(resultSet.getString(2));
            teacher.setPhone(resultSet.getString(3));
            teacher.setName(resultSet.getString(4));
            teacher.setNational_Id(resultSet.getString(5));
            teacher.setSex(resultSet.getString(6));
            teacher.setPassword(resultSet.getString(7));
            statement = connection.prepareStatement("select * from TEACHER where Teacher_Id = " + ID);
            resultSet = statement.executeQuery();
            resultSet.next();
            teacher.setExperience(resultSet.getString(2));
            teacher.setSub(resultSet.getString(3));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return teacher;
    }
}
