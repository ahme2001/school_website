package com.school.sprint1.DButil;

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
}
