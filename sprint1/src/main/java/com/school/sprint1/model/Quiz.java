package com.school.sprint1.model;

import com.school.sprint1.DButil.QuizDB;

import java.util.Date;

public class Quiz {
    private String Quiz_Id ;
    private String name ;
    private Date endTime ;
    private String Class_Id ;


    public void setQuiz_Id(String quiz_Id) {
        Quiz_Id = quiz_Id;
    }

    public void setClass_Id(String class_Id) {
        Class_Id = class_Id;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_Id() {
        return Class_Id;
    }

    public String getQuiz_Id() {
        return Quiz_Id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }
    public String generateId() {
        QuizDB qDB = new QuizDB();
        return Integer.toString(qDB.getCount(this.Class_Id));
    }
    public String ToString() {
        return "\"" + this.Quiz_Id + "\"," +
                "\"" + this.name + "\"," +
                "\"" + this.endTime + "\"," +
                "\"" + this.Class_Id + "\"" ;
    }

}
