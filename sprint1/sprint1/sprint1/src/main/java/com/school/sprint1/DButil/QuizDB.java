package com.school.sprint1.DButil;
import com.google.gson.Gson;
import com.school.sprint1.model.DO_QUIZ;
import com.school.sprint1.model.Question;
import com.school.sprint1.model.Quiz;
import com.school.sprint1.gson.QuizQuestions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        String quizId = quiz.generateId();
        quiz.setQuiz_Id(quizId);
        try {
//            System.out.println(quiz.ToString());
            PreparedStatement statement = connection.prepareStatement("insert into QUIZ values(" + quiz.ToString() + ")");
            statement.execute();

        } catch (SQLException e) {
            System.out.println("4444444444");
            return false;
        }
//        System.out.println(qq.ToString());
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
//                System.out.println(q.ToString());
                PreparedStatement statement = connection.prepareStatement("insert into Question values(" + q.ToString() + ")");
                statement.execute();

            } catch (SQLException e) {
                System.out.println("3333333");
                return false;
            }
        }
        return announceAll(quizId , qq.getClassId());
    }
    public boolean announceAll(String qId , String classId){
        try {
            PreparedStatement statement = connection.prepareStatement("select St_Id from STUDENT where Class_Id = " + classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String stId = resultSet.getString(1);
                try {
                    DO_QUIZ doq = new DO_QUIZ(qId,stId);
                    System.out.println( doq.ToString());
                    PreparedStatement statement2 = connection.prepareStatement("insert into DO_QUIZ values(" + doq.ToString() + ")");
                    statement2.execute();

                } catch (SQLException e) {
                    System.out.println("1111");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("2222");
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
    public String getQuizzes(String StId){
        HashMap<String , Object> qs = new HashMap<>();

        ArrayList<String> Id = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select QUIZ.Quiz_Id , name from QUIZ , DO_QUIZ where DO_QUIZ.Quiz_Id =QUIZ.Quiz_Id and DO_QUIZ.grade = -1 and DO_QUIZ.St_Id=" + StId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Id.add(resultSet.getString(1));
                Name.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("2222");
            return "can't found";
        }
//        qs.setId((String[]) Id.toArray());
//        qs.setName((String[]) Name.toArray());
        qs.put("Id",Id);
        qs.put("Name",Name);
        return new Gson().toJson(qs);
    }
    public String getQuiz(String qId){
        HashMap<String , Object> qq = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> choice1 = new ArrayList<>();
        ArrayList<String> choice2 = new ArrayList<>();
        ArrayList<String> choice3 = new ArrayList<>();
        ArrayList<String> choice4 = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from QUIZ  where Quiz_Id = " + qId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                qq.put("Qname",resultSet.getString(2));
                qq.put("endDate",resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("2222");
            return "can't do it ";
        }
        try {
            PreparedStatement statement = connection.prepareStatement("select Question , choice1 , choice2 , choice3 , choice4  from Question  where Quiz_Id = " + qId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                questions.add(resultSet.getString(1));
                choice1.add(resultSet.getString(2));
                choice2.add(resultSet.getString(3));
                choice3.add(resultSet.getString(4));
                choice4.add(resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("2222");
            return "can't do it ";
        }
        qq.put("questions",questions);
        qq.put("choice1",choice1);
        qq.put("choice2",choice2);
        qq.put("choice3",choice3);
        qq.put("choice4",choice4);
        return new Gson().toJson(qq);
    }

    public int[] getSolutions(String qId , String sId , int len){
        int[] sol = new int[len];
        int counter = 0;
        try {
            System.out.println(qId + "  " +sId);
            PreparedStatement statement = connection.prepareStatement("select Question.Solution from Question , DO_QUIZ where DO_QUIZ.Quiz_Id =Question.Quiz_Id and DO_QUIZ.Quiz_Id ="+qId+" and DO_QUIZ.St_Id=" + sId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
                sol[counter++]=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("2222");
            return new int[len] ;
        }
        System.out.println(Arrays.toString(sol));
        return sol;
    }
}
