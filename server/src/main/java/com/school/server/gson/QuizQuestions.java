package com.school.server.gson;

public class QuizQuestions {
    private String classId ;
    private String teacher_Id ;

    private String endDate;
    private String Qname;
    private String[] questions;
    private String[] choice1 ;
    private String[] choice2 ;
    private String[] choice3 ;
    private String[] choice4 ;
    private int[] answers ;

    public int[] getAnswers() {
        return answers;
    }

    public String[] getChoice1() {
        return choice1;
    }

    public String getClassId() {
        return classId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String[] getChoice2() {
        return choice2;
    }

    public String[] getChoice3() {
        return choice3;
    }

    public String[] getChoice4() {
        return choice4;
    }

    public String[] getQuestions() {
        return questions;
    }
    public String getQname() {
        return Qname;
    }

    public String getTeacher_Id() {
        return teacher_Id;
    }
}
