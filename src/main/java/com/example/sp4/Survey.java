package com.example.sp4;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.UI.CommandLine.UICommandLineCreate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Survey implements Serializable {
    public static final long serialVersionUID = 104877509L;
    private String surveyName;
    private String surveyDescription;
    private ArrayList<Question> questions;
    
    public Survey(String surveyName, String surveyDescription) {
        this.surveyName = surveyName;
        this.surveyDescription = surveyDescription;
    }
    
    public Survey(String surveyName, String surveyDescription, ArrayList<Question> questions) {
        this.surveyName = surveyName;
        this.surveyDescription = surveyDescription;
        this.questions = questions;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public String getSurveyName() {
        return surveyName;
    }
    
    public String getSurveyDescription() {
        return surveyDescription;
    }
    
    public ArrayList<Question> getQuestions() {
        return questions;
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
