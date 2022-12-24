package com.school.Server.model;

public class TeacherTable {
    private String teacher_id;
    private String day;
    private String lec1;
    private String lec2;
    private String lec3;
    private String lec4;
    private String lec5;
    private String lec6;

    public TeacherTable(String teacher_id, String day, String lec1, String lec2, String lec3, String lec4, String lec5, String lec6) {
        this.teacher_id = teacher_id;
        this.day = day;
        this.lec1 = lec1;
        this.lec2 = lec2;
        this.lec3 = lec3;
        this.lec4 = lec4;
        this.lec5 = lec5;
        this.lec6 = lec6;
    }

    public TeacherTable() {
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLec1() {
        return lec1;
    }

    public void setLec1(String lec1) {
        this.lec1 = lec1;
    }

    public String getLec2() {
        return lec2;
    }

    public void setLec2(String lec2) {
        this.lec2 = lec2;
    }

    public String getLec3() {
        return lec3;
    }

    public void setLec3(String lec3) {
        this.lec3 = lec3;
    }

    public String getLec4() {
        return lec4;
    }

    public void setLec4(String lec4) {
        this.lec4 = lec4;
    }

    public String getLec5() {
        return lec5;
    }

    public void setLec5(String lec5) {
        this.lec5 = lec5;
    }

    public String getLec6() {
        return lec6;
    }

    public void setLec6(String lec6) {
        this.lec6 = lec6;
    }

    @Override
    public String toString() {
        return "\"" + this.teacher_id + "\"," +
                "\"" + this.day + "\"," +
                "\"" + this.lec1 + "\"," +
                "\"" + this.lec2 + "\"," +
                "\"" + this.lec3 + "\"," +
                "\"" + this.lec4 + "\"," +
                "\"" + this.lec5 + "\"," +
                "\"" + this.lec6 + "\"";
    }
}
