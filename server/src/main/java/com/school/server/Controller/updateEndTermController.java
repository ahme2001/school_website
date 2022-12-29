package com.school.server.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class updateEndTermController {
    @Autowired
    private com.school.server.Service.UpdateEndTermServer updateEndTerm;
    @PostMapping("IsEnd")
    public boolean  getInfo(){
        return updateEndTerm.IsEnd();
    }
    @PostMapping("update")
    public boolean setQuiz(){return updateEndTerm.updateTerm();}
}
