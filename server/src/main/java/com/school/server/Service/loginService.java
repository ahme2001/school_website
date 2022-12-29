package com.school.server.Service;

import com.google.gson.Gson;
import com.school.server.DButil.PersonDB;
import com.school.server.gson.loginPersonInfo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class loginService {

    @Autowired
    private PersonDB personDB;

    public boolean run(String input){
        loginPersonInfo info = getInfo(input);
        String id = info.getId();
        String pass = info.getPass();
        return check(id,pass);
    }


    private boolean check(String id,String pass){
        String passFromDb = personDB.getPass(id);
        return Objects.equals(pass, passFromDb);
    }

    private loginPersonInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input, loginPersonInfo.class);
    }

}
