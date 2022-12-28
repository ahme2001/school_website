package com.school.sprint1.Service;
import com.school.sprint1.DButil.StudentDB;
import org.springframework.stereotype.Service;

@Service
public class updateEndTerm {
    public boolean updateTerm(){
        StudentDB s = new StudentDB();
        return  s.updateEndTerm();
    }

    public boolean IsEnd(){
        StudentDB s = new StudentDB();
        return  s.IsEnd();
    }
}
