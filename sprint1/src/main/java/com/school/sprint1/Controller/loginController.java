package com.school.sprint1.Controller;

import com.school.sprint1.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class loginController {

    @Autowired
    private loginService loginService;

    @PostMapping("/login")
    public boolean test(@RequestBody String input){
        return loginService.run(input);
    }
}
