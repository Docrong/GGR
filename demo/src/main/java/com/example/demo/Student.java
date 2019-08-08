package com.example.demo;

public class Student {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = "123";
    }

    public static void main(String[] args) {
        Student s = new Student();
        System.out.println(s.getStr());
    }
}
