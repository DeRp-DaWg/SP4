package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.util.ArrayList;

public class UICommandLineCreate {
    private String surveyTitle;
    private String surveyDescription;
    private String choice;
    private Survey survey;
    private UICommandLineScanner scan = new UICommandLineScanner();

    public UICommandLineCreate() {
    }

    public void UICreate(ArrayList<Survey> surveys) {
        surveyTitle = scan.getUserInput("Whats the title of your survey?");

        choice = scan.getUserInputYesOrNo("Do you want to add a description to your survey?(y/n)");
        if (choice.equalsIgnoreCase("y")) {
            surveyDescription = scan.getUserInput("Survey description:");
        }

        survey = new Survey(surveyTitle, surveyDescription);

        System.out.println("Your survey:\n" + surveyTitle + " : " + surveyDescription);

        String question1 = (scan.getUserInput("Add a question:"));
        String question1Desc = (scan.getUserInput("Add a question description:"));
        Question quest = new MultipleChoice(question1, question1Desc);
        survey.addQuestion(quest);
        createQuestionAnswer(quest);

        String questionChoice;
        do {
            questionChoice = scan.getUserInputYesOrNo("Do you want to add another question?(y/n)");
            if (questionChoice.equalsIgnoreCase("y")) {
                String question2 = (scan.getUserInput("Question name:"));
                String question2Desc = (scan.getUserInput("Add a question description:"));
                Question quest2 = new MultipleChoice(question2, question2Desc);
                survey.addQuestion(quest2);
                createQuestionAnswer(quest2);
            }
        } while (!questionChoice.equalsIgnoreCase("n"));
        surveys.add(survey);
    }

    private void createQuestionAnswer(Question q) {
        q.addAnswer(scan.getUserInput("Answer:"));
        q.addAnswer(scan.getUserInput("Answer:"));

        do {
            choice = scan.getUserInputYesOrNo("Add another answer?(y/n)");
            if (choice.equalsIgnoreCase("y")) {
                q.addAnswer(scan.getUserInput("Answer:"));
            }
        } while (!choice.equalsIgnoreCase("n"));
    }
}