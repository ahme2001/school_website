package com.school.sprint1.model;

import com.school.sprint1.DButil.ParentDB;
import com.school.sprint1.DButil.StudentDB;

public class Student extends Person{
    String St_id;
    String Class_Id;
    String ST_Term_Id;
    String Curr_term_id;
    String P_id;
    String parent_national_id;

    public Student(String st_id, String class_Id, String ST_Term_Id, String curr_term_id, String p_id) {
        this.St_id = st_id;
        this.Class_Id = class_Id;
        this.ST_Term_Id = ST_Term_Id;
        this.Curr_term_id = curr_term_id;
        this.P_id = p_id;
    }
    public Student() {
    }
    public String getSt_id() {
        return St_id;
    }

    public void setSt_id(String st_id) {
        St_id = st_id;
    }

    public String getClass_Id() {
        return Class_Id;
    }

    public void setClass_Id(String class_Id) {
        Class_Id = class_Id;
    }

    public String getSt_term_is() {
        return ST_Term_Id;
    }

    public void setSt_term_is(String st_term_id) {
        ST_Term_Id = st_term_id;
    }

    public String getCurrent_term_is() {
        return Curr_term_id;
    }

    public void setCurrent_term_is(String current_term_id) {
        Curr_term_id = current_term_id;
    }

    public String getP_id() {
        return P_id;
    }

    public void setP_id(String p_id){
        this.P_id = p_id;
    }

    public void setP_id() {
        String p_id = new ParentDB().getID(this.parent_national_id);
        this.P_id = p_id;
    }

    public String getST_Term_Id() {
        return ST_Term_Id;
    }

    public void setST_Term_Id(String ST_Term_Id) {
        this.ST_Term_Id = ST_Term_Id;
    }

    public String getCurr_term_id() {
        return Curr_term_id;
    }

    public void setCurr_term_id(String curr_term_id) {
        Curr_term_id = curr_term_id;
    }

    public String getParent_national_id() {
        return parent_national_id;
    }

    public void setParent_national_id(String parent_national_id) {
        this.parent_national_id = parent_national_id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
        this.St_id = id;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String ToStringSpecific() {
        return "\"" + this.St_id + "\"," +
        "\"" + this.Class_Id + "\"," +
        "\"" + this.ST_Term_Id + "\"," +
        "\"" + this.Curr_term_id + "\"," +
        "\"" + this.P_id + "\"" ;
    }
    @Override
    public String generateId() {
        String s_term = this.ST_Term_Id;
        StudentDB sdb = new StudentDB();
        int count = sdb.getCount(s_term);
        String c = Integer.toString(count);
        while(c.length() < 4)
            c = "0"+c;
        while(s_term.length() < 2)
            s_term = "0"+s_term;

        String ID = s_term + "01" + c;
        return ID;
    }

}
