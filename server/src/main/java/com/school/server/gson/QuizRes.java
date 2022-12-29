package com.school.server.gson;

public class QuizRes {
    String StId ;
    String QuizId ;
    int[] answers;

    public int[] getAnswers() {
        return answers;
    }

    public String getQuizId() {
        return QuizId;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

    public void setQuizId(String quizId) {
        QuizId = quizId;
    }

    public String getStId() {
        return StId;
    }

    public void setStId(String stId) {
        StId = stId;
    }
}
