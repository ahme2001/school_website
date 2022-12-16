package com.school.sprint1.Service;
import com.school.sprint1.DButil.QuizDB;
import com.school.sprint1.DButil.TeacherDB;
import com.google.gson.Gson;
import gson.QuizQuestions;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service
public class QuizService {
    private Connection connection;

    public String getInfo(String tId){
      return new TeacherDB().getClass(tId);
    }

    public boolean setQuiz(String q){
        Gson gson = new Gson();
        QuizQuestions quizQuestions = gson.fromJson(q, QuizQuestions.class);
        return new QuizDB().uploadQuiz(quizQuestions);
    }

}
