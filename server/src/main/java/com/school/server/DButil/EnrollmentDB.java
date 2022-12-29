package com.school.server.DButil;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentDB {
    private Connection connection;

    public EnrollmentDB() {
        connection = DButil.getConnection();
    }

    public List<String> getStudentSubject(String id){
        List<String> subjectsIDs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select Sub_Id from enrolment where St_Id = " + id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                subjectsIDs.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            return null;
        }
        return subjectsIDs;
    }
}
