package com.school.Server.model;

public class Sub {
    private String Sub_Id;
    private String Name;
    private String Max_grade;
    private String Min_grade;
    private String exam_grade;
    private String In_grade;
    private String Exam_Date;

    public Sub(String sub_Id, String name, String max_grade, String min_grade, String exam_grade, String in_grade, String exam_Date) {
        Sub_Id = sub_Id;
        Name = name;
        Max_grade = max_grade;
        Min_grade = min_grade;
        this.exam_grade = exam_grade;
        In_grade = in_grade;
        Exam_Date = exam_Date;
    }

    public Sub() {
    }

    public String getSub_Id() {
        return Sub_Id;
    }

    public void setSub_Id(String sub_Id) {
        Sub_Id = sub_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMax_grade() {
        return Max_grade;
    }

    public void setMax_grade(String max_grade) {
        Max_grade = max_grade;
    }

    public String getMin_grade() {
        return Min_grade;
    }

    public void setMin_grade(String min_grade) {
        Min_grade = min_grade;
    }

    public String getExam_grade() {
        return exam_grade;
    }

    public void setExam_grade(String exam_grade) {
        this.exam_grade = exam_grade;
    }

    public String getIn_grade() {
        return In_grade;
    }

    public void setIn_grade(String in_grade) {
        In_grade = in_grade;
    }

    public String getExam_Date() {
        return Exam_Date;
    }

    public void setExam_Date(String exam_Date) {
        Exam_Date = exam_Date;
    }

    @Override
    public String toString() {
        return "\"" + Sub_Id + '\"' +
                ", \"" + Name + '\"' +
                ", \"" + Max_grade + '\"' +
                ", \"" + Min_grade + '\"' +
                ", \"" + exam_grade + '\"' +
                ", \"" + In_grade + '\"' +
                ", \"" + Exam_Date + '\"';
    }
}
