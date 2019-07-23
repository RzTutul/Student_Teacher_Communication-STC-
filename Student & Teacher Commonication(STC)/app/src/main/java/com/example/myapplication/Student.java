package com.example.myapplication;

public class Student {

    String id;
    String pass;
    String catagories;

    public Student(){

    }

    public Student(String id, String pass, String catagories) {
        this.id = id;
        this.pass = pass;
        this.catagories = catagories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCatagories() {
        return catagories;
    }

    public void setCatagories(String catagories) {
        this.catagories = catagories;
    }
}
