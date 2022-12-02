package com.school.sprint1.model;

import com.school.sprint1.DButil.StaffDB;

public class Staff extends Person{
    String S_id;
    String Job;

    public String getS_id() {
        return S_id;
    }

    public void setS_id(String s_id) {
        S_id = s_id;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String Jop) {
        this.Job = Jop;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
        this.S_id = id;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    public String ToStringSpecific() {
        return "\"" + this.S_id + "\"," +
                "\"" + this.Job + "\"" ;
    }
    @Override
    public String generateId() {
        StaffDB Sdb = new StaffDB();
        int count = Sdb.getCount();
        String c = Integer.toString(count);
        while(c.length() < 4)
            c = "0"+c;
        return "0404" + c;
    }
}
