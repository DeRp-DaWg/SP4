package com.example.sp4;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable, Comparable<Survey> {
    public static final long serialVersionUID = 104877509L;
    private transient long id;
    private String surveyTitle;
    private String surveyDescription;
    private ArrayList<Question> questions = new ArrayList<>();
    private boolean fromDB = false;
    
    public Survey(String surveyTitle, String surveyDescription){
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
    }

    public Survey(String surveyTitle, String surveyDescription, ArrayList<Question> questions, long id) {
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.questions = questions;
        this.id = id;
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
    public void addQuestion(Question question){
        questions.add(question);
    }
    
    public boolean isFromDB() {
        return fromDB;
    }
    
    public void setFromDB(boolean fromDB) {
        this.fromDB = fromDB;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(Survey o) {
        return surveyTitle.compareTo(o.getSurveyTitle());
    }

    @Override
    public String toString(){
        return this.surveyTitle+" : "+this.surveyDescription;
    }
}
