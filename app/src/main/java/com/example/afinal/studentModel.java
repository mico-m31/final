package com.example.afinal;

public class studentModel {
    Integer studentId;
    String studentName;
    String email;
    String password;

    public studentModel(String email,String password){
        this.email = email;
        this.password = password;
    }
    public studentModel(Integer studentId,String studentName,String email,String password){
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.password = password;
    }

    public Integer getStudentId(){
        return this.studentId;
    }

    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public String getStudentName(){
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
