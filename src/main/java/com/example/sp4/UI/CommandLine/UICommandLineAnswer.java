package com.example.sp4.UI.CommandLine;

import com.example.sp4.UI.UIAnswer;

import java.util.ArrayList;

public class UICommandLineAnswer implements UIAnswer {
    private String[] validOptions;
    private ArrayList<Survey> surveys;
    private Survey chosenSurvey;
    private String name;
    private String email;
    private ArrayList<String> answersToQuestions;
    private UICommandLineScanner scan = new UICommandLineScanner();


    public UICommandLineAnswer(ArrayList<Survey> surveys) {
        this.surveys = surveys;
        this.validOptions = new String[surveys.size()];
        this.answersToQuestions = new ArrayList<>();
        for (Survey s : surveys) {
            validOptions[surveys.indexOf(s)] = String.valueOf(surveys.indexOf(s));
        }
    }

    @Override
    public void UIShowAnswer() {
        int counter = 0;
        for (Survey s : surveys) {
            System.out.println(counter + ": " + s);
            counter++;
        }
        chosenSurvey = surveys.get(Integer.parseInt(scan.getUserInput("choose a survey from the list to answer by writing the corresponding number:(single choice)", validOptions)));

        name = scan.getUserInput("Write your name:");
        email = scan.getUserInput("Write your email");

        for (Question q : chosenSurvey.getQuestions()) {
            System.out.println(q);

            int choiceCounter = 0;
            validOptions = new String[q.getChoices().size()];
            for (String choice : q.getChoices()) {
                validOptions[q.getChoices().indexOf(choice)] = String.valueOf(q.getChoices().indexOf(choice));
                System.out.println(choiceCounter + ": " + choice);
                choiceCounter++;
            }
            int choice = Integer.parseInt(scan.getUserInput("Choose one of the listed answers to the above question by writing the corresponding number:(single choice)", validOptions));
            answersToQuestions.add(q.getChoices().get(choice));
        }
