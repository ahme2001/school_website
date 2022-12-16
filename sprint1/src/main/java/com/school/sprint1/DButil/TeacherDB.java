package com.school.sprint1.DButil;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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


    public String getClass(String tId){
        HashMap<String , Object> res = new HashMap<>();
        ArrayList<String> Ids=new ArrayList<>();
        ArrayList<String> Ns =new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("where CLASS.Class_Id = Teach.Class_Id and Teach.Teacher_Id = TEACHER.Teacher_Id and TEACHER.Teacher_Id = "+tId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Ids.add(resultSet.getString(1));
                Ns.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
            return "NOT FOUND";
        }
        return new Gson().toJson(res);
    }
}
