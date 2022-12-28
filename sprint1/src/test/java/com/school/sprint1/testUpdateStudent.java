package com.school.sprint1;
import com.google.gson.Gson;
import com.school.sprint1.DButil.QuizDB;
import com.school.sprint1.DButil.StudentDB;
import com.school.sprint1.DButil.TeacherDB;
import com.school.sprint1.Service.QuizService;
import com.school.sprint1.Service.updateEndTerm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest

public class testUpdateStudent {
    @Autowired
    private updateEndTerm endTerm  ;

    @MockBean
    private StudentDB studentDB ;

    @Test
    public void testGetTeacherInfoWithValidId(){
        Mockito.when(studentDB.updateEndTerm()).thenReturn(true);
        Mockito.when(studentDB.checkToUpdate(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(studentDB.updateStudentInfo(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);

        // test
        boolean output = endTerm.updateTerm();
        System.out.println(output);
        assertEquals(true,output);
    }



}
