package com.school.sprint1.Controller;

import com.school.sprint1.Service.GradesService;
import com.school.sprint1.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @PostMapping("/grades/IDs")
    public String test(@RequestBody String input){
//        System.out.println(input);
        String res = gradesService.getIds(input);
//        System.out.println(res);
        return res;
    }
}