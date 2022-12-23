package com.school.Server;

import com.school.Server.Service.ExamTableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testExamTable {

    @Test
    public void testGetAllSubjects(){
        ExamTableService examTableService = new ExamTableService();
        String input = "{\"code\" : \"031\"}";
        String res = examTableService.getSubjects(input);
        String Expected = "[" +
                "{\"subject\":\"english\",\"id\":\"03102\",\"date\":\"\"}," +
                "{\"subject\":\"french\",\"id\":\"03103\",\"date\":\"\"}," +
                "{\"subject\":\"algebra\",\"id\":\"03107\",\"date\":\"\"}" +
                "]";
        assertEquals(Expected,res);
    }

    @Test
    public void testSetExamTable(){
        ExamTableService examTableService = new ExamTableService();
        String input = "[" +
                "{\"subject\":\"english\",\"id\":\"03102\",\"date\":\"13-2\"}," +
                "{\"subject\":\"french\",\"id\":\"03103\",\"date\":\"17-2\"}," +
                "{\"subject\":\"algebra\",\"id\":\"03107\",\"date\":\"20-2\"}" +
                "]";
        boolean res = examTableService.setStudentExamTable(input);
        assertTrue(res);
    }

    @Test
    public void testGetExamTable(){
        ExamTableService examTableService = new ExamTableService();
        String input = "{\"id\" : \"20010006\"}";
        String res = examTableService.getStudentExamTable(input);
        String Expected = "[" +
                "{\"subject\":\"english\",\"id\":\"03102\",\"date\":\"13-2\"}," +
                "{\"subject\":\"french\",\"id\":\"03103\",\"date\":\"17-2\"}," +
                "{\"subject\":\"algebra\",\"id\":\"03107\",\"date\":\"20-2\"}" +
                "]";
        assertEquals(Expected,res);
    }

    // if the input id was empty
    @Test
    public void testGetExamTable2(){
        ExamTableService examTableService = new ExamTableService();
        String input = "{\"id\" : \"\"}";
        String res = examTableService.getStudentExamTable(input);
        assertNull(res);
    }

    // if the code isn't exist
    @Test
    public void testGetAllSubjects2(){
        ExamTableService examTableService = new ExamTableService();
        String input = "{\"code\" : \"\"}";
        String res = examTableService.getSubjects(input);

        assertEquals("[]",res);
    }
}
