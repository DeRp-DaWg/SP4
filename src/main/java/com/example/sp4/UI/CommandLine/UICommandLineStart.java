package com.example.sp4.UI.CommandLine;

import com.example.sp4.Survey;

import java.util.ArrayList;

public class UICommandLineStart{
    private String userChoice;
    private String[] validOptions = {"q", "c", "a"};
    private UICommandLineScanner scan = new UICommandLineScanner();

    public ArrayList<Survey> run(ArrayList<Survey> surveys) {
        do {
            System.out.println("\n" + "Press Q to quit");
            System.out.println("Press C to create a new survey");
            System.out.println("Press A to answer an existing survey");

            userChoice = scan.getUserInput("", validOptions);
            if (userChoice.equals("c")) {
                UICommandLineCreate create = new UICommandLineCreate();
                create.(surveys);

            }
            if (userChoice.equals("a")) {
                UICommandLineAnswer answer = new UICommandLineAnswer(surveys);
                answer.UIShowAnswer();

            }
        } while (!userChoice.equals("q"));
        System.out.println("Quitting...");
        return surveys;
    }
}
