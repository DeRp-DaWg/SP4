package com.example.sp4.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends Question {
    HashMap<String,Integer> answers;

    public MultipleChoice(String questionName, String questionDescription, HashMap<String, Integer> answers) {
        super(questionName, questionDescription);
        this.answers = answers;
    }
    public MultipleChoice(String questionName, String questionDescription) {
        super(questionName, questionDescription);
    }

    public void addQuestion(String title){}

    @Override
    public void addAnswer() {

    }

    @Override
    public void addResult() {

    }

    @Override
    public HashMap<String, Integer> getAnswers() {
        return null;
    }

    public void draw(){

    }
}
