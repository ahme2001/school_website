package com.school.server;

import com.school.server.DButil.*;
import com.school.server.Service.CreateAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateAccountTest {

    @Autowired
    private CreateAccountService CA;

    @MockBean
    private PersonDB personDB;

    @MockBean
    private StudentDB studentDB;

    @MockBean
    private TeacherDB teacherDB;

    @MockBean
    private StaffDB staffDB;

    @MockBean
    private ParentDB parentDB;

    @Test
    public void testCreateTeacherAcc(){
        // Mockup
        Mockito.when(personDB.addPerson(Mockito.anyString())).thenReturn(true);
        Mockito.when(teacherDB.addTeacher(Mockito.anyString())).thenReturn(true);
        Mockito.when(teacherDB.getCount()).thenReturn(1);

        // test
        boolean res = CA.run("{\"Name\":\"mohsen\",\"Phone\":\"01336543217\",\"National_Id\":\"95637462321862\",\"Address\":\"fowa\",\"birth_date\":\"2022-07-14\",\"Sex\":\"M\",\"Sub\":\"arabic\",\"Experience\":\"teacher A\"}","Teacher");
        assertTrue(res);
    }

    @Test
    public void testCrateStaffAcc(){
        // Mockup
        Mockito.when(personDB.addPerson(Mockito.anyString())).thenReturn(true);
        Mockito.when(staffDB.addStaff(Mockito.anyString())).thenReturn(true);
        Mockito.when(staffDB.getCount()).thenReturn(1);

        // test
        boolean res = CA.run("{\"Name\":\"ibrahim\",\"Phone\":\"12562584936\",\"National_Id\":\"13563294985697\",\"Address\":\"cairo\",\"birth_date\":\"2022-04-14\",\"Sex\":\"M\",\"Job\":\"manager\"}","Staff");
        assertTrue(res);
    }
    //test parent create account
    @Test
    public void testCreateParentAcc(){
        // Mockup
        Mockito.when(personDB.addPerson(Mockito.anyString())).thenReturn(true);
        Mockito.when(parentDB.addParent(Mockito.anyString())).thenReturn(true);
        Mockito.when(parentDB.getCount()).thenReturn(1);

        // test
        boolean res = CA.run("{\"Name\":\"ayman\",\"National_Id\":\"26523455655623\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","Parent");
        assertTrue(res);
    }
    //test student create account
    @Test
    public void testCreateStudentAcc(){
        // Mockup
        Mockito.when(personDB.addPerson(Mockito.anyString())).thenReturn(true);
        Mockito.when(studentDB.addStudent(Mockito.anyString())).thenReturn(true);
        Mockito.when(studentDB.getCount("1")).thenReturn(1);
        Mockito.when(parentDB.getID("26523455655623")).thenReturn("20020003");

        // test
        boolean res = CA.run("{\"Name\":\"islam\",\"Phone\":\"12695549876\",\"National_Id\":\"32658741336513\",\"Address\":\"alex\",\"birth_date\":\"2022-12-23\",\"Sex\":\"M\",\"Class_Id\":\"2\",\"ST_Term_Id\":\"1\",\"Curr_Term_Id\":\"9\",\"parent_name\":\"ayman\",\"parent_national_id\":\"26523455655623\",\"parent_phone\":\"23018529835\",\"parent_job\":\"doctor\",\"parent_sex\":\"M\"}","Student");
        assertTrue(res);
    }

    @Test
    public void testEnterNotExistType(){
        CA.run("{\"Name\":\"ayman\",\"National_Id\":\"14725836912345\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","Parent");
        boolean res = CA.run("{\"Name\":\"ayman\",\"National_Id\":\"14725836912345\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","test");
        assertFalse(res);
    }
}