package com.school.server.model;

import java.util.Date;

public class DO_QUIZ {
    private String Quiz_Id;
    private String St_Id;
    private int grade;
    private String SubmissionTime;

    public DO_QUIZ(String Quiz_Id , String St_Id){
        this.Quiz_Id=Quiz_Id;
        this.St_Id=St_Id;
        this.grade= -1;
        this.SubmissionTime=null;
    }
    public String ToString() {
        return "\"" + this.Quiz_Id + "\"," +
                "\"" + this.St_Id + "\"," +
                "\"" + this.grade + "\"," +
                "\"" + this.SubmissionTime + "\"" ;
    }

}