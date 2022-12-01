package com.school.sprint1.Service;

import com.school.sprint1.DButil.DButil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Service
public class loginService {

    private Connection connection;

    public loginService() {
        connection = DButil.getConnection();
    }

    public boolean run(String input){
        String[] input_split = split(input);
        String id = input_split[0];
        String pass = input_split[1];
        return check(id,pass);
    }

    public String[] split(String input){
        String[] input_split = input.split(",");
        return input_split;
    }

    public boolean check(String id,String pass){
        String passFromDb = "";
        try {
            PreparedStatement statement = connection.prepareStatement("select password from person where id = " + id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                passFromDb = resultSet.getString(1);
            }
        } catch (SQLException e) {
            return false;
        }
        if(Objects.equals(pass, passFromDb)) return true;
        return false;
    }


}
