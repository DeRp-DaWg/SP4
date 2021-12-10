package com.example.sp4.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends Question {
    HashMap<String, Integer> answers = new HashMap<>();

    public MultipleChoice(String questionName, String questionDescription) {
        super(questionName, questionDescription);
    }

    public MultipleChoice(String questionName, String questionDescription, String[] answers) {
        super(questionName, questionDescription);
        for (String answer : answers) {
            addAnswer(answer);
        }
    }

    @Override
    public void addAnswer(String answer) {
        this.answers.put(answer, 0);
    }

    @Override
    public void addResult(String answer) {
        this.answers.put(answer, this.answers.get(answer) + 1);
    }

    @Override
    public HashMap<String, Integer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question: " + questionName + "\n" +
                "Question description: " + questionDescription;
    }
}
