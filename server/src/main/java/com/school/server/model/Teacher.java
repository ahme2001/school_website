package com.school.server.model;


import com.school.server.DButil.ParentDB;

import com.school.server.DButil.TeacherDB;

public class Teacher extends Person{
    private String Teacher_id;
    private String Experience;
    private String Sub;

    public Teacher(String teacher_id, String experience, String sub) {
        this.Teacher_id = teacher_id;
        this.Experience = experience;
        this.Sub = sub;
    }

    public Teacher() {
    }

    public String getTeacher_id() {
        return Teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        Teacher_id = teacher_id;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSub() {
        return Sub;
    }

    public void setSub(String sub) {
        Sub = sub;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
        this.Teacher_id = id;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    public String ToStringSpecific() {
        return "\"" + this.Teacher_id + "\"," +
                "\"" + this.Experience + "\"," +
                "\"" + this.Sub + "\"" ;
    }

    @Override
    public String generateId() {
        TeacherDB Tdb = new TeacherDB();
        int count = Tdb.getCount();
        String c = Integer.toString(count);
        while(c.length() < 4)
            c = "0"+c;
        return "0303" + c;
    }

    public String generateTeacherId(TeacherDB teacherDB){
        int count = teacherDB.getCount();
        String c = Integer.toString(count);
        while(c.length() < 4)
            c = "0"+c;
        return "0303" + c;
    }
}
