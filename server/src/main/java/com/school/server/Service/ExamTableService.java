package com.school.server.Service;


import com.google.gson.Gson;
import com.school.server.DButil.EnrollmentDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.SubDB;
import com.school.server.gson.ExamTableStudentInfo;
import com.school.server.gson.SubjectsInfo;
import com.school.server.gson.Year_Code;
import com.school.server.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamTableService {

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private EnrollmentDB enrollmentDB;

    @Autowired
    private SubDB subDB;

    public String getStudentExamTable(String input){
        ExamTableStudentInfo info = getInfo(input);
        String id = info.getId();

        // check if this student already exist
        Student student = studentDB.getStudent(id);
        if(student == null) return null;

        // get the subjects that are taken by this student
        List<String> studentSubjectsIDs = enrollmentDB.getStudentSubject(id);

        // get student table
        return new Gson().toJson(subDB.getSubjectInfo(studentSubjectsIDs));

    }

    public boolean setStudentExamTable(String input){
        SubjectsInfo[] subjectsInfoList = new Gson().fromJson(input,SubjectsInfo[].class);

        // update the table in the database
        return subDB.updateExamDates(subjectsInfoList);
    }

    public String getSubjects(String input){
        Year_Code year_code = getYearCode(input);
        String code = year_code.getCode();

        return new Gson().toJson(subDB.getAllSubjects(code));
    }


    private ExamTableStudentInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input, ExamTableStudentInfo.class);
    }

    private Year_Code getYearCode(String input) {
        Gson gson = new Gson();
        return gson.fromJson(input, Year_Code.class);
    }

}
