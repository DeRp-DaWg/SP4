package com.example.sp4.UI.CommandLine;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class UICommandLineStart {
    public void run() {
        String choice;
        String[] validOptions = {"q", "n", "a"};

        System.out.println("\n" + "Press Q to quit");
        System.out.println("Press N to create a new survey");
        System.out.println("Press A to answer an existing survey");

        choice = getUserInput(":", validOptions);

        if (choice.equals("n")) {
            UICommandLineCreate.run();//maybe use UIShowCreate???

        }
        if (choice.equals("a")) {
            UICommandLineAnswer.run();//maybe use UIShowCreate???

        }
        if (choice.equals("q")) {
            System.out.println("Quitting...");
        }
    }

    public String getUserInput(String msg, String[] validOptions) {//get user input which must match menu options
        Scanner sc = new Scanner(System.in);// maybe put scanner initiation in constructor???
        String s = sc.next();
        if (!Set.of(validOptions).contains(s.toLowerCase())) {
            System.out.println(("Invalid input!"));
            s = getUserInput(msg, validOptions);
        }
        return s.toLowerCase();
    }

    public String getUserInput(String msg) { //get user input which could be anything
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
