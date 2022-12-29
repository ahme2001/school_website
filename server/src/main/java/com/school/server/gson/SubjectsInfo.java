package com.school.server.gson;

public class SubjectsInfo {
    private String subject;
    private String id;
    private String date;

    public SubjectsInfo(String subject, String id, String date) {
        this.subject = subject;
        this.id = id;
        this.date = date;
    }

    public SubjectsInfo() {
    }

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
