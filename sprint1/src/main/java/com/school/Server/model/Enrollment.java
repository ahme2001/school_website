package com.school.Server.model;

public class Enrollment {
    private String Sub_Id;
    private String St_Id;
    private String Term_Id;
    private String Date_Flag;
    private String Year_Works;
    private String Exam_grade;
    private String Grade_Flag;
    private String state;

    public String getSub_Id() {
        return Sub_Id;
    }

    public void setSub_Id(String sub_Id) {
        Sub_Id = sub_Id;
    }

    public String getSt_Id() {
        return St_Id;
    }

    public void setSt_Id(String st_Id) {
        St_Id = st_Id;
    }

    public String getTerm_Id() {
        return Term_Id;
    }

    public void setTerm_Id(String term_Id) {
        Term_Id = term_Id;
    }

    public String getDate_Flag() {
        return Date_Flag;
    }

    public void setDate_Flag(String date_Flag) {
        Date_Flag = date_Flag;
    }

    public String getYear_Works() {
        return Year_Works;
    }

    public void setYear_Works(String year_Works) {
        Year_Works = year_Works;
    }

    public String getExam_grade() {
        return Exam_grade;
    }

    public void setExam_grade(String exam_grade) {
        Exam_grade = exam_grade;
    }

    public String getGrade_Flag() {
        return Grade_Flag;
    }

    public void setGrade_Flag(String grade_Flag) {
        Grade_Flag = grade_Flag;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
