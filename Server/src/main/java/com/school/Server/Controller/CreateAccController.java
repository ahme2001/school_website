package com.school.Server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class CreateAccController {

    @Autowired
    private com.school.Server.Service.CreateAccountService CreateService;

    @PostMapping("/create/student")
    public boolean CreateStudent(@RequestBody String input){
        boolean res = CreateService.run(input,"Student");
        return res;
    }
    @PostMapping("/create/staff")
    public boolean CreateStaff(@RequestBody String input){
        boolean res = CreateService.run(input,"Staff");
        return res;
    }
    @PostMapping("/create/parent")
    public boolean CreateParent(@RequestBody String input){
        boolean res = CreateService.run(input,"Parent");
        return res;
    }
    @PostMapping("/create/teacher")
    public boolean CreateTeacher(@RequestBody String input){
        boolean res = CreateService.run(input,"Teacher");
        return res;
    }
}
