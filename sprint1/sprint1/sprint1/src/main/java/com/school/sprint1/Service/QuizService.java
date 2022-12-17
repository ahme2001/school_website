package com.school.sprint1.Service;
import com.school.sprint1.DButil.QuizDB;
import com.school.sprint1.DButil.TeacherDB;
import com.google.gson.Gson;
import com.school.sprint1.gson.QuizQuestions;
import com.school.sprint1.gson.QuizRes;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.HashMap;

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

    public String getQuizzes(String input){
        String Qs = new QuizDB().getQuizzes(input);
        return Qs;
    }
    public String DoQuiz(String qId){
        return new QuizDB().getQuiz(qId);

    }

    public String calcRes(String input){
        Gson gson  = new Gson();
        QuizRes qr =  gson.fromJson(input, QuizRes.class);
        String qId = qr.getQuizId();
        String sId = qr.getStId();
        int [] ans = qr.getAnswers();
        int [] sol = new QuizDB().getSolutions(qId,sId,ans.length);
        HashMap<String , String> res = new HashMap<>();
        int grade = 0 ;
        for (int i = 0 ; i < ans.length ; i++){
            if(ans[i]==sol[i])
                grade++;
        }
        res.put("grade",Integer.toString(grade));
        res.put("from",Integer.toString(ans.length));
        return new Gson().toJson(res);
    }


}
