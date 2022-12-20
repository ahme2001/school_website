package com.school.sprint1;

import com.school.sprint1.Service.loginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class testLogin {

    @Test
    public void test1(){
        loginService ls = new loginService();
        boolean res = ls.run("20010001,123");
        assertEquals(true,res);
    }

    @Test
    public void test2(){
        loginService ls = new loginService();
        boolean res = ls.run("20010011,123");
        assertEquals(false,res);
    }

    @Test
    public void test3(){
        loginService ls = new loginService();
        boolean res = ls.run("20010011,123");
        assertEquals(false,res);
    }

    @Test
    public void test4(){
        loginService ls = new loginService();
        boolean res = ls.run("20010011,123");
        assertEquals(false,res);
    }

    @Test
    public void test5(){
        loginService ls = new loginService();
        boolean res = ls.run("20010002,pass");
        assertEquals(true,res);
    }

    @Test
    public void test6(){
        loginService ls = new loginService();
        boolean res = ls.run("20010003,test");
        assertEquals(true,res);
    }

    @Test
    public void test7(){
        loginService ls = new loginService();
        boolean res = ls.run("20010004,MpnA012");
        assertEquals(true,res);
    }

    @Test
    public void test8(){
        loginService ls = new loginService();
        boolean res = ls.run("20010005,GHDGFDGFDHBFGH5");
        assertEquals(true,res);
    }

    @Test
    public void test9(){
        loginService ls = new loginService();
        boolean res = ls.run("20110005,GHDGFDGFDHBFGH5");
        assertEquals(false,res);
    }

    @Test
    public void test10(){
        loginService ls = new loginService();
        boolean res = ls.run("20010005,theID");
        assertEquals(false,res);
    }

    @Test
    public void test11(){
        loginService ls = new loginService();
        boolean res = ls.run("20110004,test");
        assertEquals(false,res);
    }

    @Test
    public void test12(){
        loginService ls = new loginService();
        boolean res = ls.run("20110007,test");
        assertEquals(false,res);
    }
}
