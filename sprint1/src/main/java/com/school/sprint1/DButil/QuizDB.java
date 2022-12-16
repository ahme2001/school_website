package com.school.sprint1.DButil;
import com.school.sprint1.model.DO_QUIZ;
import com.school.sprint1.model.Question;
import com.school.sprint1.model.Quiz;
import gson.QuizQuestions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class QuizDB {
    private Connection connection;

    public QuizDB(){
        connection=DButil.getConnection();
    }
    public boolean uploadQuiz(QuizQuestions qq ){
        Question q = new Question();
        Quiz quiz = new Quiz();
        quiz.setClass_Id(qq.getClassId());
        quiz.setEndTime(qq.getEndDate());
        quiz.setName(qq.getQname());
        String quizId = quiz.getQuiz_Id();
        quiz.setQuiz_Id(quizId);
        try {
            PreparedStatement statement = connection.prepareStatement("insert into QUIZ values(" + quiz.ToString() + ")");
            statement.execute();

        } catch (SQLException e) {
            return false;
        }
        String[] questions = qq.getQuestions();
        String[] choice1 = qq.getChoice1();
        String[] choice2 = qq.getChoice2();
        String[] choice3 = qq.getChoice3();
        String[] choice4 = qq.getChoice4();
        int[] sol = qq.getAnswers();
        for (int i =0 ; i< questions.length ; i++){
            q.setQuiz_Id(quizId);
            q.setChoice1(choice1[i]);
            q.setChoice2(choice2[i]);
            q.setChoice3(choice3[i]);
            q.setChoice4(choice4[i]);
            q.setQuestion(questions[i]);
            q.setSolution(sol[i]);
            q.setQuestion_Id(quizId+ i);
            try {
                PreparedStatement statement = connection.prepareStatement("insert into Question values(" + q.ToString() + ")");
                statement.execute();

            } catch (SQLException e) {
                return false;
            }
        }
        return announceAll("qId" , qq.getClassId());
    }
    public boolean announceAll(String qId , String classId){
        try {
            PreparedStatement statement = connection.prepareStatement("select St_Id from STUDENT where St_Id = " + classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String stId = resultSet.getString(1);
                try {
                    DO_QUIZ doq = new DO_QUIZ(qId,stId);
                    PreparedStatement statement2 = connection.prepareStatement("insert into DO_QUIZ values(" + doq.ToString() + ")");
                    statement2.execute();

                } catch (SQLException e) {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        }

         return true;
    }
    public int getCount(String class_id) {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(Quiz_Id) from QUIZ where Class_Id = " + class_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        }
        return count;
    }
}
