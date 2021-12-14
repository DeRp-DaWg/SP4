package com.example.sp4.Comparators;

import java.util.Comparator;

public class SurveyFileComparator implements Comparator<String> {
    
    @Override
    public int compare(String o1, String o2) {
        return getLastInt(o1)-getLastInt(o2);
    }
    
    private int getLastInt(String str) {
        String i = str.split("Survey")[1];
        return i.isEmpty() ? 0 : Integer.parseInt(i);
    }
}
