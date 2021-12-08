package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UICreate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class UICommandLineCreate {
    private String surveyTitle;
    private String surveyDescription;
    private String choice;
    private UICommandLineScanner scan = new UICommandLineScanner();
    private Survey survey;

    public UICommandLineCreate() {
    }

    public void UICreate(ArrayList<Survey> surveys) {

        surveyTitle = scan.getUserInput("Whats the title of your survey?");

        choice = scan.getUserInputYesOrNo("Do you want to add a description to your survey?(y/n)");
        if (choice.equalsIgnoreCase("y")) {
            surveyDescription = scan.getUserInput("Survey description:");
        }
        survey = new Survey(surveyTitle, surveyDescription);

        System.out.println(surveyTitle + " : " + surveyDescription);

        String question1 = (scan.getUserInput("Add a question:"));
        Question quest = new Question(question1);
        survey.addQuestions(quest);

        createQuestionAnswer(quest);
        String questionChoice;
        do {
            questionChoice = scan.getUserInputYesOrNo("Do you want to add another question?(y/n)");
            if (questionChoice.equalsIgnoreCase("y")) {
                String question2 = (scan.getUserInput("Question name:"));
                Question quest2= new Question(question2);
                survey.addQuestions(quest2);
                createQuestionAnswer(quest2);
            }
        } while (!questionChoice.equalsIgnoreCase("n"));
        surveys.add(survey);
    }

    private void createQuestionAnswer(Question q) {
        q.addChoices(scan.getUserInput("Answer:"));
        q.addChoices(scan.getUserInput("Answer:"));
        do {
            choice = scan.getUserInputYesOrNo("Add another answer?(y/n)");
            if (choice.equalsIgnoreCase("y")) {
                q.addChoices(scan.getUserInput("Answer:"));
            }
        } while (!choice.equalsIgnoreCase("n"));
    }
}
