package com.school.server;

import com.school.server.DButil.ParentDB;
import com.school.server.DButil.StaffDB;
import com.school.server.DButil.StudentDB;
import com.school.server.DButil.TeacherDB;
import com.school.server.Service.ProfileService;
import com.school.server.model.Parent;
import com.school.server.model.Staff;
import com.school.server.model.Student;
import com.school.server.model.Teacher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class profileTest {

    @Autowired
    private ProfileService ps;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private ParentDB parentDB;

    @MockBean
    private TeacherDB teacherDB;

    @MockBean
    private StaffDB staffDB;


    @Test
    public void testGetStudentProfile(){
        Student student = new Student(null,"1","0310","0510","02020000");
        student.setAddress("vdsv");
        student.setPhone("01236547892");
        student.setName("fofo");
        student.setNational_Id("01236547892365");
        student.setSex("Male");
        student.setPassword("123456");

        // Mockup
        Mockito.when(studentDB.getInfo("01010000")).thenReturn(student);

        // test
        String res = ps.run("01010000");
        String trueRes = "{\"Class_Id\":\"1\",\"ST_Term_Id\":\"0310\",\"Curr_term_id\":\"0510\",\"P_id\":\"02020000\",\"Address\":\"vdsv\",\"Phone\":\"01236547892\",\"Sex\":\"Male\",\"National_Id\":\"01236547892365\",\"Name\":\"fofo\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetParentProfile(){
        Parent parent = new Parent(null,"doctor");
        parent.setAddress("bgfb");
        parent.setPhone("01203214567");
        parent.setName("momo");
        parent.setNational_Id("12365478912345");
        parent.setSex("Male");
        parent.setPassword("123456");

        // Mockup
        Mockito.when(parentDB.getInfo("02020000")).thenReturn(parent);

        // test
        String res = ps.run("02020000");
        String trueRes = "{\"Job\":\"doctor\",\"Address\":\"bgfb\",\"Phone\":\"01203214567\",\"Sex\":\"Male\",\"National_Id\":\"12365478912345\",\"Name\":\"momo\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetTeacherProfile(){
        Teacher teacher = new Teacher(null,"6","arabic");
        teacher.setAddress("fowa");
        teacher.setPhone("01005422136");
        teacher.setName("mohamed");
        teacher.setNational_Id("301041500236");
        teacher.setSex("Male");
        teacher.setPassword("123456");

        // Mockup
        Mockito.when(teacherDB.getInfo("03030000")).thenReturn(teacher);

        // test
        String res = ps.run("03030000");
        String trueRes = "{\"Experience\":\"6\",\"Sub\":\"arabic\",\"Address\":\"fowa\",\"Phone\":\"01005422136\",\"Sex\":\"Male\",\"National_Id\":\"301041500236\",\"Name\":\"mohamed\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetStaffProfile(){
        Staff staff = new Staff(null,"manager");
        staff.setAddress("fowwa");
        staff.setPhone("01005622358");
        staff.setName("mostafa");
        staff.setNational_Id("12345696325148");
        staff.setSex("Male");
        staff.setPassword("123456");

        // Mockup
        Mockito.when(staffDB.getInfo("04040000")).thenReturn(staff);

        // test
        String res = ps.run("04040000");
        String trueRes = "{\"Job\":\"manager\",\"Address\":\"fowwa\",\"Phone\":\"01005622358\",\"Sex\":\"Male\",\"National_Id\":\"12345696325148\",\"Name\":\"mostafa\",\"Password\":\"123456\"}";
        assertEquals(trueRes,res);
    }


    @Test
    public void testEmptyId(){
        String res = ps.run("");
        String trueRes = "Error ID";
        assertEquals(trueRes,res);
    }

    @Test
    public void testNotValidId(){
        String res = ps.run("01052364");
        String trueRes = "Error bad ID format";
        assertEquals(trueRes,res);
    }

    @Test
    public void testGetProfileNotInDB(){
        // Mockup
        Mockito.when(studentDB.getInfo("01010000")).thenReturn(null);

        // test
        String res = ps.run("01012222");
        String trueRes = "ID not found in the database";
        assertEquals(trueRes,res);
    }
}
