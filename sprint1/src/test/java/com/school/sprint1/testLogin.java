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
        String input = "{'id':'20010001','pass':'123'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test2(){
        loginService ls = new loginService();
        String input = "{'id':'20010011','pass':'123'}";
        boolean res = ls.run(input);
        assertEquals(false,res);
    }


    @Test
    public void test3(){
        loginService ls = new loginService();
        String input = "{'id':'20010002','pass':'pass'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test4(){
        loginService ls = new loginService();
        String input = "{'id':'20010003','pass':'test'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test5(){
        loginService ls = new loginService();
        String input = "{'id':'20010004','pass':'MpnA012'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test6(){
        loginService ls = new loginService();
        String input = "{'id':'20010005','pass':'GHDGFDGFDHBFGH5'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test7(){
        loginService ls = new loginService();
        String input = "{'id':'20110005','pass':'GHDGFDGFDHBFGH5'}";
        boolean res = ls.run(input);
        assertEquals(false,res);
    }

    @Test
    public void test8(){
        loginService ls = new loginService();
        String input = "{'id':'20010005','pass':'theID'}";
        boolean res = ls.run(input);
        assertEquals(false,res);
    }

    @Test
    public void test9(){
        loginService ls = new loginService();
        String input = "{'id':'20010006','pass':'g,t-+*v12TN'}";
        boolean res = ls.run(input);
        assertEquals(true,res);
    }

    @Test
    public void test10(){
        loginService ls = new loginService();
        String input = "{'id':'20010006','pass':'g,t+*v12TN'}";
        boolean res = ls.run(input);
        assertEquals(false,res);
    }

}
