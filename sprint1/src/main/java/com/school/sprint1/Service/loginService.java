package com.school.sprint1.Service;

import org.springframework.stereotype.Service;

@Service
public class loginService {

    public boolean run(String input){
        String[] input_split = split(input);
        String id = input_split[0];
        String pass = input_split[1];
        return check(id,pass);
    }

    public String[] split(String input){
        String[] input_split = input.split(",");
        return input_split;
    }

    public boolean check(String id,String pass){
        return true;
    }


}
