package com.school.sprint1.Controller;

import com.school.sprint1.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/School")
public class DiscussionController {

    @Autowired
    private com.school.sprint1.Service.DiscussionService DiscussionService;

    @PostMapping("/discussion/classes")
    public String  getInfo(@RequestBody String input){
        return DiscussionService.getTeacherClasses(input);
    }
    @PostMapping("/discussion/post")
//    {"id":"\"01010000\"","content":"jkdjfzc ?","date":"2022/12/25"}
//    {"id":"\"0101000\"","content":"brod aho","parentPost":"0","date":"2022/12/25"}
    public boolean CreatePost(@RequestBody String input){
        boolean res = DiscussionService.addPost(input);
        return res;
    }
    @PostMapping("/discussion/post")
//    {"id":"\"01010000\"","content":"jkdjfzc ?","date":"2022/12/25"}
//    {"id":"\"0101000\"","content":"brod aho","parentPost":"0","date":"2022/12/25"}
    public String ShowPost(@RequestBody String input){
        String res = DiscussionService.showPosts(input);
        return res;
    }
}
