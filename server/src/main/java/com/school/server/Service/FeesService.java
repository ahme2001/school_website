package com.school.server.Service;

import com.google.gson.Gson;
import com.school.server.DButil.FeesDB;
import com.school.server.DButil.PersonDB;
import com.school.server.DButil.StudentDB;
import com.school.server.gson.ParentInfo;
import com.school.server.gson.StudentName_fees;
import com.school.server.gson.Year_Fees;
import com.school.server.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class FeesService {

    @Autowired
    private FeesDB feesDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private PersonDB personDB;

    public boolean setYearFees(String input){
        Year_Fees year_fees = new Gson().fromJson(input,Year_Fees.class);
        String year = year_fees.getYear();
        String fees = year_fees.getFees();

        // insert into the database
        return feesDB.insertFeesYear(year,fees);
    }

    public String getAllChildrenFees(String input){
        ParentInfo parentInfo = new Gson().fromJson(input,ParentInfo.class);

        // get children IDs
        Vector<String> allChildrenIds = studentDB.getAllChildrenIds(parentInfo.getId());
        if(allChildrenIds == null) return null;

        // get children Names
        Vector<String> allChildrenNames = personDB.getAllChildrenName(allChildrenIds);

        // get children fees
        Vector<String> allChildrenFees = getAllStudentFees(allChildrenIds);
        return getStudentNameFees(allChildrenNames,allChildrenFees);
    }

    private Vector<String> getAllStudentFees(Vector<String> childrenIds){
        Vector<String> childrenFees = new Vector<>();

        for (String childrenId : childrenIds) {
            // get current term of the student
            Student student = studentDB.getStudent(childrenId);
            String curr_term = student.getCurr_Term_Id();

            // get the year code
            String year_code = curr_term.substring(0, 2);

            // get the fees of the student
            childrenFees.add(feesDB.getFees(year_code));
        }
        return childrenFees;
    }

    // make gson of the children name and their fees and return it to the user
    private String getStudentNameFees(Vector<String> allChildrenNames,Vector<String> allChildrenFees){
        StudentName_fees[] studentName_fees = new StudentName_fees[allChildrenNames.size()];
        for(int i=0;i<allChildrenNames.size();i++){
            String name = allChildrenNames.get(i);
            String fees = allChildrenFees.get(i);
            studentName_fees[i] = new StudentName_fees(name,fees);
        }
        return new Gson().toJson(studentName_fees);
    }
}
