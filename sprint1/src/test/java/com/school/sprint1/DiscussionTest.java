package com.school.sprint1;

import com.school.sprint1.DButil.*;
import com.school.sprint1.Service.DiscussionService;
import com.school.sprint1.Service.ProfileService;
import com.school.sprint1.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DiscussionTest {
    @Autowired
    private DiscussionService ds;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private TeacherDB teacherDB;

    @MockBean
    private DiscussionDB discussionDB;


    @Test
    public void testGetTeacherClassesCorrectID(){
        // Mockup
        Mockito.when(teacherDB.getClass("03030000")).thenReturn("{\"Id\":[\"0203\"],\"Name\":[\"2/3\"]}");

        // test
        String res = ds.getTeacherClasses("03030000");
        String trueRes = "{\"Id\":[\"0203\"],\"Name\":[\"2/3\"]}";
        assertEquals(trueRes,res);
    }
    @Test
    public void testGetTeacherClassesWrongID(){
        String classes = "";

        // Mockup
        Mockito.when(teacherDB.getClass("03030001")).thenReturn("NOT FOUND");

        // test
        String res = ds.getTeacherClasses("03030001");
        String trueRes = "NOT FOUND";
        assertEquals(trueRes,res);
    }
    @Test
    public void testAddCorrectPost(){
        // Mockup
        Mockito.when(discussionDB.addPost(Mockito.anyString())).thenReturn(true);

        // test
        Boolean res = ds.addPost("{\"id\":\"\\\"01010000\\\"\",\"content\":\"hello world....\",\"date\":\"25/12/2022\"}");
        assertEquals(true,res);
    }
    @Test
    public void testAddWrongPost(){
        // Mockup
        Mockito.when(discussionDB.addPost(Mockito.anyString())).thenReturn(false);

        // test
        boolean res = ds.addPost("{\"id\":\"\\\"01010003\\\"\",\"content\":\"hello world....\",\"date\":\"25/12/2022\"}");
        assertEquals(false,res);
    }
    @Test
    public void testAddCorrectReply(){
        // Mockup
        Mockito.when(discussionDB.addPost(Mockito.anyString())).thenReturn(true);

        // test
        Boolean res = ds.addPost("{\"id\":\"\\\"01010000\\\"\",\"content\":\"brod aho1\",\"parentPost\":\"0\",\"date\":\"25/12/2022\"}");
        assertEquals(true,res);
    }
    @Test
    public void testAddWrongReply(){
        // Mockup
        Mockito.when(discussionDB.addPost(Mockito.anyString())).thenReturn(false);

        // test
        boolean res = ds.addPost("{\"id\":\"\\\"01010000\\\"\",\"content\":\"brod aho1\",\"parentPost\":\"16\",\"date\":\"25/12/2022\"}");
        assertEquals(false,res);
    }
    @Test
    public void testShowCorrectPosts(){
        // Mockup
        String dataBaseResult = "[{\"date\":\"225/12/2022\",\"number\":2,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"0\",\"reply\":\"{\\\"name\\\":[\\\"mohamed\\\",\\\"mohamed\\\"],\\\"id\\\":[\\\"01010000\\\",\\\"01010000\\\"],\\\"content\\\":[\\\"brod aho1\\\",\\\"brod aho1\\\"]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"10\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"11\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"12\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"13\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":1,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"14\",\"reply\":\"{\\\"name\\\":[\\\"michael\\\"],\\\"id\\\":[\\\"03030000\\\"],\\\"content\\\":[\\\"hello world...\\\"]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":2,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"16\",\"reply\":\"{\\\"name\\\":[\\\"mohamed\\\",\\\"mohamed\\\"],\\\"id\\\":[\\\"01010000\\\",\\\"01010000\\\"],\\\"content\\\":[\\\"brod aho1\\\",\\\"brod aho1\\\"]}\",\"Name\":\"mohamed\"},{\"date\":\"25/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"18\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"3\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"4\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"5\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"6\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"7\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"8\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"},{\"date\":\"225/12/2022\",\"number\":0,\"post\":\"a7aa1\",\"id\":\"01010000\",\"postId\":\"9\",\"reply\":\"{\\\"name\\\":[],\\\"id\\\":[],\\\"content\\\":[]}\",\"Name\":\"mohamed\"}]";
        Mockito.when(discussionDB.getPosts("0203")).thenReturn(dataBaseResult);
        Mockito.when(studentDB.getClass("01010000")).thenReturn("0203");

        // test
        String res = ds.showPosts("{\"id\":\"01010000\"}");
        String resTrue = dataBaseResult;
        assertEquals(resTrue,res);
    }
    @Test
    public void testShowWrongPosts(){
        Mockito.when(discussionDB.getPosts("0204")).thenReturn(null);
        Mockito.when(studentDB.getClass("01010000")).thenReturn("0204");

        // test
        String res = ds.showPosts("{\"id\":\"01010000\"}");
        String resTrue = null;
        assertEquals(resTrue,res);
    }
}
