package com.school.server;
import com.google.gson.Gson;
import com.school.server.DButil.QuizDB;
import com.school.server.DButil.TeacherDB;
import com.school.server.Service.QuizService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class testQuiz {

    @Autowired
    private QuizService QS;

    @MockBean
    private QuizDB quizDB;

    @MockBean
    private TeacherDB teacherDB;

    @Test
    public void testGetTeacherInfoWithValidId() {
        HashMap<String, Object> res = new HashMap<>();
        ArrayList<String> Ids = new ArrayList<>();
        ArrayList<String> Ns = new ArrayList<>();
        Ids.add("10");
        Ids.add("30");
        Ids.add("50");
        Ns.add("1/1");
        Ns.add("2/1");
        Ns.add("3/1");

        res.put("Id", Ids);
        res.put("Name", Ns);

        // Mockup
        Mockito.when(teacherDB.getClass("03030000")).thenReturn(new Gson().toJson(res));

        // test
        String output = QS.getTeacherInfo("03030000");
        assertEquals("{\"Id\":[\"10\",\"30\",\"50\"],\"Name\":[\"1/1\",\"2/1\",\"3/1\"]}", output);
    }

    @Test
    public void testSetQuiz() {
        // Mockup
        Mockito.when(quizDB.uploadQuiz(Mockito.any())).thenReturn(true);

        // test
        boolean res = QS.setQuiz("{\"classId\":\"10\",\"endDate\":\"2022-12-23\",\"Qname\":\"Quiz1\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"12\",\"b%\",\"17c\",\"200\"],\"choice3\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"ab%\",\"15ac\",\"12\"],\"answers\":[2,1,3,2]}");
        assertEquals(true, res);
    }

    @Test
    public void testSetQuizWithInvalidClassId() {
        // Mockup
        Mockito.when(quizDB.uploadQuiz(Mockito.any())).thenReturn(false);

        // test
        boolean res = QS.setQuiz("{\"classId\":\"100\",\"endDate\":\"2022-12-23\",\"Qname\":\"ejabfj\",\"questions\":[\"ejkabfjkc\"],\"choice1\":[\"12\"],\"choice2\":[\"3\"],\"choice3\":[\"2\"],\"choice4\":[\"4\"],\"answers\":[2]}");
        assertEquals(false, res);
    }

    @Test
    public void testGetAllStudentQuizzes() {
        HashMap<String, Object> qs = new HashMap<>();
        ArrayList<String> Id = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        Id.add("2");
        Name.add("Quiz3");
        qs.put("Id", Id);
        qs.put("Name", Name);
        // Mockup
        Mockito.when(quizDB.getQuizzes("01010000")).thenReturn(new Gson().toJson(qs));

        // test
        String res = QS.getQuizzes("01010000");
        assertEquals("{\"Id\":[\"2\"],\"Name\":[\"Quiz3\"]}", res);
    }

    @Test
    public void testGetAllStudentQuizzesWithInvalidId() {
        HashMap<String, Object> qs = new HashMap<>();
        ArrayList<String> Id = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        qs.put("Id", Id);
        qs.put("Name", Name);
        // Mockup
        Mockito.when(quizDB.getQuizzes("01010002")).thenReturn(new Gson().toJson(qs));

        // test
        String res = QS.getQuizzes("01010002");
        assertEquals("{\"Id\":[],\"Name\":[]}", res);
    }

    @Test
    public void testDoQuiz() {
        HashMap<String, Object> qq = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> choice1 = new ArrayList<>();
        ArrayList<String> choice2 = new ArrayList<>();
        ArrayList<String> choice3 = new ArrayList<>();
        ArrayList<String> choice4 = new ArrayList<>();
        qq.put("Qname", "Quiz2");
        qq.put("endDate", "2022-12-23");
        questions.add("Q1");
        questions.add("Q2");
        questions.add("Q3");
        questions.add("Q4");
        questions.add("Q5");
        questions.add("Q6");
        questions.add("Q7");
        questions.add("Q8");
        choice1.add("13");
        choice1.add("ab%");
        choice1.add("15ac");
        choice1.add("12");
        choice1.add("13");
        choice1.add("ab%");
        choice1.add("15ac");
        choice1.add("12");
        choice2.add("13");
        choice2.add("ab%");
        choice2.add("15ac");
        choice2.add("12");
        choice2.add("12");
        choice2.add("b%");
        choice2.add("17c");
        choice2.add("200");
        choice3.add("13");
        choice3.add("ab%");
        choice3.add("13");
        choice3.add("ab%");
        choice3.add("15ac");
        choice3.add("12");
        choice3.add("15ac");
        choice3.add("12");
        choice4.add("13");
        choice4.add("13");
        choice4.add("ab%");
        choice4.add("15ac");
        choice4.add("12");
        choice4.add("ab%");
        choice4.add("15ac");
        choice4.add("12");
        qq.put("questions", questions);
        qq.put("choice1", choice1);
        qq.put("choice2", choice2);
        qq.put("choice3", choice3);
        qq.put("choice4", choice4);
        // Mockup
        Mockito.when(quizDB.getQuiz("1")).thenReturn(new Gson().toJson(qq));

        // test
        String res = QS.DoQuiz("1");
        assertEquals("{\"Qname\":\"Quiz2\",\"endDate\":\"2022-12-23\",\"questions\":[\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"],\"choice3\":[\"13\",\"ab%\",\"13\",\"ab%\",\"15ac\",\"12\",\"15ac\",\"12\"],\"choice4\":[\"13\",\"13\",\"ab%\",\"15ac\",\"12\",\"ab%\",\"15ac\",\"12\"],\"choice1\":[\"13\",\"ab%\",\"15ac\",\"12\",\"13\",\"ab%\",\"15ac\",\"12\"],\"choice2\":[\"13\",\"ab%\",\"15ac\",\"12\",\"12\",\"b%\",\"17c\",\"200\"]}", res);
    }

    @Test
    public void testCalcRes() {
        int[] sol = new int[4];
        sol[0] = 1;
        sol[1] = 2;
        sol[2] = 3;
        sol[3] = 3;

        // Mockup
        Mockito.when(quizDB.getSolutions("0", "01010000", 4)).thenReturn(sol);
        Mockito.when(quizDB.updateDoQuiz("0", "01010000", 1)).thenReturn(true);

        // test
        String res = QS.calcRes("{\"StId\":\"01010000\",\"QuizId\":\"0\",\"answers\":[2,2,1,4]}");
        assertEquals("{\"grade\":\"1\",\"from\":\"4\"}", res);
    }

    @Test
    public void testCalcResWithNotExistAnswer() {
        int[] sol = new int[4];
        sol[0] = 0;
        sol[1] = 2;
        sol[2] = 3;
        sol[3] = 3;

        // Mockup
        Mockito.when(quizDB.getSolutions("1", "01010000", 4)).thenReturn(sol);

        // test
        String res = QS.calcRes("{\"StId\":\"01010000\",\"QuizId\":\"1\",\"answers\":[1,2,3,3]}");
        assertEquals("can't found", res);
    }

    @Test
    public void testGetPrevResult() {
        HashMap<String, Object> qs = new HashMap<>();
        ArrayList<String> grade = new ArrayList<>();
        ArrayList<String> maxGrade = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> submissionDate = new ArrayList<>();
        grade.add("10");
        grade.add("15");
        grade.add("20");
        maxGrade.add("20");
        maxGrade.add("20");
        maxGrade.add("20");
        Name.add("Q1");
        Name.add("Q2");
        Name.add("Q3");
        submissionDate.add("12-3-2021");
        submissionDate.add("21-3-2022");
        submissionDate.add("13-4-2021");
        qs.put("Grade", grade);
        qs.put("MaxGrade", maxGrade);
        qs.put("Name", Name);
        qs.put("Date", submissionDate);

        // Mockup
        Mockito.when(quizDB.getPrevResults("01010000")).thenReturn(new Gson().toJson(qs));
        // test
        String output = QS.getPrevResult("01010000");
        assertEquals("{\"MaxGrade\":[\"20\",\"20\",\"20\"],\"Grade\":[\"10\",\"15\",\"20\"],\"Date\":[\"12-3-2021\",\"21-3-2022\",\"13-4-2021\"],\"Name\":[\"Q1\",\"Q2\",\"Q3\"]}", output);
    }

    @Test
    public void testGetClassQuizzes() {
        HashMap<String , Object> qs = new HashMap<>();
        ArrayList<String> QId = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> endDate = new ArrayList<>();
        QId.add("1");QId.add("2");
        Name.add("Q1");Name.add("Q2");
        endDate.add("12-11-2022");endDate.add("15-10-2022");
        qs.put("QId",QId);
        qs.put("Name",Name);
        qs.put("endDate",endDate);

        // Mockup
        Mockito.when(quizDB.getClassQuizzes("03030000","22")).thenReturn(new Gson().toJson(qs));
        HashMap<String , String> input = new HashMap<>();
        input.put("teacher_Id","03030000");
        input.put("classId","22");
        // test
        String output = QS.getClassQuizzes(new Gson().toJson(input));
        assertEquals("{\"endDate\":[\"12-11-2022\",\"15-10-2022\"],\"QId\":[\"1\",\"2\"],\"Name\":[\"Q1\",\"Q2\"]}", output);
    }
    @Test
    public void testGetQuizResults(){
        HashMap<String , Object> qResults = new HashMap<>();
        ArrayList<String> LateName = new ArrayList<>();
        ArrayList<String> LateId = new ArrayList<>();
        ArrayList<String> DoneName = new ArrayList<>();
        ArrayList<String> DoneId = new ArrayList<>();
        ArrayList<String> grade = new ArrayList<>();
        ArrayList<String> submissionDate = new ArrayList<>();
        DoneId.add("2");DoneId.add("3");
        DoneName.add("Mohamed");DoneName.add("Moaab");
        grade.add("15");grade.add("13");
        submissionDate.add("12-12-2021");submissionDate.add("13-11-2021");
        LateId.add("4");LateId.add("5");
        LateName.add("mariam");LateName.add("ali");
        qResults.put("DoneId",DoneId);
        qResults.put("DoneName",DoneName);
        qResults.put("Grade",grade);
        qResults.put("SubmissionTime",submissionDate);
        qResults.put("LateId",LateId);
        qResults.put("LateName",LateName);
        // Mockup
        Mockito.when(quizDB.getQuizResults("3")).thenReturn(new Gson().toJson(qResults));

        // test
        String output = QS.getQuizResults("3");
        assertEquals("{\"DoneName\":[\"Mohamed\",\"Moaab\"],\"DoneId\":[\"2\",\"3\"],\"SubmissionTime\":[\"12-12-2021\",\"13-11-2021\"],\"Grade\":[\"15\",\"13\"],\"LateId\":[\"4\",\"5\"],\"LateName\":[\"mariam\",\"ali\"]}",output);
    }
}
