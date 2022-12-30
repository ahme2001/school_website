package com.school.server.Service;
import com.school.server.DButil.StudentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateEndTermServer {
    @Autowired
    private  StudentDB s = new StudentDB();
    public boolean updateTerm(){
        return  s.updateEndTerm();
    }

    public boolean IsEnd(){
        return  s.IsEnd();
    }
}
