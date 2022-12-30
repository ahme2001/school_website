package com.school.server.DButil;

import com.school.server.Config.AesEncryption;
import com.school.server.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
            PreparedStatement statement = connection.prepareStatement("select password from PERSON where id = " + id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                pass = resultSet.getString(1);
                AesEncryption aes = new AesEncryption();
                pass = aes.decrypt(pass);
            }
            System.out.println(pass);
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
    public Vector<String> getAllChildrenName(Vector<String> childrenIds){
        Vector<String> childrenNames = new Vector<>();
        for(int i=0;i<childrenIds.size();i++){
            try {
                PreparedStatement statement = connection.prepareStatement("select Name from PERSON where Id = " + childrenIds.get(i));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String name = resultSet.getString(1);
                    childrenNames.add(name);
                }

            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
        }
        return childrenNames;
    }
}
