package com.school.server.Controller;

import com.school.server.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class loginController {

    @Autowired
    private loginService loginService;

    @PostMapping("/login")
    public boolean login(@RequestBody String input){
//        System.out.println(input);
        boolean res = loginService.run(input);
//        System.out.println(res);
        return res;
    }
}
