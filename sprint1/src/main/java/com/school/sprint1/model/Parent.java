package com.school.sprint1.model;

import com.school.sprint1.DButil.ParentDB;
import com.school.sprint1.DButil.StudentDB;

public class Parent extends Person{
    String P_id;
    String Jop;

    public String getP_id() {
        return P_id;
    }

    public void setP_id(String p_id) {
        P_id = super.id;
    }

    public String getJop() {
        return Jop;
    }

    public void setJop(String Jop) {
        this.Jop = Jop;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
        this.P_id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public String ToStringSpecific() {
        return "\"" + this.P_id + "\"," +
                "\"" + this.Jop + "\"" ;
    }
    @Override
    public String generateId() {
        ParentDB Pdb = new ParentDB();
        int count = Pdb.getCount();
        String c = Integer.toString(count);
        while(c.length() < 4)
            c = "0"+c;
        return "0202" + c;
    }
}
