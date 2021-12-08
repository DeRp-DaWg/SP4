package com.example.sp4.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class MultipleChoice extends Question{
    HashMap<String, Integer> answers;
    
    public MultipleChoice(String questionName, String questionDescription) {
        super(questionName, questionDescription);
    }
    
    public MultipleChoice(String questionName, String questionDescription, String[] answers) {
        super(questionName, questionDescription);
        for (String answer : answers) {
            addAnswer(answer);
        }
    }
    
    @Override
    public void addAnswer(String answer) {
        this.answers.put(answer, 0);
    }
    
    @Override
    public void addResult(String answer) {
        this.answers.put(answer, this.answers.get(answer) + 1);
    }
    
    @Override
    public HashMap<String, Integer> getAnswers() {
        return answers;
    }
    
    
    
    /*public MultipleChoice() {
    
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
    }*/
}
