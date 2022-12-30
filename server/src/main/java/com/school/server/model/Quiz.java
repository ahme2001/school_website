package com.school.server.model;

import com.school.server.DButil.QuizDB;

public class Quiz {
    private String Quiz_Id ;
    private String name ;
    private String endTime ;
    private String Class_Id ;


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

    public String getClass_Id() {
        return Class_Id;
    }

    public String getQuiz_Id() {
        return Quiz_Id;
    }

    public String getEndTime() {
        return endTime;
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
                "\"" + this.endTime + "\"," +
                "\"" + this.Class_Id + "\"" ;
    }

}
