package com.school.sprint1.Service;

import com.google.gson.Gson;
import com.school.sprint1.DButil.PersonDB;
import com.school.sprint1.gson.loginInfo;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class loginService {

    public boolean run(String input){
        loginInfo info = getInfo(input);
        String id = info.getId();
        String pass = info.getPass();
        System.out.println(id + " " + pass);
        return check(id,pass);
    }


    private boolean check(String id,String pass){
        PersonDB personDB = new PersonDB();
        String passFromDb = personDB.getPass(id);
        return Objects.equals(pass, passFromDb);
    }

    private loginInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input, loginInfo.class);
    }

}
