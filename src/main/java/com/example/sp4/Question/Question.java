package com.example.sp4.Question;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Question implements Serializable {
    public static final long serialVersionUID = 578852834L;
    String questionName;
    String questionDescription;
    
    public Question(String questionName, String questionDescription) {
        this.questionName = questionName;
        this.questionDescription = questionDescription;
    }
    
    public abstract void addAnswer(String answer);
    
    public abstract void addResult(String answer);
    
    public String getQuestionName() {
        return questionName;
    }
    
    public String getQuestionDescription() {
        return questionDescription;
    }
    
    public abstract HashMap<String, Integer> getAnswers();
}
