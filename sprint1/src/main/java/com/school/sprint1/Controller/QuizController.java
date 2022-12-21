package com.school.sprint1.Controller;
import com.school.sprint1.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class QuizController {
    @Autowired
    private com.school.sprint1.Service.CreateAccountService CreateService;
    @PostMapping("getInfo")
    public String  getInfo(@RequestBody String input){
        return new QuizService().getTeacherInfo(input);
    }
    @PostMapping("setQuiz")
    public boolean setQuiz(@RequestBody String input){
        return new QuizService().setQuiz(input);
    }

    @PostMapping("Quizzes")
    public String Quizzes(@RequestBody String input){
        return new QuizService().getQuizzes(input);
    }

    @PostMapping("DoQuiz")
    public String DoQuiz(@RequestBody String input){
        return new QuizService().DoQuiz(input);
    }
    @PostMapping("getResult")
    public String getResult(@RequestBody String input){
        return new QuizService().calcRes(input);
    }



}
