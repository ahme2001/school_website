package com.school.server.Controller;

import com.school.server.Service.FeesService;
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

    @PostMapping("/parent/get/children/fees")
    public String getAllChildrenFees(@RequestBody String input){ return feesService.getAllChildrenFees(input); }
}
