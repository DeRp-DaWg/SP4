package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.UI.UICreate;

import java.util.Scanner;

public class UICommandLineCreate implements UICreate {
    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;

    public UICommandLineCreate(){
    }

    public static void run() {
    }

    @Override
    public void UIShowCreate(String surveyType) {
        if(surveyType.equals("multiplechoice")){
            Scanner sc = new Scanner(System.in);

            System.out.println("Din surveys titel: ");
            surveyTitle = sc.nextLine();

            System.out.println("survey description: ");
            surveyDescription = sc.nextLine();

            //Tilføj name og email

            MultipleChoice multipleChoice = new MultipleChoice();

            //System.out.println(surveyTitle + "\n" + surveyDescription);
        }
    }
}
