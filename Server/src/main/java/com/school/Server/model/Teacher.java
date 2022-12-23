package com.school.Server.model;

import com.school.Server.DButil.TeacherDB;

public class Teacher extends Person{
    String Teacher_id;
    String Experience;
    String Sub;

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
}
