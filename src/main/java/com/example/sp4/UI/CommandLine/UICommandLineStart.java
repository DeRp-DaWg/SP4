package com.example.sp4.UI.CommandLine;

import com.example.sp4.Survey;

import java.util.ArrayList;

public class UICommandLineStart {
    private String choice;
    private String[] validOptions = {"q", "c", "a","v"};
    private UICommandLineScanner scan = new UICommandLineScanner();

    public UICommandLineStart() {
    }

    public void UIShowStart(ArrayList<Survey> surveys) {
        do {
            System.out.println("\n" + "Press Q to quit");
            System.out.println("Press C to create a new survey");
            System.out.println("Press A to answer an existing survey");
            System.out.println("Press V to answer an existing survey");

            choice = scan.getUserInput("", validOptions);
            if (choice.equals("c")) {
                UICommandLineCreate create = new UICommandLineCreate();
                create.UICreate(surveys);

            }
            if (choice.equals("a")) {
                UICommandLineAnswer answer = new UICommandLineAnswer();
                answer.UIShowAnswer(surveys);

            }
            if (choice.equals("v")) {
                UICommandLineViewAnswers answerView = new UICommandLineViewAnswers();
                answerView.UIShowViewAnswers(surveys);
            }
        } while (!choice.equals("q"));
        System.out.println("Quitting...");
    }
}