package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.util.ArrayList;

public class UICommandLineAnswer {
    private String[] validOptions;
    private Survey chosenSurvey;
    private String choice;
    private String name;
    private UICommandLineScanner scan = new UICommandLineScanner();

    public UICommandLineAnswer() {
    }

    public void UIShowAnswer(ArrayList<Survey> surveys) {
        //Sets validOptions to the indexes of surveys.
        this.validOptions = new String[surveys.size()];
        for (Survey s : surveys) {
            validOptions[surveys.indexOf(s)] = String.valueOf(surveys.indexOf(s)+1);
        }

        //prints all surveys and lets the user chose one to answer.
        //maybe make list of surveys output start at 1?
        int counter = 1;
        for (Survey s : surveys) {
            System.out.println(counter + ": " + s);
            counter++;
        }
        chosenSurvey = surveys.get(Integer.parseInt(scan.getUserInput("choose a survey from the list to answer by writing the corresponding number:", validOptions))-1);

        name = scan.getUserInput("Write your name:");

        //prints all questions and their answer choices and lets the user choose an answer.
        for (Question q : chosenSurvey.getQuestions()) {
            do {
                System.out.println(q);
                System.out.println("Answers: \n"+q.getAnswers().keySet());

                validOptions = q.getAnswers().keySet().toArray(new String[q.getAnswers().size()]);
                choice = scan.getUserInput("Choose one of the listed answers to the above question by writing the answer:", validOptions);
            }while (!q.getAnswers().containsKey(choice));
            int newChoiceValue = q.getAnswers().get(choice) + 1;
            q.getAnswers().put(choice, newChoiceValue);
        }
    }
}