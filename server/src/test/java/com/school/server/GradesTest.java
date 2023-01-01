package com.school.server;

import com.school.server.DButil.StaffDB;
import com.school.server.DButil.StudentDB;
import com.school.server.Service.GradesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GradesTest {

    @Autowired
    private GradesService gs;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private StaffDB staffDB;

    @Test
    public void testGetAllStudentsOnGivenTerm(){
        Map<String,String> mapStudents = new HashMap<>();
        mapStudents.put("01010001","toto");
        mapStudents.put("01010000","fofo");
        // Mockup
        Mockito.when(studentDB.getStudents("051")).thenReturn(mapStudents);

        // test
        String res = gs.getIds("051");
        String trueRes = "[\"{\\\"grade\\\":\\\"\\\",\\\"name\\\":\\\"toto\\\",\\\"id\\\":\\\"01010001\\\"}\",\"{\\\"grade\\\":\\\"\\\",\\\"name\\\":\\\"fofo\\\",\\\"id\\\":\\\"01010000\\\"}\"]";
        assertEquals(trueRes,res);
    }

    @Test
    public void testEmptyInputTerm(){
        String res = gs.getIds("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }

    @Test
    public void testNotInDBTerm(){
        Map<String,String> mapStudent =new HashMap();
        // Mockup
        Mockito.when(studentDB.getStudents("055")).thenReturn(mapStudent);

        // test
        String res = gs.getIds("055");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetTermRangeWithCorrectId(){
        Map<String, String> termRange = new HashMap<>();
        termRange.put("startyear","031");
        termRange.put("currentyear","051");
        // Mockup
        Mockito.when(studentDB.getTermRange("01010000")).thenReturn(termRange);

        // test
        String res = gs.getTermRange("01010000");
        String trueRes = "{\"currentyear\":\"05\",\"startyear\":\"03\"}";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetTermRangeWithEmptyId(){
        Map<String, String> termRange = new HashMap<>();
        // Mockup
        Mockito.when(studentDB.getTermRange("")).thenReturn(termRange);

        // test
        String res = gs.getTermRange("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetTermRangeWithWrongFormatId(){
        Map<String, String> termRange = new HashMap<>();
        // Mockup
        Mockito.when(studentDB.getTermRange("11")).thenReturn(termRange);

        // test
        String res = gs.getTermRange("11");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }

    @Test
    public void testSaveStudentGradesWithValidInput(){
        Map<String,String> momo = new HashMap<>();
        momo.put("startyear","011");
        momo.put("currentyear","051");
        // Mockup
        Mockito.when(studentDB.getTermRange(Mockito.anyString())).thenReturn(momo);
        Mockito.when(staffDB.addGrade(Mockito.anyString())).thenReturn(true);

        // test
        boolean res = gs.saveGrades("{\"subject\":\"05103\",\"students\":[{\"id\":\"01010001\",\"name\":\"momo\",\"grade\":\"50\"}]}");
        assertEquals(true,res);
    }

    @Test
    public void testSaveStudentGradesWithEmptyInput(){
        Map<String,String> momo = new HashMap<>();
        // Mockup
        Mockito.when(studentDB.getTermRange("")).thenReturn(momo);
        Mockito.when(staffDB.addGrade(Mockito.anyString())).thenReturn(false);

        // test
        boolean res = gs.saveGrades("");
        assertEquals(false,res);
    }

    @Test
    public void testShowGradesWithValidId(){
        Map<String, String> grades = new HashMap<>();
        grades.put("arabic","40");
        grades.put("activities","89");
        grades.put("science","30");
        grades.put("english","60");
        grades.put("algebra","100");
        grades.put("geometry","55");
        grades.put("math","20");
        grades.put("social studies","90");
        // Mockup
        Mockito.when(studentDB.getTermGrades("01010000","0510")).thenReturn(grades);

        // test
        String res = gs.showGrades("{\"id\":\"01010000\",\"term\":\"0510\"}");
        String trueRes = "[\"{\\\"subject\\\":\\\"arabic\\\",\\\"grade\\\":\\\"40\\\"}\",\"{\\\"subject\\\":\\\"activities\\\",\\\"grade\\\":\\\"89\\\"}\",\"{\\\"subject\\\":\\\"science\\\",\\\"grade\\\":\\\"30\\\"}\",\"{\\\"subject\\\":\\\"english\\\",\\\"grade\\\":\\\"60\\\"}\",\"{\\\"subject\\\":\\\"algebra\\\",\\\"grade\\\":\\\"100\\\"}\",\"{\\\"subject\\\":\\\"geometry\\\",\\\"grade\\\":\\\"55\\\"}\",\"{\\\"subject\\\":\\\"math\\\",\\\"grade\\\":\\\"20\\\"}\",\"{\\\"subject\\\":\\\"social studies\\\",\\\"grade\\\":\\\"90\\\"}\"]";
        assertEquals(trueRes,res);
    }

    @Test
    public void testShowGradesWithEmptyId(){
        String res = gs.showGrades("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
}
