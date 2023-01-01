package com.school.server.Service;

import com.google.gson.Gson;
import com.school.server.DButil.*;
import com.school.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.util.Objects;

@Service
public class CreateAccountService {

    @Autowired
    private PersonDB PDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private TeacherDB teacherDB;

    @Autowired
    private StaffDB staffDB;

    @Autowired
    private ParentDB parentDB;

    public boolean run(String input, String type){
        Person person = getPerson(input,type);
        if(person == null) return false;
        String id = "";
        switch (type){
            case "Student" :
                Student s = (Student) person;
                id = s.generateStudentId(studentDB); break;
            case "Staff" :
                Staff staff = (Staff) person;
                id = staff.generateStaffId(staffDB); break;
            case "Parent" :
                Parent parent = (Parent) person;
                id = parent.generateParentId(parentDB); break;
            case "Teacher" :
                Teacher teacher = (Teacher) person;
                id = teacher.generateTeacherId(teacherDB); break;
        }
        person.setId(id);
        String password = person.generatePassword();
        person.setPassword(password);
        return StoreDB(type, person);
    }

    boolean StoreDB(String type, Person person){
        boolean res = PDB.addPerson(person.toString());
        if(Objects.equals(type, "Student")) {
            return res && studentDB.addStudent(person.ToStringSpecific());
        }else if(Objects.equals(type, "Staff")){
            return res && staffDB.addStaff(person.ToStringSpecific());
        }else if(Objects.equals(type, "Parent")){
            return res && parentDB.addParent(person.ToStringSpecific());
        }else{
            return res && teacherDB.addTeacher(person.ToStringSpecific());
        }
    }

    Person getPerson(String input, String type){
        Gson gson = new Gson();

        if(Objects.equals(type, "Student")){
            Student s = gson.fromJson(input, Student.class);
            s.setP_id(parentDB);
            return s;
        }else if(Objects.equals(type, "Staff")){
            return gson.fromJson(input, Staff.class);
        }else if(Objects.equals(type, "Parent")){
            return gson.fromJson(input, Parent.class);
        }else if(Objects.equals(type, "Teacher")){
            return gson.fromJson(input, Teacher.class);
        }
        return null;
    }

}
