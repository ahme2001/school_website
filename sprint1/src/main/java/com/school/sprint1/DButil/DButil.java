package com.school.sprint1.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    //change password of your database connection before beginning
    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection != null) return connection;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/SCHOOL?useSSL=false";
        String user = "root";
<<<<<<< HEAD:server/src/main/java/com/school/sprint1/DButil/DButil.java
        String password = "welcome123";
=======
        String password = "Michael82468246";
>>>>>>> c82507a6084dc1bc83fa9107da0626d2ea6f1658:sprint1/src/main/java/com/school/sprint1/DButil/DButil.java

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
