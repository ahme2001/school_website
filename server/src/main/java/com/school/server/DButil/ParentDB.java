package com.school.server.DButil;

import com.school.server.model.Parent;
import com.school.server.model.Person;
import com.school.server.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentDB {
    private Connection connection;

    public ParentDB() {
        connection = DButil.getConnection();
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(P_id) from PARENT");
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
    public String getID(String national_id){
        String str = "";
        try {
            PreparedStatement statement = connection.prepareStatement("select Id from PERSON where National_Id = " + national_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                str = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return str;
    }
    public boolean addParent(String values){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into PARENT values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Parent getInfo(String ID){
        Parent parent = new Parent();
        try {
            if(!new PersonDB().getInfo(ID, parent)) return  null;
            PreparedStatement statement = connection.prepareStatement("select * from PARENT where P_id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            parent.setJob(resultSet.getString(2));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return parent;
    }
}
