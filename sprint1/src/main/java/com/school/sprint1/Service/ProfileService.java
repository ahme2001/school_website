package com.school.sprint1.Service;

import com.google.gson.Gson;
import com.school.sprint1.DButil.*;
import com.school.sprint1.model.*;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    public String run(String ID){
        String person = getType(ID);
        return person;
    }
    private String getType(String ID){
        String type="";
        String identifier="";
        identifier += ID.charAt(3);
        identifier += ID.charAt(2);
        System.out.println(identifier);
        Gson gson = new Gson();

        if(identifier.compareTo("01") == 0){
            StudentDB DB = new StudentDB();
            Student student = DB.getInfo(ID);
            return gson.toJson(student);
        }else if(identifier.compareTo("02") == 0){
            ParentDB DB = new ParentDB();
            Parent parent = DB.getInfo(ID);
            return gson.toJson(parent);
        }else if(identifier.compareTo("03") == 0){
            TeacherDB DB = new TeacherDB();
            Teacher teacher = DB.getInfo(ID);
            return gson.toJson(teacher);
        }else if(identifier.compareTo("04") == 0){
            StaffDB DB = new StaffDB();
            Staff staff = DB.getInfo(ID);
            return gson.toJson(staff);
        }
        return null;
    }
}
