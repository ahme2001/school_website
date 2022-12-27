package com.school.sprint1.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscussionDB {
    private Connection connection;

    public DiscussionDB() {
        connection = DButil.getConnection();
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(post_id) from Discussion");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
        return count;
    }
    public boolean addPost(String values){
        try {
            System.out.println("insert into Discussion values(" + values + ")");
            PreparedStatement statement = connection.prepareStatement("insert into Discussion values(" + values + ")");
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
