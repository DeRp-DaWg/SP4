package com.example.sp4.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class MultipleChoice extends Question {
   public MultipleChoice(String title, int amountOfAnswers){}
    public void addQuestion(String title){}

    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;

    public MultipleChoice(String surveyTitle, String surveyDescription, String name, String email) {
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.name = name;
        this.email = email;

        Scanner sc = new Scanner(System.in);
        ArrayList<String> questions = new ArrayList<>();
        String getNextQuestion = "";
        String getNextAnswerInput = "";
        HashMap<String, ArrayList<String>> answerPossibilites = new HashMap<>();
        while(!getNextQuestion.toLowerCase().equals("e")){
            ArrayList<String> getNextAnswer = new ArrayList<>();
            System.out.println("Enter the questions you want to ask and their answer possibilites. Click E when you're done: ");
            getNextQuestion = sc.nextLine();
            if(getNextQuestion.equals("e")){
                break;
            }

            while(true){
                System.out.println("Enter the answer possibilites for this question, type b when you're done: ");
                getNextAnswerInput = sc.nextLine();
                if(getNextAnswerInput.equals("b")){
                    break;
                }
                getNextAnswer.add(getNextAnswerInput);
            }
            answerPossibilites.put(getNextQuestion, getNextAnswer);
        }
        System.out.println(answerPossibilites);

        //I hashmappet answerPossibilites ligger spørgsmålene, efterfulgt af svarmulighederne i et smukt array
    }
}
