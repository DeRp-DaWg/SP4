package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Survey;
import com.example.sp4.UI.UICreate;

import java.util.Scanner;

public class UICommandLineCreate extends UICommandLine implements UICreate {
    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;

    public UICommandLineCreate() {
    }

    @Override
    public Survey UIShowCreate() {
        return null;
    }

    /*
    @Override
    public void UIShowCreate(String surveyType) {
        if (surveyType.equals("multiplechoice")) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Din surveys titel: ");
            surveyTitle = sc.nextLine();

            System.out.println("survey description: ");
            surveyDescription = sc.nextLine();

            Survey survey = new Survey(surveyTitle, surveyDescription);

            //System.out.println(surveyTitle + "\n" + surveyDescription);
        }
    }
    */
}
