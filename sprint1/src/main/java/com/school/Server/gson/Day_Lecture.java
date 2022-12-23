package com.school.Server.gson;

public class Day_Lecture {
    private String Day;
    private Lectures lectures;

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        this.Day = day;
    }

    public Lectures getLectures() {
        return lectures;
    }

    public void setLectures(Lectures lectures) {
        this.lectures = lectures;
    }
}
