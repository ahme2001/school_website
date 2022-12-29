package com.school.sprint1.Service;
import com.school.sprint1.DButil.StudentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateEndTermServer {
    @Autowired
    private  StudentDB s ;
    public boolean updateTerm(){
        return  s.updateEndTerm();
    }

    public boolean IsEnd(){
        return  s.IsEnd();
    }
}
