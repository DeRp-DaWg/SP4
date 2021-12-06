package com.example.sp4.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class MultipleChoice extends Question{
    public MultipleChoice() {

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
                System.out.println("Enter what people can answer, type b when you're done: ");
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
