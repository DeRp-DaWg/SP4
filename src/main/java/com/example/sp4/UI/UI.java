package com.example.sp4.UI;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;

import java.util.Scanner;

public class UI {
    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;
    Scanner sc = new Scanner(System.in);
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
}
