package com.example.sp4.UI;

import com.example.sp4.Survey;
import com.example.sp4.UI.CommandLine.UICommandLineStart;
import com.example.sp4.UI.CommandLine.UICommandLineAnswer;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

public class UI {
    //private String surveyTitle;
    //private String surveyDescription;
    private UIStart uiStart = new UICommandLineStart();
    private UIAnswer uiAnswer = new UICommandLineAnswer();
    private UICreate uiCreate = new UICommandLineCreate();
    /*Scanner sc = new Scanner(System.in);
    public void start(){
        System.out.println("What do you want to do?");
    }
    public void createSurvey(){
        System.out.println("What is the title of your survey?");
    surveyTitle = sc.nextLine();

        System.out.println("What is your surveys description?");
    surveyDescription = sc.nextLine();

    Survey survey = new Survey(surveyTitle, surveyDescription);
    addQuestion(survey);
    }
    public void addQuestion(Survey survey) {
        boolean done = false;
        while (done != true) {
            System.out.println("What is your question?");
            String question = sc.nextLine();
            System.out.println("What kind of question do you want have?");
            String input = sc.nextLine();
            switch (input) {
                case "MultipleChoice":
                    System.out.println("How many choices do you want?");
                    int choices = sc.nextInt();
                   // Question multipleChoice = new MultipleChoice(question, choices);
                   // survey.getQuestions().add(multipleChoice);

            }
            System.out.println("Do you want to add more questions?");
            String areWeDone = sc.nextLine();
            if (areWeDone.equals("yes")) {
                done = true;
            }
        }
    }
    */

    /*
    public void showCMD() {
        uiStart = new UICommandLineStart();
        uiAnswer = new UICommandLineAnswer();
        uiCreate = new UICommandLineCreate();
    }
    public void showGUI() {
        uiStart = new UIJavaFXStart();
        uiAnswer = new UIJavaFXAnswer();
        uiCreate = new UIJavaFXCreate();
    }
    */
    public Survey start(Survey[] surveys) {
        return uiStart.UIShowStart(surveys);
    }

    public Survey answer(Survey survey) {
        return uiAnswer.UIShowAnswer(survey);
    }

    public Survey create() {
        return uiCreate.UIShowCreate();
    }

    public String getIOType() {
        return uiStart.getIOType();
    }
    
    
    //Starter med at vise uiStart.
    /*
    public Survey start() {
        Survey survey = uiStart.UIShowStart();
        if (survey == null) {
            survey = uiCreate.UIShowCreate();
        }
        if (survey != null) {
            uiAnswer.UIShowAnswer(survey);
        }
        return survey;
    }
    */
}
