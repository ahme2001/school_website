package com.school.sprint1.Controller;

import com.school.sprint1.Service.ProfileService;
import com.school.sprint1.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class profileController {
    @Autowired
    private com.school.sprint1.Service.ProfileService profileService;

    @PostMapping("/profile")
    public String getProfile(@RequestBody String ID) {
        String res = profileService.run(ID);
        return res;
    }
}
