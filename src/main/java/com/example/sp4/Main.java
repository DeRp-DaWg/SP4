package com.example.sp4;

import com.example.sp4.IO.IODatabase;

public class Main {
    public static void main(String[] args) {
        //Survey survey = new Survey();
        IODatabase ioDatabase = new IODatabase();
        ioDatabase.read("age survey");
        System.out.println(ioDatabase.getIdOfSurvey("Age survey"));
    }
}
