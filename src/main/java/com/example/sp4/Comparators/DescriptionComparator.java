package com.example.sp4.Comparators;

import com.example.sp4.Survey;

import java.util.Comparator;

public class DescriptionComparator implements Comparator<Survey> {
    public int compareMain(Survey o1, Survey o2) {
        return o1.getSurveyDescription().toLowerCase().compareTo(o2.getSurveyDescription().toLowerCase());
    }
    
    @Override
    public int compare(Survey o1, Survey o2) {
        int comparison = compareMain(o1, o2);
        if (comparison == 0) comparison = new QuestionsComparator().compareMain(o1, o2);
        if (comparison == 0) comparison = new LocationComparator().compareMain(o1, o2);
        return comparison;
    }
}
