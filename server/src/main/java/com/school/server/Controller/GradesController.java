package com.school.server.Controller;

import com.school.server.Service.GradesService;
import com.school.server.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @PostMapping("/grades/IDs")
    public String getIDs(@RequestBody String input){
        String res = gradesService.getIds(input);
        return res;
    }
    @PostMapping("/grades/range")
    public String getRange(@RequestBody String Id){
        String res = gradesService.getTermRange(Id);
        return res;
    }
//    {"subject":"05103","students":[{"id":"01010001","name":"","grade":"50"}]}
    @PostMapping("/grades/save")
    public boolean saveGrades(@RequestBody String input){
        boolean res = gradesService.saveGrades(input);
        return res;
    }

    @PostMapping("/grades/show")
    public String showGrades(@RequestBody String input){
        String res = gradesService.showGrades(input);
        return res;
    }
}