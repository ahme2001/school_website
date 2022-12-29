package com.school.sprint1;

import com.school.sprint1.Service.CreateAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class CreateAccountTest {

    //test teacher create account
    @Test
    public void test1(){
        CreateAccountService CA = new CreateAccountService();
        boolean res = CA.run("{\"Name\":\"mohsen\",\"Phone\":\"01336543217\",\"National_Id\":\"95637462321862\",\"Address\":\"fowa\",\"birth_date\":\"2022-07-14\",\"Sex\":\"M\",\"Sub\":\"arabic\",\"Experience\":\"teacher A\"}","Teacher");
        assertEquals(true,res);
    }
    //test staff create account
    @Test
    public void test2(){
        CreateAccountService CA = new CreateAccountService();
        boolean res = CA.run("{\"Name\":\"ibrahim\",\"Phone\":\"12562584936\",\"National_Id\":\"13563294985697\",\"Address\":\"cairo\",\"birth_date\":\"2022-04-14\",\"Sex\":\"M\",\"Job\":\"manager\"}","Staff");
        assertEquals(true,res);
    }
    //test parent create account
    @Test
    public void test3(){
        CreateAccountService CA = new CreateAccountService();
        boolean res = CA.run("{\"Name\":\"ayman\",\"National_Id\":\"26523455655623\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","Parent");
        assertEquals(true,res);
    }
    //test student create account
    @Test
    public void test4(){
        CreateAccountService CA = new CreateAccountService();
        boolean res = CA.run("{\"Name\":\"islam\",\"Phone\":\"12695549876\",\"National_Id\":\"32658741336513\",\"Address\":\"alex\",\"birth_date\":\"2022-12-23\",\"Sex\":\"M\",\"Class_Id\":\"2\",\"St_Term_Id\":\"1\",\"Curr_Term_Id\":\"9\",\"parent_name\":\"ayman\",\"parent_national_id\":\"26523455655623\",\"parent_phone\":\"23018529835\",\"parent_job\":\"doctor\",\"parent_sex\":\"M\"}","Student");
        assertEquals(true,res);
    }
    //add parent with same national id --> false
    @Test
    public void test5(){
        CreateAccountService CA = new CreateAccountService();
        CA.run("{\"Name\":\"ayman\",\"National_Id\":\"14725836912345\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","Parent");
        boolean res = CA.run("{\"Name\":\"ayman\",\"National_Id\":\"14725836912345\",\"phone\":\"23018529835\",\"Job\":\"doctor\",\"Address\":\"alex\",\"Sex\":\"M\"}","Parent");
        assertEquals(false,res);
    }
}