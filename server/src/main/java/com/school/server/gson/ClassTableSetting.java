package com.school.server.gson;

public class ClassTableSetting {
    private String classId;
    private String termId;
    private Day_Lecture[] days;

    public ClassTableSetting(String classId, String termId, Day_Lecture[] days) {
        this.classId = classId;
        this.termId = termId;
        this.days = days;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public Day_Lecture[] getDays() {
        return days;
    }

    public void setDays(Day_Lecture[] days) {
        this.days = days;
    }
}
