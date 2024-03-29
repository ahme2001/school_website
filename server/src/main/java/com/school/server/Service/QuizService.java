package com.school.server.Service;
import com.school.server.DButil.QuizDB;
import com.school.server.DButil.TeacherDB;
import com.google.gson.Gson;
import com.school.server.gson.QuizQuestions;
import com.school.server.gson.QuizRes;

import com.school.server.gson.TeacherQuizzes;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuizService {
    @Autowired
    private QuizDB quizDB;

    @Autowired
    private TeacherDB teacherDB;

    public String getTeacherInfo(String tId){
        return teacherDB.getClass(tId);
    }

    public boolean setQuiz(String q){
        Gson gson = new Gson();
        QuizQuestions quizQuestions = gson.fromJson(q, QuizQuestions.class);
        return quizDB.uploadQuiz(quizQuestions);
    }

    public String getQuizzes(String input){
        String Qs = quizDB.getQuizzes(input);
        return Qs;
    }
    public String DoQuiz(String qId){
        return quizDB.getQuiz(qId);
    }

    public String calcRes(String input){
        Gson gson  = new Gson();
        QuizRes qr =  gson.fromJson(input, QuizRes.class);
        String qId = qr.getQuizId();
        String sId = qr.getStId();
        int [] ans = qr.getAnswers();
        int [] sol = quizDB.getSolutions(qId,sId,ans.length);
        HashMap<String , String> res = new HashMap<>();
        int grade = 0 ;
        for (int i = 0 ; i < ans.length ; i++){
            if(ans[i]==sol[i])
                grade++;
            if (sol[i]==0)
                return "can't found";
        }
        res.put("grade",Integer.toString(grade));
        res.put("from",Integer.toString(ans.length));
        quizDB.updateDoQuiz(qId,sId,grade);
        return new Gson().toJson(res);
    }

    public String getPrevResult(String StId){
        return quizDB.getPrevResults(StId);
    }
    public String getClassQuizzes(String input){
        Gson gson  = new Gson();
        TeacherQuizzes tq =  gson.fromJson(input, TeacherQuizzes.class);
        String TId = tq.getTeacher_Id();
        String CId = tq.getClassId();
        return quizDB.getClassQuizzes(TId,CId);
    }

    public String getQuizResults(String QId){
        return quizDB.getQuizResults(QId);
    }
}