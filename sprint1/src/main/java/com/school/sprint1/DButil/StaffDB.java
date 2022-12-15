package com.school.sprint1.DButil;

import com.school.sprint1.model.Parent;
import com.school.sprint1.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDB {
    private Connection connection;

    public StaffDB() {
        connection = DButil.getConnection();
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(S_id) from STAFF ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        }
        return count;
    }
    public boolean addStaff(String values){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into STAFF values(" + values + ")");
            statement.execute();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public Staff getInfo(String ID){
        Staff staff = new Staff();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from PERSON where Id = " + ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            staff.setAddress(resultSet.getString(2));
            staff.setPhone(resultSet.getString(3));
            staff.setName(resultSet.getString(4));
            staff.setNational_Id(resultSet.getString(5));
            staff.setSex(resultSet.getString(6));
            staff.setPassword(resultSet.getString(7));
            statement = connection.prepareStatement("select * from STAFF where S_id = " + ID);
            resultSet = statement.executeQuery();
            resultSet.next();
            staff.setJob(resultSet.getString(2));
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return staff;
    }
}
