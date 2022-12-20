package com.school.sprint1.gson;

public class TeacherTableSetting {
    private String Teacher_Id;
    private Day_Lecture[] days;

    public TeacherTableSetting(String teacherId, Day_Lecture[] days) {
        this.Teacher_Id = teacherId;
        this.days = days;
    }

    public String getTeacherId() {
        return Teacher_Id;
    }

    public void setTeacherId(String teacherId) {
        this.Teacher_Id = teacherId;
    }

    public Day_Lecture[] getDays() {
        return days;
    }

    public void setDays(Day_Lecture[] days) {
        this.days = days;
    }
}
