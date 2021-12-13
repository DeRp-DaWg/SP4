package com.example.sp4.Comparators;

import com.example.sp4.Survey;

import java.util.Comparator;

public class LocationComparator implements Comparator<Survey> {
    public int compareMain(Survey o1, Survey o2) {
        Boolean b1 = o1.isFromDB();
        Boolean b2 = o2.isFromDB();
        return b1.compareTo(b2);
    }
    
    @Override
    public int compare(Survey o1, Survey o2) {
        int comparison = compareMain(o1, o2);
        if (comparison == 0) comparison = new TitleComparator().compareMain(o1, o2);
        if (comparison == 0) comparison = new DescriptionComparator().compareMain(o1, o2);
        if (comparison == 0) comparison = new QuestionsComparator().compareMain(o1, o2);
        return comparison;
    }
    
    
}
