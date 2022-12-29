package com.school.server.DButil;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class FeesDB {

    private Connection connection;
    public FeesDB() {
        connection = DButil.getConnection();
    }

    public boolean insertFeesYear(String year,String fees){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into fees values(" + fees + "," + year + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String getFees(String year){
        String fees = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select fees from fees where year = " + year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                fees = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return fees;
    }
}
