package com.example.sp4;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;

import java.util.ArrayList;
import java.util.Scanner;

public class Survey {
    public String getName() {
        return name;

    }

    String name;
ArrayList<Question> listOfQuestions;

public void addQuestion(){
    //scanner is temporary, it's there until UI is put together
    Scanner scanner = new Scanner(System.in);
    System.out.println("What do you want to ask?");
    String questionTitle = scanner.nextLine();
    System.out.println("How many answers do you want?");
    int amountOfAnswers = scanner.nextInt();
    Question question = new MultipleChoice(questionTitle,amountOfAnswers);
    listOfQuestions.add(question);
}
//Skeleton to if we add more kinds of questions
public void addQuestion(String input){
    switch (input){
        case "MultipleChoice":
           // Question question = new MultipleChoice();
         //   listOfQuestions.add(question);
            break;
    }
}

}
