package com.example.sp4;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

import java.util.ArrayList;

public class Survey {

    public Survey(){
        UICommandLineCreate uiCommandLineCreate = new UICommandLineCreate();
    }
    //ArrayList<Question> listOfQuestions;


/*public void addQuestion(){
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
            Question question = new MultipleChoice();
            listOfQuestions.add(question);
            break;
    }
}
*/
}
