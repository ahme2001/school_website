package com.school.sprint1.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    //change password of your database connection before begining
    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection != null) return connection;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/SCHOOL?useSSL=false";
        String user = "root";
        String password = "TIGER";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
