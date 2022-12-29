package com.school.server;

import com.school.server.Service.GradesService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradesTest {
    @Test
    //correct term id
    public void test1(){
        GradesService gs = new GradesService();
        String res = gs.getIds("051");
        String trueRes = "[\"{\\\"grade\\\":\\\"\\\",\\\"name\\\":\\\"toto\\\",\\\"id\\\":\\\"01010001\\\"}\",\"{\\\"grade\\\":\\\"\\\",\\\"name\\\":\\\"fofo\\\",\\\"id\\\":\\\"01010000\\\"}\"]";
        assertEquals(trueRes,res);
    }
    @Test
    //null input
    public void test2(){
        GradesService gs = new GradesService();
        String res = gs.getIds("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //term not in database
    public void test3(){
        GradesService gs = new GradesService();
        String res = gs.getIds("011");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //wrong format term id
    public void test4(){
        GradesService gs = new GradesService();
        String res = gs.getIds("11");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //correct id
    public void test5(){
        GradesService gs = new GradesService();
        String res = gs.getTermRange("01010000");
        String trueRes = "{\"currentyear\":\"05\",\"startyear\":\"03\"}";
        assertEquals(trueRes,res);
    }
    @Test
    //null input
    public void test6(){
        GradesService gs = new GradesService();
        String res = gs.getTermRange("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //term not in database
    public void test7(){
        GradesService gs = new GradesService();
        String res = gs.getTermRange("01010003");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //wrong format id
    public void test8(){
        GradesService gs = new GradesService();
        String res = gs.getTermRange("11");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //correct id and id term to set one subject for one student
    //delete the database before testing
    public void test9(){
        GradesService gs = new GradesService();
        boolean res = gs.saveGrades("{\"subject\":\"05103\",\"students\":[{\"id\":\"01010001\",\"name\":\"momo\",\"grade\":\"50\"}]}");
        assertEquals(true,res);
    }
    @Test
    //null input
    public void test10(){
        GradesService gs = new GradesService();
        boolean res = gs.saveGrades("");
        assertEquals(false,res);
    }
    @Test
    //term not in database
    public void test11(){
        GradesService gs = new GradesService();
        boolean res = gs.saveGrades("{\"subject\":\"05203\",\"students\":[{\"id\":\"01010001\",\"name\":\"momo\",\"grade\":\"50\"}]}");
        assertEquals(false,res);
    }
    @Test
    //wrong student id
    public void test12(){
        GradesService gs = new GradesService();
        boolean res = gs.saveGrades("{\"subject\":\"05103\",\"students\":[{\"id\":\"01010003\",\"name\":\"momo\",\"grade\":\"50\"}]}");
        assertEquals(false,res);
    }
    @Test
    public void test13(){
        GradesService gs = new GradesService();
        String res = gs.showGrades("{\"id\":\"01010000\",\"term\":\"0510\"}");
        String trueRes = "[\"{\\\"subject\\\":\\\"arabic\\\",\\\"grade\\\":\\\"40\\\"}\",\"{\\\"subject\\\":\\\"activities\\\",\\\"grade\\\":\\\"89\\\"}\",\"{\\\"subject\\\":\\\"science\\\",\\\"grade\\\":\\\"30\\\"}\",\"{\\\"subject\\\":\\\"english\\\",\\\"grade\\\":\\\"60\\\"}\",\"{\\\"subject\\\":\\\"algebra\\\",\\\"grade\\\":\\\"100\\\"}\",\"{\\\"subject\\\":\\\"geometry\\\",\\\"grade\\\":\\\"55\\\"}\",\"{\\\"subject\\\":\\\"math\\\",\\\"grade\\\":\\\"20\\\"}\",\"{\\\"subject\\\":\\\"social studies\\\",\\\"grade\\\":\\\"90\\\"}\"]";
        assertEquals(trueRes,res);
    }
    @Test
    //null input
    public void test14(){
        GradesService gs = new GradesService();
        String res = gs.showGrades("");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //term not in database
    public void test15(){
        GradesService gs = new GradesService();
        String res = gs.showGrades("{\"id\":\"01010000\",\"term\":\"0530\"}");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }
    @Test
    //wrong format id
    public void test16(){
        GradesService gs = new GradesService();
        String res = gs.showGrades("{\"id\":\"01010003\",\"term\":\"0520\"}");
        String trueRes = "ERROR";
        assertEquals(trueRes,res);
    }

}
