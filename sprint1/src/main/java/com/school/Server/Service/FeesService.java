package com.school.Server.Service;

import com.google.gson.Gson;
import com.school.Server.DButil.FeesDB;
import com.school.Server.DButil.PersonDB;
import com.school.Server.DButil.StudentDB;
import com.school.Server.gson.ParentInfo;
import com.school.Server.gson.StudentFeesInfo;
import com.school.Server.gson.StudentName_Id;
import com.school.Server.gson.Year_Fees;
import com.school.Server.model.Student;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class FeesService {

    public boolean setYearFees(String input){
        Year_Fees year_fees = new Gson().fromJson(input,Year_Fees.class);
        String year = year_fees.getYear();
        String fees = year_fees.getFees();

        // insert into the database
        FeesDB feesDB = new FeesDB();
        return feesDB.insertFeesYear(year,fees);
    }

    public String getAllChildren(String input){
        ParentInfo parentInfo = new Gson().fromJson(input,ParentInfo.class);
        StudentDB studentDB = new StudentDB();

        // get children IDs
        Vector<String> allChildrenIds = studentDB.getAllChildrenIds(parentInfo.getId());
        if(allChildrenIds == null) return null;

        // get children Names
        PersonDB personDB = new PersonDB();
        Vector<String> allChildrenNames = personDB.getAllChildrenName(allChildrenIds);
        return getStudentNameId(allChildrenIds,allChildrenNames);
    }

    public String getStudentFees(String input){
        StudentFeesInfo studentFeesInfo = new Gson().fromJson(input,StudentFeesInfo.class);
        String id = studentFeesInfo.getId();
        StudentDB studentDB = new StudentDB();

        // get current term of the student
        Student student = studentDB.getStudent(id);
        String curr_term = student.getCurr_term_id();

        // get the year code
        String year_code = curr_term.substring(0,2);

        // return the fees of the student
        FeesDB feesDB = new FeesDB();
        return feesDB.getFees(year_code);
    }


    // make gson of the children name and the children id and return it to the user
    private String getStudentNameId(Vector<String> allChildrenIds,Vector<String> allChildrenNames){
        StudentName_Id[] studentName_id = new StudentName_Id[allChildrenIds.size()];
        for(int i=0;i<allChildrenIds.size();i++){
            String name = allChildrenIds.get(i);
            String Id = allChildrenNames.get(i);
            studentName_id[i] = new StudentName_Id(name,Id);
        }
        return new Gson().toJson(studentName_id);
    }

}
