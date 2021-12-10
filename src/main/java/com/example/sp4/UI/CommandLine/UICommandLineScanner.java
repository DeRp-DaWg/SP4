package com.example.sp4.UI.CommandLine;

import java.util.Scanner;
import java.util.Set;

public class UICommandLineScanner {
    private Scanner sc;

    public UICommandLineScanner(){
        sc = new Scanner(System.in);
    }

    public String getUserInput(String msg) { //get user input which could be anything
        System.out.println(msg);
        return sc.nextLine();
    }

    public String getUserInput(String msg, String[] validOptions) {//get user input which must match menu options
        System.out.println(msg);
        String s = sc.nextLine();
        if (!Set.of(validOptions).contains(s.toLowerCase())) {
            System.out.println(("Invalid input!"));
            s = getUserInput(msg, validOptions);
        }
        return s.toLowerCase();
    }

    public String getUserInputYesOrNo(String msg) {//get user input which must be y or n
        System.out.println(msg);
        String[] validOptions = {"y", "n"};
        String s = sc.nextLine();
        if (!Set.of(validOptions).contains(s.toLowerCase())) {
            System.out.println(("Invalid input!"));
            s = getUserInput(msg, validOptions);
        }
        return s.toLowerCase();
    }
}