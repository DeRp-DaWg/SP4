package com.example.sp4.IO;

import com.example.sp4.Survey;

public interface IO {
    Survey read(String name);
    void save(Survey survey);
}
