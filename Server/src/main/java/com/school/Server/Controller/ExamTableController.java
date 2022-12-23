package com.school.Server.Controller;


import com.school.Server.Service.ExamTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class ExamTableController {

    @Autowired
    private ExamTableService examTableService;


    @PostMapping("/student/get/all-subjects")
    public String getSubjects(@RequestBody String input){
        return examTableService.getSubjects(input);
    }


    @PostMapping("/student/get/exam-table")
    public String getExamTable(@RequestBody String input){
        return examTableService.getStudentExamTable(input);
    }

    @PostMapping("/staff/set/exam-table")
    public boolean setExamTable(@RequestBody String input){
        return examTableService.setStudentExamTable(input);
    }
}
