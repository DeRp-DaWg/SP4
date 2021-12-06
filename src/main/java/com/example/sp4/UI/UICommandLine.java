package com.example.sp4.UI;

import com.example.sp4.Question.MultipleChoice;

import java.util.Scanner;

public class UICommandLine {
    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;

    public UICommandLine(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Din surveys titel: ");
        surveyTitle = sc.nextLine();

        System.out.println("survey description: ");
        surveyDescription = sc.nextLine();

        //MultipleChoice multipleChoice = new MultipleChoice(surveyTitle, surveyDescription, name, email);

        System.out.println(surveyTitle + "\n" + surveyDescription);
    }
}
