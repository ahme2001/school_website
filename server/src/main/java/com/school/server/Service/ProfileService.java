package com.school.server.Service;

import com.google.gson.Gson;
import com.school.server.DButil.*;
import com.school.server.model.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private ParentDB parentDB;

    @Autowired
    private TeacherDB teacherDB;

    @Autowired
    private StaffDB staffDB;


    public String run(String ID){
        if(ID.length() != 8) return "Error ID";
        return getPerson(ID);
    }
    private String getPerson(String ID){
        String identifier="";

        identifier += ID.charAt(2);
        identifier += ID.charAt(3);
        Gson gson = new Gson();

        if(identifier.compareTo("01") == 0){
            Student student = studentDB.getInfo(ID);
            if(student == null) return "ID not found in the database";
            return gson.toJson(student);
        }else if(identifier.compareTo("02") == 0){
            Parent parent = parentDB.getInfo(ID);
            if(parent == null) return "ID not found in the database";
            return gson.toJson(parent);
        }else if(identifier.compareTo("03") == 0){
            Teacher teacher = teacherDB.getInfo(ID);
            if(teacher == null) return "ID not found in the database";
            return gson.toJson(teacher);
        }else if(identifier.compareTo("04") == 0){
            Staff staff = staffDB.getInfo(ID);
            if(staff == null) return "ID not found in the database";
            return gson.toJson(staff);
        }
        return "Error bad ID format";
    }
}
