package com.school.server.Service;

import com.google.gson.Gson;
import com.school.server.DButil.*;
import com.school.server.model.*;
import org.springframework.stereotype.Service;
import java.sql.Connection;

@Service
public class CreateAccountService {

    private Connection connection;

    public CreateAccountService() {
        connection = DButil.getConnection();
    }

    public boolean run(String input, String type){
        Person person = getPerson(input,type);
        person.setId(person.generateId());
        person.setPassword(person.generatePassword());
        return StoreDB(type, person);
    }
    boolean StoreDB(String type, Person person){
        PersonDB PDB = new PersonDB();
        boolean res = PDB.addPerson(person.toString());
        if(type == "Student") {
            return res && new StudentDB().addStudent(person.ToStringSpecific());
        }else if(type == "Staff"){
            return res && new StaffDB().addStaff(person.ToStringSpecific());
        }else if(type == "Parent"){
            System.out.println(person.ToStringSpecific());
            return res && new ParentDB().addParent(person.ToStringSpecific());
        }else if(type == "Teacher"){
            return res && new TeacherDB().addTeacher(person.ToStringSpecific());
        }
        return true;
    }
    Person getPerson(String input, String type){
        Gson gson = new Gson();

        if(type == "Student"){
            Student s = gson.fromJson(input, Student.class);
            s.setP_id();
            return s;
        }else if(type == "Staff"){
            return gson.fromJson(input, Staff.class);
        }else if(type == "Parent"){
            return gson.fromJson(input, Parent.class);
        }else if(type == "Teacher"){
            return gson.fromJson(input, Teacher.class);
        }
        return new Student(); //solve later
    }

}
