package com.example.sp4.IO;

import com.example.sp4.Survey;

public interface IO {
    Survey[] read() throws Exception;
    Survey read(String name) throws Exception;
    void save(Survey survey);
}
