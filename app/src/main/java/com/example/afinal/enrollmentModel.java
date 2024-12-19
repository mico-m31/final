package com.example.afinal;

public class enrollmentModel {
    private int studentId;
    private int subjectId;
    private int credits;

    public enrollmentModel(int studentId, int subjectName, int credits) {
        this.studentId = studentId;
        this.subjectId= subjectName;
        this.credits = credits;
    }
    public int getStudentId(){
        return this.studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int  getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId){
        this.subjectId = subjectId;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public int getCredits() {
        return this.credits;
    }
}
