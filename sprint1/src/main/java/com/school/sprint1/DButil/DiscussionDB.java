package com.school.sprint1.DButil;

import com.google.gson.Gson;
import org.yaml.snakeyaml.events.Event;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DiscussionDB {
    private Connection connection;

    public DiscussionDB() {
        connection = DButil.getConnection();
    }
    public int getCount() {
        int count=0;
        try {
            PreparedStatement statement = connection.prepareStatement("select count(post_id) from Discussion");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
        return count;
    }
    public boolean addPost(String values){
        try {
            System.out.println("insert into Discussion values(" + values + ")");
            PreparedStatement statement = connection.prepareStatement("insert into Discussion values(" + values + ")");
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public String getPosts(String classID){
        HashMap<String , Object> res = new HashMap<>();
        ArrayList<String> Ids=new ArrayList<>();
        ArrayList<String> Names =new ArrayList<>();
        ArrayList<String> postIDs =new ArrayList<>();
        ArrayList<String> Contents =new ArrayList<>();
        ArrayList<String> dates =new ArrayList<>();
        ArrayList<HashMap<String,Object>> Posts =new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT d.person_id , p.Name , d.post_id , d.content , d.post_date FROM Discussion d Join PERSON p ON d.person_id = p.Id WHERE d.parent_post_id IS NULL AND class_id = \"" + classID + "\"");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                res.put("id", resultSet.getString(1));
                res.put("Name", resultSet.getString(2));
                res.put("postId", resultSet.getString(3));
                res.put("post", resultSet.getString(4));
                res.put("date", resultSet.getString(5));
                PreparedStatement statement_reply = connection.prepareStatement("SELECT d.person_id , p.Name , d.content FROM Discussion d Join PERSON p ON d.person_id = p.Id WHERE d.parent_post_id = \"" + res.get("postId") + "\"");
                ResultSet resultSet_reply = statement_reply.executeQuery();
                int count = 0;
                while(resultSet_reply.next()){
                    count++;
                    Ids.add(resultSet_reply.getString(1));
                    Names.add(resultSet_reply.getString(2));
                    Contents.add(resultSet_reply.getString(3));

                }
                res.put("number", count);
                HashMap<String , Object> reply = new HashMap<>();
                reply.put("id",Ids);
                reply.put("name",Names);
                reply.put("content",Contents);
                res.put("reply",new Gson().toJson(reply));
                System.out.println("ylaaaaaaaaaaaaaaaaaa");
                System.out.println(res);
                Posts.add(res);
                res = new HashMap<>();
                Ids = new ArrayList<>();
                Names =new ArrayList<>();
                Contents = new ArrayList<>();
                System.out.println(Posts);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        System.out.println(new Gson().toJson(Posts));
        return new Gson().toJson(Posts);
    }
}
