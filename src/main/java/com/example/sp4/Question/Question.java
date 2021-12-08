package com.example.sp4.Question;

import java.util.HashMap;

public abstract class Question {
    String questionName;
    String questionDescription;

    String answer[];
    abstract void addQuestion(String title);

    public Question(String questionName, String questionDescription) {
        this.questionName = questionName;
        this.questionDescription = questionDescription;
    }
    abstract public void addAnswer();
    abstract public void addResult();

    public String getQuestionName() {
        return questionName;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }
    public abstract HashMap<String,Integer> getAnswers();
}
