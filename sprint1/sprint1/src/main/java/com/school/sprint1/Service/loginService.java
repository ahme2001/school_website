package com.school.sprint1.Service;

import com.school.sprint1.DButil.PersonDB;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class loginService {

    public boolean run(String input){
        String[] input_split = split(input);
        String id = input_split[0];
        String pass = input_split[1];
        return check(id,pass);
    }

    private String[] split(String input){
        String[] input_split = input.split(",");
        return input_split;
    }

    private boolean check(String id,String pass){
        PersonDB personDB = new PersonDB();
        String passFromDb = personDB.getPass(id);
        if(Objects.equals(pass, passFromDb)) return true;
        return false;
    }


}
