package com.school.server;

import com.school.server.DButil.FeesDB;
import com.school.server.DButil.PersonDB;
import com.school.server.DButil.StudentDB;
import com.school.server.Service.FeesService;
import com.school.server.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FeesTest {

    @Autowired
    private FeesService feesService;

    @MockBean
    private FeesDB feesDB;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private PersonDB personDB;

    @Test
    public void testSetYearFees(){
        // Mockup
        Mockito.when(feesDB.insertFeesYear("1","1000")).thenReturn(true);

        // test
        String input = "{\"year\":\"1\",\"fees\":\"1000\"}";
        boolean res = feesService.setYearFees(input);
        assertTrue(res);
    }

    @Test
    public void testGetAllChildrenFees(){
        Vector<String> allChildrenIds = new Vector<>();
        allChildrenIds.add("01010000");
        allChildrenIds.add("01010010");

        Vector<String> allChildrenNames = new Vector<>();
        allChildrenNames.add("str1");
        allChildrenNames.add("ahmed");

        Student str1 = new Student("01010000","10","1","011","02020000");
        Student ahmed = new Student("01010010","10","1","011","02020000");
        // Mockup
        Mockito.when(studentDB.getAllChildrenIds("02020000")).thenReturn(allChildrenIds);
        Mockito.when(personDB.getAllChildrenName(allChildrenIds)).thenReturn(allChildrenNames);
        Mockito.when(studentDB.getStudent("01010000")).thenReturn(str1);
        Mockito.when(studentDB.getStudent("01010010")).thenReturn(ahmed);
        Mockito.when(feesDB.getFees("01")).thenReturn("1000");

        // test
        String input = "{\"id\":\"02020000\"}";
        String res = feesService.getAllChildrenFees(input);
        String Expected = "[" +
                "{\"name\":\"str1\",\"fees\":\"1000\"}," +
                "{\"name\":\"ahmed\",\"fees\":\"1000\"}" +
                "]";
        assertEquals(Expected,res);
    }
}
