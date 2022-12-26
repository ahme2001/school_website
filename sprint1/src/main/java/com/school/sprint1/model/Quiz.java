package com.school.sprint1.model;

import com.school.sprint1.DButil.QuizDB;

import java.util.Date;

public class Quiz {
    private String Quiz_Id ;
    private String teacher_Id ;
    private String name ;
    private String endTime ;
    private String Class_Id ;
    private String maxGrade ;

    public void setQuiz_Id(String quiz_Id) {
        Quiz_Id = quiz_Id;
    }

    public void setClass_Id(String class_Id) {
        Class_Id = class_Id;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxGrade(String maxGrade) {
        this.maxGrade = maxGrade;
    }

    public void setTeacher_Id(String teacher_Id) {
        this.teacher_Id = teacher_Id;
    }

    public String getName() {
        return name;
    }
    public String generateId() {
        QuizDB qDB = new QuizDB();
        return Integer.toString(qDB.getCount());
    }
    public String ToString() {
        return "\"" + this.Quiz_Id + "\"," +
                "\"" + this.name + "\"," +
                "\"" + this.teacher_Id + "\"," +
                "\"" + this.endTime + "\"," +
                "\"" + this.Class_Id + "\"" +
                "\"" + this.maxGrade + "\","
                ;
    }

}
