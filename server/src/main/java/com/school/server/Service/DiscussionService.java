package com.school.server.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.server.DButil.DiscussionDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.TeacherDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
public class DiscussionService {
    @Autowired
    private TeacherDB teacherDB;

    @Autowired
    private DiscussionDB discussionDB;

    @Autowired
    private StudentDB studentDB;

    public String getTeacherClasses(String Id){
        return teacherDB.getClass(Id);
    }

    public boolean addPost(String input){
        if(input.length() == 0) return false;
        Map<String, String> postData = getPostInfo(input);
        String values = postValueDatabase(postData);
        return discussionDB.addPost(values);
    }
    public String showPosts(String input){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> postData = gson.fromJson(input, type);
        if(postData.get("classId") == null){
            postData.put("classId", studentDB.getClass(postData.get("id")));
        }
        System.out.println(postData.get("classId"));
        return discussionDB.getPosts(postData.get("classId"));
    }
    private String postValueDatabase(Map<String,String> postData){
        String res = "\"" + postData.get("postId") + "\","
                + postData.get("parentPost") + ","
                + "\"" + postData.get("content") + "\","
                + "\"" + postData.get("date") + "\","
                + postData.get("id") + ","
                + "\"" + postData.get("classId") + "\"";
        return res;
    }
    private Map<String,String> getPostInfo(String input){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> postData = gson.fromJson(input, type);
        if(postData.get("parentPost") == null){
            postData.put("parentPost","null");
        }
        if(postData.get("classId") == null){
            postData.put("classId", studentDB.getClass(postData.get("id")));
        }
        postData.put("postId", Integer.toString(discussionDB.getCount()));
        System.out.println(postData);
        return postData;
    }
}
