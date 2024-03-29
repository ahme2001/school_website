package com.school.server.Service;


import com.google.gson.Gson;
import com.school.server.DButil.ClassTableDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.TeacherDB;
import com.school.server.DButil.TeacherTableDB;
import com.school.server.gson.*;
import com.school.server.model.ClassTable;
import com.school.server.model.Student;
import com.school.server.model.Teacher;
import com.school.server.model.TeacherTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ScheduleService {

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private TeacherDB teacherDB;

    @Autowired
    private ClassTableDB classTableDB;

    @Autowired
    private TeacherTableDB teacherTableDB;

    public String getStudentTable(String input){
        schedulePersonInfo info = getInfo(input);
        String id = info.getId();
        Student student = studentDB.getStudent(id);
        if(student == null) return null;
        String classId = student.getClass_Id();

        // get table from class table with the class id
        ClassTable[] classTable = classTableDB.getClassTable(classId);

        // format the gson to the front
        ClassTableSetting classTableSetting = getClassTable(classTable);

        // convert the object to string gson and return it to the frontend
        Gson gson = new Gson();
        return gson.toJson(classTableSetting);
    }

    public boolean setStudentTable(String input){
        // split the json
        ClassTableSetting classTableSetting = splitClassTable(input);

        // insert the table to the database
        return classTableDB.insertTable(classTableSetting);
    }

    public String getTeacherTable(String input){
        schedulePersonInfo info = getInfo(input);
        String id = info.getId();

        // check if the id exists in teacher table
        Teacher teacher = teacherDB.getTeacher(id);
        if(teacher == null) return null;

        // get table from teacher table with the id
        TeacherTable[] teacherTables = teacherTableDB.getTeacherTable(id);

        TeacherTableSetting teacherTable = getTeacherTableObjects(teacherTables);

        Gson gson = new Gson();
        return gson.toJson(teacherTable);
    }

    public boolean setTeacherTable(String input){

        // split the gson
        TeacherTableSetting teacherTableSetting = splitTeacherTable(input);

        // insert the table to the database
        return teacherTableDB.insertTable(teacherTableSetting);

    }

    private schedulePersonInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input, schedulePersonInfo.class);
    }


    private ClassTableSetting splitClassTable(String input){
        Gson gson = new Gson();
        return gson.fromJson(input,ClassTableSetting.class);
    }

    private ClassTableSetting getClassTable(ClassTable[] classTables){
        String classId = classTables[0].getClassId();
        String termId = classTables[0].getTermId();

        Day_Lecture[] days = new Day_Lecture[6];
        for(int i=0;i<6;i++){
            Day_Lecture day_lecture = new Day_Lecture();
            day_lecture.setDay(classTables[i].getDay());
            Lectures lectures = new Lectures();
            lectures.setLec1(classTables[i].getLec1());
            lectures.setLec2(classTables[i].getLec2());
            lectures.setLec3(classTables[i].getLec3());
            lectures.setLec4(classTables[i].getLec4());
            lectures.setLec5(classTables[i].getLec5());
            lectures.setLec6(classTables[i].getLec6());
            day_lecture.setLectures(lectures);
            days[i] = day_lecture;
        }
        return new ClassTableSetting(classId,termId,days);
    }

    private TeacherTableSetting splitTeacherTable(String input){
        Gson gson = new Gson();
        return gson.fromJson(input,TeacherTableSetting.class);
    }

    private TeacherTableSetting getTeacherTableObjects(TeacherTable[] teacherTables){
        String teacherId = teacherTables[0].getTeacher_id();

        Day_Lecture[] days = new Day_Lecture[6];
        for(int i=0;i<6;i++){
            Day_Lecture day_lecture = new Day_Lecture();
            day_lecture.setDay(teacherTables[i].getDay());
            Lectures lectures = new Lectures();
            lectures.setLec1(teacherTables[i].getLec1());
            lectures.setLec2(teacherTables[i].getLec2());
            lectures.setLec3(teacherTables[i].getLec3());
            lectures.setLec4(teacherTables[i].getLec4());
            lectures.setLec5(teacherTables[i].getLec5());
            lectures.setLec6(teacherTables[i].getLec6());
            day_lecture.setLectures(lectures);
            days[i] = day_lecture;
        }
        return new TeacherTableSetting(teacherId,days);
    }
}
