package com.school.sprint1.Service;

import com.google.gson.Gson;
import com.school.sprint1.DButil.PersonDB;
import com.school.sprint1.gson.loginPersonInfo;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class loginService {

    public boolean run(String input){
        loginPersonInfo info = getInfo(input);
        String id = info.getId();
        String pass = info.getPass();
        return check(id,pass);
    }


    private boolean check(String id,String pass){
        PersonDB personDB = new PersonDB();
        String passFromDb = personDB.getPass(id);
        return Objects.equals(pass, passFromDb);
    }

    private loginPersonInfo getInfo(String input){
        Gson gson = new Gson();
        return gson.fromJson(input, loginPersonInfo.class);
    }

}
