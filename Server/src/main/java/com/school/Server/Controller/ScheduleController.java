package com.school.Server.Controller;

import com.school.Server.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/student/get/table")
    public String getStudentTable(@RequestBody String input){
        return scheduleService.getStudentTable(input);
    }

    @PostMapping("/staff/set/student-table")
    public boolean setStudentTable(@RequestBody String input){
        return scheduleService.setStudentTable(input);
    }

    @PostMapping("/teacher/get/table")
    public String getTeacherTable(@RequestBody String input){
        return scheduleService.getTeacherTable(input);
    }

    @PostMapping("/staff/set/teacher-table")
    public boolean setTeacherTable(@RequestBody String input){
        return scheduleService.setTeacherTable(input);
    }
}
