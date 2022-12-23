package com.school.Server.model;

public class Question {
    private String Quiz_Id ;
    private String Question_Id ;
    private String question ;
    private String choice1 ;
    private String choice2 ;
    private String choice3 ;
    private String choice4 ;
    private int Solution ;

    public String getChoice1() {
        return choice1;
    }

    public String getQuiz_Id() {
        return Quiz_Id;
    }

    public int getSolution() {
        return Solution;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestion_Id() {
        return Question_Id;
    }

    public void setQuiz_Id(String quiz_Id) {
        Quiz_Id = quiz_Id;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestion_Id(String question_Id) {
        Question_Id = question_Id;
    }

    public void setSolution(int solution) {
        Solution = solution;
    }

    public String ToString() {
        return "\"" + this.Quiz_Id + "\"," +
                "\"" + this.Question_Id + "\"," +
                "\"" + this.question + "\"," +
                "\"" + this.choice1 + "\"," +
                "\"" + this.choice2 + "\"," +
                "\"" + this.choice3 + "\"," +
                "\"" + this.choice4 + "\"," +
                "\"" + this.Solution + "\"" ;
    }

}
