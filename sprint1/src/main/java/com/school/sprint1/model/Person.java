package com.school.sprint1.model;

public class Person {
    int id;
    String sex;
    String name;
    String pass;
    String school;
    String type;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", school='" + school + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
