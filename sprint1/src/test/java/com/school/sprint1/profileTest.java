package com.school.sprint1;

import com.school.sprint1.Service.ProfileService;
import com.school.sprint1.Service.loginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class profileTest {
    @Test
    public void test1(){
        ProfileService ps = new ProfileService();
        String res = ps.run("01010000");
        String trueRes = "{\"Class_Id\":\"1\",\"St_Term_Id\":\"0310\",\"Curr_Term_Id\":\"0510\",\"P_id\":\"02020000\",\"Address\":\"vdsv\",\"Phone\":\"01236547892\",\"Sex\":\"Male\",\"National_Id\":\"01236547892365\",\"Name\":\"fofo\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }
    @Test
    public void test2(){
        ProfileService ps = new ProfileService();
        String res = ps.run("01010001");
        String trueRes = "{\"Class_Id\":\"1\",\"St_Term_Id\":\"0310\",\"Curr_Term_Id\":\"0510\",\"P_id\":\"02020000\",\"Address\":\"vdzs\",\"Phone\":\"51198\",\"Sex\":\"Male\",\"National_Id\":\"516519\",\"Name\":\"toto\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }
    @Test
    public void test3(){
        ProfileService ps = new ProfileService();
        String res = ps.run("");
        String trueRes = "Error ID";
        assertEquals(trueRes,res);
    }
    @Test
    public void test4(){
        ProfileService ps = new ProfileService();
        String res = ps.run("01012222");
        String trueRes = "ID not found in the database";
        assertEquals(trueRes,res);
    }
    @Test
    public void test5(){
        ProfileService ps = new ProfileService();
        String res = ps.run("01052364");
        String trueRes = "Error bad ID format";
        assertEquals(trueRes,res);
    }

}
