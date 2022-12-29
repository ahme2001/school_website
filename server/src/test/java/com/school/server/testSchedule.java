package com.school.server;

<<<<<<< HEAD:server/src/test/java/com/school/server/testSchedule.java
import com.school.server.Service.ScheduleService;
=======
import com.school.server.DButil.ClassTableDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.TeacherDB;
import com.school.server.DButil.TeacherTableDB;
import com.school.server.Service.ScheduleService;
import com.school.server.model.ClassTable;
import com.school.server.model.Student;
import com.school.server.model.Teacher;
import com.school.server.model.TeacherTable;
>>>>>>> phase_3:sprint1/src/test/java/com/school/sprint1/testSchedule.java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testSchedule {

    @Autowired
    private ScheduleService scheduleService;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private TeacherDB teacherDB;

    @MockBean
    private ClassTableDB classTableDB;

    @MockBean
    private TeacherTableDB teacherTableDB;

    @Test
    public void testValidSetStudentTable(){
        // Mockup
        Mockito.when(classTableDB.insertTable(Mockito.any())).thenReturn(true);
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
    public void testValidGetStudentTable(){
        Student student = new Student("20010006","1","1","011","20020003");
        Mockito.when(studentDB.getStudent("20010006")).thenReturn(student);

        ClassTable saturday = new ClassTable("1","1","Saturday","arabic","english","math","italy","arabic","algebra");
        ClassTable sunday = new ClassTable("1","1","Sunday","math","arabic","GEO","math","arabic","arabic");
        ClassTable monday = new ClassTable("1","1","Monday","english","Geo","science","italy","arabic","math");
        ClassTable tuesday = new ClassTable("1","1","Tuesday","arabic","algebra","french","arabic","arabic","arabic");
        ClassTable wednesday = new ClassTable("1","1","Wednesday","science","english","arabic","science","arabic","English");
        ClassTable thursday = new ClassTable("1","1","Thursday","french","math","GEO","italy","arabic","arabic");

        ClassTable[] classTable = new ClassTable[]{saturday,sunday,monday,tuesday,wednesday,thursday};
        Mockito.when(classTableDB.getClassTable("1")).thenReturn(classTable);

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
    public void testValidSetTeacherTable(){
        Mockito.when(teacherTableDB.insertTable(Mockito.any())).thenReturn(true);
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
    public void testValidGetTeacherTable(){
        Teacher teacher = new Teacher("20030004","A","math");
        Mockito.when(teacherDB.getTeacher("20030004")).thenReturn(teacher);

        TeacherTable saturday = new TeacherTable("20030004","Saturday","3","5","1","5","5","1");
        TeacherTable sunday = new TeacherTable("20030004","Sunday","1","2","3","4","5","6");
        TeacherTable monday = new TeacherTable("20030004","Monday","1","2","3","4","5","6");
        TeacherTable tuesday = new TeacherTable("20030004","Tuesday","1","2","3","4","5","6");
        TeacherTable wednesday = new TeacherTable("20030004","Wednesday","1","2","3","4","5","6");
        TeacherTable thursday = new TeacherTable("20030004","Thursday","1","2","3","4","5","6");


        TeacherTable[] teacherTables = new TeacherTable[]{saturday,sunday,monday,tuesday,wednesday,thursday};
        Mockito.when(teacherTableDB.getTeacherTable(Mockito.any())).thenReturn(teacherTables);

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

    // test the catch in ClassTableDB by inserting id of student that doesn't exist
    @Test
    public void testNotValidStudentId(){
        Mockito.when(studentDB.getStudent("20010100")).thenReturn(null);
        String input = "{\"id\" : \"20010100\"}";
        String res = scheduleService.getStudentTable(input);
        assertNull(res);
    }

    // test the catch in TeacherTableDB --> enter teacher id that is not exist in teacher
    @Test
    public void testSetNotValidTeacherId(){
        Mockito.when(teacherTableDB.insertTable(Mockito.any())).thenReturn(false);
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
}
