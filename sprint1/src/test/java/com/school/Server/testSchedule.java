package com.school.Server;

import com.school.Server.Service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testSchedule {

    @Test
    public void testSetStudentTable(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"classId\":1," +
                "\"termId\":1," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"english\",\"lec3\":\"math\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"algebra\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"math\",\"lec2\":\"arabic\",\"lec3\":\"GEO\",\"lec4\":\"math\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"english\",\"lec2\":\"Geo\",\"lec3\":\"science\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"math\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"algebra\",\"lec3\":\"french\",\"lec4\":\"arabic\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"science\",\"lec2\":\"english\",\"lec3\":\"arabic\",\"lec4\":\"science\",\"lec5\":\"arabic\",\"lec6\":\"English\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"french\",\"lec2\":\"math\",\"lec3\":\"GEO\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}" +
                "]}";
        boolean res = scheduleService.setStudentTable(input);
        assertTrue(res);
    }

    @Test
    public void testGetStudentTable(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"id\" : \"20010006\"}";
        String res = scheduleService.getStudentTable(input);
        String Expected = "{\"classId\":\"1\"," +
                "\"termId\":\"1\"," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"english\",\"lec3\":\"math\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"algebra\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"math\",\"lec2\":\"arabic\",\"lec3\":\"GEO\",\"lec4\":\"math\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"english\",\"lec2\":\"Geo\",\"lec3\":\"science\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"math\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"algebra\",\"lec3\":\"french\",\"lec4\":\"arabic\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"science\",\"lec2\":\"english\",\"lec3\":\"arabic\",\"lec4\":\"science\",\"lec5\":\"arabic\",\"lec6\":\"English\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"french\",\"lec2\":\"math\",\"lec3\":\"GEO\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}" +
                "]}";
        assertEquals(Expected,res);
    }

    @Test
    public void testSetTeacherTable(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"Teacher_Id\":\"20030004\"," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"3\",\"lec2\":\"5\",\"lec3\":\"1\",\"lec4\":\"5\",\"lec5\":\"5\",\"lec6\":\"1\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}" +
                "]}";

        boolean res = scheduleService.setTeacherTable(input);
        assertTrue(res);
    }

    @Test
    public void testGetTeacherTable(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"id\" : \"20030004\"}";

        String res = scheduleService.getTeacherTable(input);
        String Expected = "{\"Teacher_Id\":\"20030004\"," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"3\",\"lec2\":\"5\",\"lec3\":\"1\",\"lec4\":\"5\",\"lec5\":\"5\",\"lec6\":\"1\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}" +
                "]}";
        assertEquals(Expected,res);
    }

    // test the catch in StudentTableDB --> enter class id that is not exist in class
    @Test
    public void testSetStudentTable2(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"classId\":5," +
                "\"termId\":1," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"english\",\"lec3\":\"math\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"algebra\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"math\",\"lec2\":\"arabic\",\"lec3\":\"GEO\",\"lec4\":\"math\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"english\",\"lec2\":\"Geo\",\"lec3\":\"science\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"math\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"arabic\",\"lec2\":\"algebra\",\"lec3\":\"french\",\"lec4\":\"arabic\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"science\",\"lec2\":\"english\",\"lec3\":\"arabic\",\"lec4\":\"science\",\"lec5\":\"arabic\",\"lec6\":\"English\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"french\",\"lec2\":\"math\",\"lec3\":\"GEO\",\"lec4\":\"italy\",\"lec5\":\"arabic\",\"lec6\":\"arabic\"}}" +
                "]}";
        boolean res = scheduleService.setStudentTable(input);
        assertFalse(res);
    }

    // test the catch in ClassTableDB by inserting id of student that doesn't exist
    @Test
    public void testGetStudentTable2(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"id\" : \"20010100\"}";
        String res = scheduleService.getStudentTable(input);
        assertNull(res);
    }

    // test the catch in TeacherTableDB --> enter teacher id that is not exist in teacher
    @Test
    public void testSetTeacherTable2(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"Teacher_Id\":\"20030152\"," +
                "\"days\":[" +
                "{\"Day\":\"Saturday\",\"lectures\":{\"lec1\":\"3\",\"lec2\":\"5\",\"lec3\":\"1\",\"lec4\":\"5\",\"lec5\":\"5\",\"lec6\":\"1\"}}," +
                "{\"Day\":\"Sunday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Monday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Tuesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Wednesday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}," +
                "{\"Day\":\"Thursday\",\"lectures\":{\"lec1\":\"1\",\"lec2\":\"2\",\"lec3\":\"3\",\"lec4\":\"4\",\"lec5\":\"5\",\"lec6\":\"6\"}}]}";

        boolean res = scheduleService.setTeacherTable(input);
        assertFalse(res);
    }

    // test the catch in TeacherTableDB by inserting id of teacher that doesn't exist
    @Test
    public void testGetTeacherTable2(){
        ScheduleService scheduleService = new ScheduleService();
        String input = "{\"id\" : \"20030152\"}";
        String res = scheduleService.getTeacherTable(input);
        assertNull(res);
    }
}
