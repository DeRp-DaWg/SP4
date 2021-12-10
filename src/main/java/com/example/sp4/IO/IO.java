package com.example.sp4.IO;

import com.example.sp4.Survey;

import java.util.ArrayList;

public interface IO {
    ArrayList<Survey> read() throws Exception;
    Survey read(String name) throws Exception;
    void save(Survey survey);
}
