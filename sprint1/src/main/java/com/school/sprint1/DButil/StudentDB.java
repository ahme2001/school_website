package com.school.sprint1.DButil;
import com.school.sprint1.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
    public boolean updateEndTerm (){
        // update all tables
        try {
            PreparedStatement statement = connection.prepareStatement("delete from class_dialy_table where Class_Id != \"-1\"");
            statement.execute();
            statement = connection.prepareStatement("delete from do_quiz where Quiz_Id != \"-1\"");
            statement.execute();
            statement = connection.prepareStatement("delete from fees where year != -1");
            statement.execute();
            statement = connection.prepareStatement("delete from question where Quiz_Id != \"-1\"");
            statement.execute();
            statement = connection.prepareStatement("delete from quiz where Quiz_Id != \"-1\"");
            statement.execute();
            statement = connection.prepareStatement("delete from teacher_dialy_table where Teacher_Id != \"-1\"");
            statement.execute();
        }catch (SQLException e) {
            System.out.println("2222   "+ e);
            return false;
        }
        // check if all students are available to update
        try {
            PreparedStatement statement = connection.prepareStatement("select St_Id , Curr_Term_Id , Class_Id from STUDENT");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(!updateStudentInfo(resultSet.getString(1) , resultSet.getString(2) , resultSet.getString(3))){
                    System.out.println("n2222222222222222222222222222222222222");
                    return false ;
                }
            }
        }catch (SQLException e) {
            System.out.println("33333   "+ e);
            return false;
        }
        return true ;
    }
    public boolean IsEnd(){
        // check if all students are available to update
        try {
            PreparedStatement statement = connection.prepareStatement("select St_Id , Curr_Term_Id from STUDENT");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(!checkToUpdate(resultSet.getString(1),resultSet.getString(2))){
                    return false ;
                }
            }
        }catch (SQLException e) {
            return false;
        }
        return true;
    }
    public boolean checkToUpdate(String St , String term ) {
        try {
            PreparedStatement statement = connection.prepareStatement("select Exam_grade from ENROLMENT where St_Id = "+St + " and Term_ID =  "+term);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getInt(1)<0)
                    return false ;
            }
        }catch (SQLException e) {
            return false;
        }
        return true;
    }
    public boolean updateStudentInfo(String St , String term , String Class ) {
        int intT = Integer.parseInt(term);
        if (term.equals("072"))
            deleteStudentEnrolment(St);
        else{
            if (intT%2==0) {
                intT+=10;
                intT -=1;
            }
            else intT +=1;
            term = Integer.toString(intT);
            if (term.length()==2){
                term="0"+term;
            }
            int num = Integer.parseInt(Class)%10;
            Class = term+Integer.toString(num);
            try {
                PreparedStatement statement1 = connection.prepareStatement("update STUDENT set Class_Id = \""+ Class +"\" where St_Id = " + St);
                statement1.execute();
                PreparedStatement statement2 = connection.prepareStatement("update STUDENT set Curr_Term_Id = \""+term+"\" where St_Id = " + St);
                statement2.execute();
            }catch (SQLException e){
                return false;
            }
            addStudentEnrolment(St , term);
        }
        return true;
    }
    public void addStudentEnrolment(String St , String CurrTerm ) {
        try {
            PreparedStatement statement = connection.prepareStatement("select Sub_Id from sub where Sub_Id like " + "\"" + CurrTerm + "__\"");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String res=  "(\"" + resultSet.getString(1) + "\"," +
                        "\"" + St + "\"," +
                        "\"" + CurrTerm+ "\"," +
                        "0"  + "," +
                        "0"  + "," +
                        "-1" + "," +
                        "0"  + "," +
                        "0" + ")";
                try {
                    PreparedStatement statement2 = connection.prepareStatement("insert into ENROLMENT values "+ res);
                    statement2.execute();

                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        } catch (SQLException e){
            System.out.println(e);
        }
    }
    public void deleteStudentEnrolment(String St ){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from enrolment where St_Id = " + St);
            statement.execute();
            PreparedStatement statement1 = connection.prepareStatement("delete from STUDENT where St_Id = " + St);
            statement1.execute();
            PreparedStatement statement2 = connection.prepareStatement("delete from PERSON where Id = " + St);
            statement2.execute();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
