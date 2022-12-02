package com.school.sprint1.DButil;

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
}
