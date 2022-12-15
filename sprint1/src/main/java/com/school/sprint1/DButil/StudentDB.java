package com.school.sprint1.DButil;

import com.school.sprint1.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDB {
    private Connection connection;

    public StudentDB() {
        connection = DButil.getConnection();
    }
    public int getCount(String term_id) {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(St_Id) from STUDENT where St_Term_Id = " + term_id + " group by st_term_id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        }
        return count;
    }
    public boolean addStudent(String values){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into STUDENT values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public Student getInfo(String ID){
        Student s = new Student();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from PERSON where Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            s.setAddress(resultSet.getString(2));
            s.setPhone(resultSet.getString(3));
            s.setName(resultSet.getString(4));
            s.setNational_Id(resultSet.getString(5));
            s.setSex(resultSet.getString(6));
            s.setPassword(resultSet.getString(7));
            statement = connection.prepareStatement("select * from STUDENT where St_Id = " + ID);
            resultSet = statement.executeQuery();
            resultSet.next();
            s.setClass_Id(resultSet.getString(2));
            s.setSt_Term_Id(resultSet.getString(3));
            s.setCurr_Term_Id(resultSet.getString(4));
            s.setP_id(resultSet.getString(5));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return s;
    }
}
