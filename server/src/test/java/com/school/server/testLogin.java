package com.school.server;

import com.school.server.DButil.PersonDB;
import com.school.server.Service.loginService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class testLogin {

    @Autowired
    private loginService loginService;

    @MockBean
    private PersonDB personDB;


    @Test
    public void testTruePass(){
        // Mockup
        Mockito.when(personDB.getPass("20010001")).thenReturn("123");

        // test the unit login service
        String input = "{'id':'20010001','pass':'123'}";
        boolean res = loginService.run(input);
        assertEquals(true,res);
    }

    @Test
    public void testWrongPass(){
        Mockito.when(personDB.getPass("20010011")).thenReturn("0126");
        String input = "{'id':'20010011','pass':'123'}";
        boolean res = loginService.run(input);
        assertEquals(false,res);
    }

    @Test
    public void testEnterEmptyPass(){
        Mockito.when(personDB.getPass("20010001")).thenReturn("123");
        String input = "{'id':'20010001','pass':''}";
        boolean res = loginService.run(input);
        assertEquals(false,res);
    }

}
