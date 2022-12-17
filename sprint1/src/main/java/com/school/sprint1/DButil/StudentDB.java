package com.school.sprint1.DButil;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
    public Map<String,String> getStudents(String Term){
        Map<String,String> mapStudent =new HashMap();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT St_Id, Name  FROM STUDENT join PERSON ON St_Id = Id WHERE Curr_Term_Id  LIKE \"" + Term +"%\"");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                mapStudent.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return mapStudent;
    }
    public Map<String,String> getTermRange(String ID){
        Map<String,String> mapRange =new HashMap();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT St_Term_Id, Curr_Term_Id  FROM STUDENT WHERE St_Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                mapRange.put("startyear", resultSet.getString(1));
                mapRange.put("currentyear", resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return mapRange;
    }
}
