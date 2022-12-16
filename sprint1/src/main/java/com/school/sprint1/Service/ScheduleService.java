package com.school.sprint1.Service;


import com.google.gson.Gson;
import com.school.sprint1.DButil.ClassTableDB;
import com.school.sprint1.DButil.StudentDB;
import com.school.sprint1.gson.ClassTableSetting;
import com.school.sprint1.gson.Day_Lecture;
import com.school.sprint1.gson.Lectures;
import com.school.sprint1.gson.scheduleInfo;
import com.school.sprint1.model.ClassTable;
import com.school.sprint1.model.Student;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    public String getStudentTable(String input){
        scheduleInfo info = getInfo(input);
        String id = info.getId();
        StudentDB studentDB = new StudentDB();
        Student student = studentDB.getStudent(id);;


        String classId = student.getClass_Id();

        // get table from class table with the class id
        ClassTableDB classTableDB = new ClassTableDB();
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
        ClassTableDB classTableDB = new ClassTableDB();
        return classTableDB.insertTable(classTableSetting);
    }

    public String getTeacherTable(String input){
        scheduleInfo info = getInfo(input);
        String id = info.getId();

        // get table from teacher table with the id

        Gson gson = new Gson();
//        return gson.toJson(teacherTable)
        return null;
    }

    public boolean setTeacherTable(String input){


        return true;
    }

    private scheduleInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input,scheduleInfo.class);
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
}
