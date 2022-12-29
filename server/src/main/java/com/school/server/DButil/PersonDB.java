package com.school.server.DButil;

import com.school.server.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDB {
    private Connection connection;

    public PersonDB() {
        connection = DButil.getConnection();
    }
    public boolean addPerson(String values) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into PERSON values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public String getPass(String id){
        String pass = null;
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
    
    public boolean getInfo(String ID, Person person){
        try {
            PreparedStatement statement = connection.prepareStatement("select * from PERSON where Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            person.setAddress(resultSet.getString(2));
            person.setPhone(resultSet.getString(3));
            person.setName(resultSet.getString(4));
            person.setNational_Id(resultSet.getString(5));
            person.setSex(resultSet.getString(6));
            person.setPassword(resultSet.getString(7));
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
