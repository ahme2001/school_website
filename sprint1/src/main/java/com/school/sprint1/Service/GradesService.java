package com.school.sprint1.Service;

import com.google.gson.Gson;
import com.school.sprint1.DButil.StudentDB;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradesService {

    public String getIds(String Term){
        Map<String,String> mapStudents = new StudentDB().getStudents(Term);
        if(mapStudents == null) return "No students Were found";
        String res = getGson(mapStudents);
        return  res;
    }
    private String getGson(Map<String,String> mapStudents){
        Map<String,String> map = new HashMap();
        String[] res = new String[mapStudents.size()];
        Gson gson = new Gson();
        int i=0;
        for (Map.Entry<String,String> entry : mapStudents.entrySet()) {
            map.put("id", entry.getKey());
            map.put("name", entry.getValue());
            map.put("grade","");
            res[i++] = gson.toJson(map);
        }
        return gson.toJson(res);
    }
}
