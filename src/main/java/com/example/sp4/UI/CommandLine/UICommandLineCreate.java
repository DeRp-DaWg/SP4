package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UICreate;

import java.util.Scanner;

public class UICommandLineCreate {
    private String surveyTitle;
    private String surveyDescription;
    private String name;
    private String email;

    public UICommandLineCreate(){
    }

    public void UICreate(ArrayList<Survey> surveys) {

        surveyTitle = scan.getUserInput("Whats the title of your survey?");

            System.out.println("Din surveys titel: ");
            surveyTitle = sc.nextLine();

            System.out.println("survey description: ");
            surveyDescription = sc.nextLine();

        String question1 = (scan.getUserInput("Add a question:"));
        Question quest = new Question(question1);
        survey.addQuestions(quest);

        createQuestionAnswer(quest);
        String questionChoice;
        do {
            questionChoice = scan.getUserInputYesOrNo("Do you want to add another question?(y/n)");
            if (questionChoice.equalsIgnoreCase("y")) {
                String question2 = (scan.getUserInput("Question name:"));
                Question quest2= new Question(question2);
                survey.addQuestions(quest2);
                createQuestionAnswer(quest2);
            }
        } while (!questionChoice.equalsIgnoreCase("n"));
        surveys.add(survey);
    }

            //System.out.println(surveyTitle + "\n" + surveyDescription);
        }
    }
}
