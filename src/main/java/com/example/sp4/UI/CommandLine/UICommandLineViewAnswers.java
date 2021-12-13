package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.util.ArrayList;

public class UICommandLineViewAnswers {
    private String[] validOptions;
    private Survey chosenSurvey;
    private UICommandLineScanner scan = new UICommandLineScanner();

    public UICommandLineViewAnswers() {
    }

    public void UIShowViewAnswers(ArrayList<Survey> surveys) {
        //Sets validOptions to the indexes of surveys.
        this.validOptions = new String[surveys.size()];
        for (Survey s : surveys) {
            validOptions[surveys.indexOf(s)] = String.valueOf(surveys.indexOf(s)+1);
        }

        //prints all surveys and lets the user chose one to view the answers.
        //maybe make list of surveys output start at 1?
        int counter = 1;
        for (Survey s : surveys) {
            System.out.println(counter + ": " + s);
            counter++;
        }
        chosenSurvey = surveys.get(Integer.parseInt(scan.getUserInput("choose a survey from the list to view by writing the corresponding number:", validOptions))-1);
        for (Question q : chosenSurvey.getQuestions()) {
            System.out.println(q+"\nAnswers:");
            String s = String.valueOf(q.getAnswers()).replace("{"," ");
            String line = s.replace("}"," ");
            String[] lineArr = line.split(",");
            for (String string : lineArr) {
                System.out.println(string);
            }
        }
    }
}