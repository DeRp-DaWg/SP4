package com.example.sp4;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {
    public static final long serialVersionUID = 104877509L;
    private String surveyTitle;
    private String surveyDescription;
    private ArrayList<Question> questions = new ArrayList<>();

    public Survey(String surveyTitle, String surveyDescription) {
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
    }

    public Survey(String surveyTitle, String surveyDescription, ArrayList<Question> questions) {
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.questions = questions;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public String toString() {
        String s="";

        if (surveyDescription != null) {
           s= "Survey title: " + surveyTitle + "\n" +
                    "Survey description: " + surveyDescription + "\n";
        }else {
            s="Survey title: " + surveyTitle;
        }
        return s;
    }
}
