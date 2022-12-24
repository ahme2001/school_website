package com.school.Server.Service;


import com.google.gson.Gson;
import com.school.Server.DButil.EnrollmentDB;
import com.school.Server.DButil.StudentDB;
import com.school.Server.DButil.SubDB;
import com.school.Server.gson.ExamTableStudentInfo;
import com.school.Server.gson.SubjectsInfo;
import com.school.Server.gson.Year_Code;
import com.school.Server.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamTableService {

    public String getStudentExamTable(String input){
        ExamTableStudentInfo info = getInfo(input);
        String id = info.getId();

        // check if this student already exist
        StudentDB studentDB = new StudentDB();
        Student student = studentDB.getStudent(id);
        if(student == null) return null;

        // get the subjects that are taken by this student
        EnrollmentDB enrollmentDB = new EnrollmentDB();
        List<String> studentSubjectsIDs = enrollmentDB.getStudentSubject(id);

        // get student table
        SubDB subDB = new SubDB();
        return new Gson().toJson(subDB.getSubjectInfo(studentSubjectsIDs));

    }

    public boolean setStudentExamTable(String input){
        SubjectsInfo[] subjectsInfoList = new Gson().fromJson(input,SubjectsInfo[].class);

        SubDB subDB = new SubDB();
        return subDB.updateExamDates(subjectsInfoList);
    }

    public String getSubjects(String input){
        Year_Code year_code = getYearCode(input);
        String code = year_code.getCode();

        SubDB subDB = new SubDB();
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
