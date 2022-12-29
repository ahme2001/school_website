package com.school.server.DButil;
import com.school.server.model.Student;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Repository
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

    public Student getInfo(String ID){
        Student student = new Student();
        try {
            if(!new PersonDB().getInfo(ID, student)) return  null;
            PreparedStatement statement = connection.prepareStatement("select * from STUDENT where St_Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            student.setClass_Id(resultSet.getString(2));
            student.setST_Term_Id(resultSet.getString(3));
            student.setCurr_term_id(resultSet.getString(4));
            student.setP_id(resultSet.getString(5));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return student;
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
    public Map<String,String> getTermGrades(String ID, String Term){
        Map<String,String> mapGrades =new HashMap();
        String previousSub = "";
        String currentSub = "";

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Sub_Id, Exam_grade FROM ENROLMENT WHERE St_Id = \"" + ID + "\" AND Term_Id  LIKE \'" + Term + "%\' ORDER by Sub_Id, Term_Id DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                currentSub = resultSet.getString(1);
                if(currentSub.compareTo(previousSub) != 0){
                    statement = connection.prepareStatement("SELECT Name FROM SUB WHERE Sub_Id = \"" + currentSub + "\"");
                    ResultSet subName = statement.executeQuery();
                    subName.next();
                    mapGrades.put(subName.getString(1), resultSet.getString(2));
                }
                previousSub = currentSub;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return mapGrades;
    }

    public Student getStudent(String ID){
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from STUDENT where St_Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                student = new Student();
                student.setSt_id(resultSet.getString(1));
                student.setClass_Id(resultSet.getString(2));
                student.setST_Term_Id(resultSet.getString(3));
                student.setCurr_term_id(resultSet.getString(4));
                student.setP_id(resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return student;
    }
    public String getClass(String ID) {
        try {
            PreparedStatement statement = connection.prepareStatement("select Class_Id from STUDENT where St_Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return  resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
