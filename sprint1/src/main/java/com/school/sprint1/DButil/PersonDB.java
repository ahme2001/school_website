package com.school.sprint1.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }
}
