package com.example.sp4;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {
    
    @Test
    void serializeSurvey() throws IOException {
        new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new Survey());
    }
}