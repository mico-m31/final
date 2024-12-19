package com.example.afinal;

public class subjectModel {
    private Integer subjectId;
    private String subjectName;
    private String className;
    private Integer credits;

    public subjectModel(){

    }
    public subjectModel(Integer subjectId, String subjectName, String className, Integer credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.className = className;
        this.credits = credits;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
