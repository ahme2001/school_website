package com.school.server;
import com.school.server.DButil.StudentDB;
import com.school.server.Service.UpdateEndTermServer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest

public class testUpdateStudent {
    @Autowired
    private UpdateEndTermServer endTerm  ;

    @MockBean
    private StudentDB studentDB ;
    @Test
    public void testValidIsEnd(){
        // Mockup
        Mockito.when(studentDB.IsEnd()).thenReturn(true);
        // test
        boolean output = endTerm.IsEnd();
        assertEquals(true,output);
    }
    @Test
    public void testValidUpdateTerm(){
        Mockito.when(studentDB.updateEndTerm()).thenReturn(true);
        Mockito.when(studentDB.checkToUpdate(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(studentDB.updateStudentInfo(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);

        // test
        boolean output = endTerm.updateTerm();
        System.out.println(output);
        assertEquals(true,output);
    }

    @Test
    public void testInvalidIsEnd(){
        // Mockup
        Mockito.when(studentDB.IsEnd()).thenReturn(false);
        // test
        boolean output = endTerm.IsEnd();
        assertEquals(false,output);
    }
    @Test
    public void testInvalidUpdateTerm(){
        Mockito.when(studentDB.updateEndTerm()).thenReturn(false);
        Mockito.when(studentDB.checkToUpdate(Mockito.any(),Mockito.any())).thenReturn(false);
        Mockito.when(studentDB.updateStudentInfo(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(false);

        // test
        boolean output = endTerm.updateTerm();
        System.out.println(output);
        assertEquals(false,output);
    }

}
