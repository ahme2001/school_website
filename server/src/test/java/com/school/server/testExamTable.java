package com.school.server;

import com.school.server.DButil.EnrollmentDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.SubDB;
import com.school.server.Service.ExamTableService;
import com.school.server.gson.SubjectsInfo;
import com.school.server.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testExamTable {

    @Autowired
    private ExamTableService examTableService;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private EnrollmentDB enrollmentDB;

    @MockBean
    private SubDB subDB;

    @Test
    public void testGetAllSubjects(){
        List<SubjectsInfo> subjectsInfosList = new ArrayList<>();
        SubjectsInfo subjectsInfo = new SubjectsInfo("english","03102","");
        SubjectsInfo subjectsInfo2 = new SubjectsInfo("french","03103","");
        SubjectsInfo subjectsInfo3 = new SubjectsInfo("algebra","03107","");
        subjectsInfosList.add(subjectsInfo);
        subjectsInfosList.add(subjectsInfo2);
        subjectsInfosList.add(subjectsInfo3);

        //Mockup
        Mockito.when(subDB.getAllSubjects("031")).thenReturn(subjectsInfosList);

        // test
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
        Mockito.when(subDB.updateExamDates(Mockito.any())).thenReturn(true);

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
        Student student = new Student("20010006","1","1","011","20020003");
        Mockito.when(studentDB.getStudent("20010006")).thenReturn(student);

        List<String> studentSubjectsIDs = new ArrayList<>();
        studentSubjectsIDs.add("03102");
        studentSubjectsIDs.add("03103");
        studentSubjectsIDs.add("03107");
        Mockito.when(enrollmentDB.getStudentSubject("20010006")).thenReturn(studentSubjectsIDs);

        List<SubjectsInfo> subjectsInfosList = new ArrayList<>();
        SubjectsInfo subjectsInfo = new SubjectsInfo("english","03102","13-2");
        SubjectsInfo subjectsInfo2 = new SubjectsInfo("french","03103","17-2");
        SubjectsInfo subjectsInfo3 = new SubjectsInfo("algebra","03107","20-2");
        subjectsInfosList.add(subjectsInfo);
        subjectsInfosList.add(subjectsInfo2);
        subjectsInfosList.add(subjectsInfo3);
        Mockito.when(subDB.getSubjectInfo(studentSubjectsIDs)).thenReturn(subjectsInfosList);


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
    public void testGetExamTableWithEmptyId(){
        Mockito.when(studentDB.getStudent("")).thenReturn(null);
        String input = "{\"id\" : \"\"}";
        String res = examTableService.getStudentExamTable(input);
        assertNull(res);
    }

    // if the code isn't exist
    @Test
    public void testGetAllSubjectsWithEmptyCode(){
        Mockito.when(subDB.getAllSubjects("")).thenReturn(new ArrayList<>());
        String input = "{\"code\" : \"\"}";
        String res = examTableService.getSubjects(input);
        assertEquals("[]",res);
    }
}
