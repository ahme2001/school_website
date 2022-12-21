package com.school.sprint1.DButil;

import com.school.sprint1.gson.SubjectsInfo;
import com.school.sprint1.model.Sub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubDB {

    private Connection connection;
    public SubDB() {
        connection = DButil.getConnection();
    }

    public List<SubjectsInfo> getAllSubjects(String code){
        List<SubjectsInfo> subjectsInfosList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select Sub_Id,Name,Exam_Date from sub where Sub_Id like " + "\"" + code + "__\"");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                SubjectsInfo subjectsInfo = new SubjectsInfo();
                subjectsInfo.setId(resultSet.getString(1));
                subjectsInfo.setSubject(resultSet.getString(2));
                subjectsInfo.setDate(resultSet.getString(3));
                subjectsInfosList.add(subjectsInfo);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return subjectsInfosList;
    }

    public boolean updateExamDates(SubjectsInfo[] subjectsInfo){
        for(int i=0;i< subjectsInfo.length;i++){
            try {
                PreparedStatement statement = connection.prepareStatement("update Sub set Exam_Date = \""+ subjectsInfo[i].getDate() + "\" where Sub_Id = " + subjectsInfo[i].getId());
                statement.execute();

            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public List<SubjectsInfo> getSubjectInfo(List<String> studentSubjectsIDs){
        List<SubjectsInfo> subjectsInfosList = new ArrayList<>();
        for(int i=0;i<studentSubjectsIDs.size();i++){
            try {
                PreparedStatement statement = connection.prepareStatement("select Name,Exam_Date from sub where Sub_Id = " + studentSubjectsIDs.get(i));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    SubjectsInfo subjectsInfo = new SubjectsInfo();
                    subjectsInfo.setId(studentSubjectsIDs.get(i));
                    subjectsInfo.setSubject(resultSet.getString(1));
                    subjectsInfo.setDate(resultSet.getString(2));
                    subjectsInfosList.add(subjectsInfo);
                }
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }
        }
        return subjectsInfosList;
    }

}
