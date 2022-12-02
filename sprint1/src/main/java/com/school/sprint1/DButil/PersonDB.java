package com.school.sprint1.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDB {
    private Connection connection;

    public PersonDB() {
        connection = DButil.getConnection();
    }
    public boolean addPerson(String values){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into PERSON values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    public String getPass(String id){
        String pass = "";
        try {
            PreparedStatement statement = connection.prepareStatement("select password from person where id = " + id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                pass = resultSet.getString(1);
            }
        } catch (SQLException e) {
            return null;
        }
        return pass;
    }
}
