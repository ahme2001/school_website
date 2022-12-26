package com.school.sprint1;
import com.school.sprint1.Service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class testQuiz {
    @Test
    public void test1(){
        QuizService QS = new QuizService();
        String res = QS.getTeacherInfo("03030000");
        assertEquals("{\"Id\":[\"10\",\"30\",\"50\"],\"Name\":[\"1/1\",\"2/1\",\"3/1\"]}",res);
    }
    @Test
    public void test2(){
        // it's an invalid teacher ID
        QuizService QS = new QuizService();
        String res = QS.getTeacherInfo("0303000");
        assertEquals("{\"Id\":[],\"Name\":[]}",res);
    }
    @Test
    public void test3(){
        QuizService QS = new QuizService();
        boolean res = QS.setQuiz("{\"classId\":\"10\",\"endDate\":\"2022-12-23\",\"Qname\":\"Quiz1\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"12\",\"b%\",\"17c\",\"200\"],\"choice3\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"answers\":[2,1,3,2]}");
        assertEquals(true,res);
    }
    @Test
    public void test4(){
        QuizService QS = new QuizService();
        boolean res = QS.setQuiz("{\"classId\":\"20\",\"endDate\":\"2022-12-23\",\"Qname\":\"Quiz2\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\",\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"13\",\"ab%\",\"15ac\",\"12\",\"12\",\"b%\",\"17c\",\"200\"],\"choice3\":[\"13\",\"ab%\",\"13\",\"ab%\",\"15ac\",\"12\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"13\",\"ab%\",\"15ac\",\"12\",\"ab%\",\"15ac\",\"12\"],\"answers\":[2,1,3,2,3,1,4,2]}");
        assertEquals(true,res);
    }
    @Test

    public void test5(){
        QuizService QS = new QuizService();
        boolean res = QS.setQuiz("{\"classId\":\"10\",\"endDate\":\"2022-12-23\",\"Qname\":\"Quiz3\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\",\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"13\",\"ab%\",\"15ac\",\"12\",\"12\",\"b%\",\"17c\",\"200\"],\"choice3\":[\"13\",\"ab%\",\"13\",\"ab%\",\"15ac\",\"12\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"13\",\"ab%\",\"15ac\",\"12\",\"ab%\",\"15ac\",\"12\"],\"answers\":[2,1,3,2,3,1,4,2]}");
        assertEquals(true,res);
    }
    @Test
    public void test6(){
        // it's an invalid Class ID
        QuizService QS = new QuizService();
        boolean res = QS.setQuiz("{\"classId\":\"100\",\"endDate\":\"2022-12-23\",\"Qname\":\"ejabfj\",\"questions\":[\"ejkabfjkc\"],\"choice1\":[\"12\"],\"choice2\":[\"3\"],\"choice3\":[\"2\"],\"choice4\":[\"4\"],\"answers\":[2]}");
        assertEquals(false,res);
    }

    @Test
    public void test7(){
        QuizService QS = new QuizService();
        String res = QS.getQuizzes("01010000");
        System.out.println(res);
        assertEquals("{\"Id\":[\"0\",\"2\"],\"Name\":[\"Quiz1\",\"Quiz3\"]}",res);
    }
    @Test
    public void test8(){
        QuizService QS = new QuizService();
        String res = QS.getQuizzes("01010001");
        assertEquals("{\"Id\":[\"1\"],\"Name\":[\"Quiz2\"]}",res);
    }
    @Test
    public void test9(){
        // it's an invalid Student ID
        QuizService QS = new QuizService();
        String res = QS.getQuizzes("01010002");
        assertEquals("{\"Id\":[],\"Name\":[]}",res);
    }
    @Test
    public void test10(){
        QuizService QS = new QuizService();
        String res = QS.DoQuiz("1");
        assertEquals("{\"Qname\":\"Quiz2\",\"endDate\":\"2022-12-23\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"],\"choice3\":[\"13\",\"ab%\",\"13\",\"ab%\",\"15ac\",\"12\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"13\",\"ab%\",\"15ac\",\"12\",\"ab%\",\"15ac\",\"12\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\",\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"13\",\"ab%\",\"15ac\",\"12\",\"12\",\"b%\",\"17c\",\"200\"]}",res);
    }
    @Test
    public void test11(){
        QuizService QS = new QuizService();
        String res = QS.DoQuiz("3");
        assertEquals("{\"questions\":[],\"choice3\":[],\"choice4\":[],\"choice1\":[],\"choice2\":[]}",res);
    }
    @Test
    public void test12(){
        QuizService QS = new QuizService();
        String res = QS.DoQuiz("2");
        assertEquals("{\"Qname\":\"Quiz3\",\"endDate\":\"2022-12-23\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"],\"choice3\":[\"13\",\"ab%\",\"13\",\"ab%\",\"15ac\",\"12\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"13\",\"ab%\",\"15ac\",\"12\",\"ab%\",\"15ac\",\"12\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\",\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"13\",\"ab%\",\"15ac\",\"12\",\"12\",\"b%\",\"17c\",\"200\"]}",res);
    }
    @Test
    public void test13(){
        QuizService QS = new QuizService();
        String res = QS.calcRes("{\"StId\":\"01010000\",\"QuizId\":\"0\",\"answers\":[2,2,1,4]}");
        assertEquals("{\"grade\":\"1\",\"from\":\"4\"}",res);
    }
    @Test
    public void test14(){
        QuizService QS = new QuizService();
        String res = QS.calcRes("{\"StId\":\"01010000\",\"QuizId\":\"0\",\"answers\":[2,1,3,2]}");
        assertEquals("{\"grade\":\"4\",\"from\":\"4\"}",res);
    }
    @Test
    public void test15(){
        QuizService QS = new QuizService();
        String res = QS.calcRes("{\"StId\":\"01010000\",\"QuizId\":\"1\",\"answers\":[2,3,4,1,2,1,3,2]}");
        System.out.println(res);
        assertEquals("can't found",res);
    }
    @Test
    public void test16(){
        QuizService QS = new QuizService();
        String res = QS.getQuizzes("01010000");
        System.out.println(res);
        assertEquals("{\"Id\":[\"2\"],\"Name\":[\"Quiz3\"]}",res);
    }

}
