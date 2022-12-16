package com.school.sprint1.Service;


import com.google.gson.Gson;
import com.school.sprint1.DButil.StudentDB;
import com.school.sprint1.gson.ExamTableInfo;
import com.school.sprint1.model.Student;
import org.springframework.stereotype.Service;

@Service
public class ExamTableService {

    public String getStudentExamTable(String input){
        ExamTableInfo info = getInfo(input);
        String id = info.getId();
        StudentDB studentDB = new StudentDB();
        Student student = studentDB.getStudent(id);

        // get student table

        return "";
    }

    public boolean setStudentExamTable(String input){

        return true;
    }



    private ExamTableInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input,ExamTableInfo.class);
    }
}
