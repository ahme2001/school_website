package com.school.server.DButil;
import com.google.gson.Gson;
import com.school.server.model.DO_QUIZ;
import com.school.server.model.Question;
import com.school.server.model.Quiz;
import com.school.server.gson.QuizQuestions;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
@Repository
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
        quiz.setTeacher_Id(qq.getTeacher_Id());
        String quizId = quiz.generateId();
        quiz.setQuiz_Id(quizId);
        String[] questions = qq.getQuestions();
        String[] choice1 = qq.getChoice1();
        String[] choice2 = qq.getChoice2();
        String[] choice3 = qq.getChoice3();
        String[] choice4 = qq.getChoice4();
        int[] sol = qq.getAnswers();
        quiz.setMaxGrade(Integer.toString(choice1.length));
        try {
            PreparedStatement statement = connection.prepareStatement("insert into QUIZ values(" + quiz.ToString() + ")");
            statement.execute();
        } catch (SQLException e) {
            return false;
        }
        for (int i =0 ; i< questions.length ; i++){
            q.setQuiz_Id(quizId);
            q.setChoice1(choice1[i]);
            q.setChoice2(choice2[i]);
            q.setChoice3(choice3[i]);
            q.setChoice4(choice4[i]);
            q.setQuestion(questions[i]);
            q.setSolution(sol[i]);
            if(i>9) q.setQuestion_Id(quizId+ i);
            else q.setQuestion_Id(quizId+"0"+i);

            try {
                PreparedStatement statement = connection.prepareStatement("insert into Question values(" + q.ToString() + ")");
                statement.execute();

            } catch (SQLException e) {
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
                    return false;

                }
            }
        }catch (SQLException e) {
            return false;
        }

        return true;
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(Quiz_Id) from QUIZ");
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
            return "can't found";
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
            return "can't found";
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
            PreparedStatement statement = connection.prepareStatement("select Question.Solution from Question , DO_QUIZ where DO_QUIZ.Quiz_Id =Question.Quiz_Id and DO_QUIZ.Quiz_Id ="+qId+" and DO_QUIZ.St_Id=" + sId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                sol[counter++]=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            return new int[len] ;
        }
        return sol;
    }

    public boolean updateDoQuiz(String qId , String sId , int grade){
        try {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
            PreparedStatement statement1 = connection.prepareStatement("update do_quiz set SubmissionTime=\""+ format.format(d) +"\" where DO_QUIZ.Quiz_Id ="+qId+" and DO_QUIZ.St_Id=" + sId);
            PreparedStatement statement2 = connection.prepareStatement("update do_quiz set grade="+ grade +" where DO_QUIZ.Quiz_Id ="+qId+" and DO_QUIZ.St_Id=" + sId);
            statement1.execute();
            statement2.execute();
        } catch (SQLException e) {
            return false;
        }
        return true ;
    }
    public String getPrevResults(String StId ){
        HashMap<String , Object> qs = new HashMap<>();
        ArrayList<String> grade = new ArrayList<>();
        ArrayList<String> maxGrade = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> submissionDate = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select name , DO_QUIZ.grade , SubmissionTime , QUIZ.maxGrade from QUIZ , DO_QUIZ where DO_QUIZ.Quiz_Id =QUIZ.Quiz_Id and DO_QUIZ.grade != -1 and DO_QUIZ.St_Id=" + StId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                maxGrade.add(resultSet.getString(4));
                grade.add(resultSet.getString(2));
                Name.add(resultSet.getString(1));
                submissionDate.add(resultSet.getString(3));
            }
        } catch (SQLException e) {
            return "can't found";
        }
        qs.put("Name",Name);
        qs.put("Grade",grade);
        qs.put("MaxGrade",maxGrade);
        qs.put("Date",submissionDate);
        return new Gson().toJson(qs);
    }
    public String getClassQuizzes(String TId , String CId ){
        HashMap<String , Object> qs = new HashMap<>();
        ArrayList<String> QId = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> endDate = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select Quiz_Id, name , endTime  from QUIZ  where QUIZ.Class_Id="+"\""+CId+"\""+" and QUIZ.teacher_Id="+TId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                QId.add(resultSet.getString(1));
                Name.add(resultSet.getString(2));
                endDate.add(resultSet.getString(3));
            }
        } catch (SQLException e) {
            return "can't found";
        }
        qs.put("QId",QId);
        qs.put("Name",Name);
        qs.put("endDate",endDate);
        return new Gson().toJson(qs);
    }
    public String getQuizResults(String QId ){
        HashMap<String , Object> qResults = new HashMap<>();
        ArrayList<String> LateName = new ArrayList<>();
        ArrayList<String> LateId = new ArrayList<>();
        ArrayList<String> DoneName = new ArrayList<>();
        ArrayList<String> DoneId = new ArrayList<>();
        ArrayList<String> grade = new ArrayList<>();
        ArrayList<String> submissionDate = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("select student.St_Id , person.Name  , DO_QUIZ.grade , SubmissionTime from DO_QUIZ ,person , student where DO_QUIZ.Quiz_Id ="+"\""+QId+"\""+" and DO_QUIZ.grade != -1 and DO_QUIZ.St_Id=student.St_Id and DO_QUIZ.St_Id=person.Id ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                DoneId.add(resultSet.getString(1));
                DoneName.add(resultSet.getString(2));
                grade.add(resultSet.getString(3));
                submissionDate.add(resultSet.getString(4));
            }
        } catch (SQLException e) {
            return "can't found";
        }
        try {
            PreparedStatement statement = connection.prepareStatement("select student.St_Id , person.Name  from DO_QUIZ ,person , student where DO_QUIZ.Quiz_Id ="+"\""+QId+"\""+" and DO_QUIZ.grade = -1 and DO_QUIZ.St_Id=student.St_Id and DO_QUIZ.St_Id=person.Id ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                LateId.add(resultSet.getString(1));
                LateName.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            return "can't found";
        }
        qResults.put("DoneId",DoneId);
        qResults.put("DoneName",DoneName);
        qResults.put("Grade",grade);
        qResults.put("SubmissionTime",submissionDate);
        qResults.put("LateId",LateId);
        qResults.put("LateName",LateName);

        return new Gson().toJson(qResults);
    }
}
