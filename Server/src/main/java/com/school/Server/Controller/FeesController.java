package com.school.Server.Controller;

import com.school.Server.Service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class FeesController {

    @Autowired
    private FeesService feesService;

    @PostMapping("/staff/set/fees")
    public boolean setYearFees(@RequestBody String input){
        return feesService.setYearFees(input);
    }

    @PostMapping("/parent/get/all/children")
    public String getAllChildren(@RequestBody String input){
        return feesService.getAllChildren(input);
    }

    @PostMapping("/parent/get/child/fees")
    public String getStudentFees(@RequestBody String input){
        return feesService.getStudentFees(input);
    }
}
