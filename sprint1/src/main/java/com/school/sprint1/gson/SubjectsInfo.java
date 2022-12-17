package com.school.sprint1.gson;

public class SubjectsInfo {
    String subject;
    String id;
    String date;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\"" + id + '\"' +
                ", \"" + subject + '\"' +
                ", \"" + date + '\"';
    }
}
