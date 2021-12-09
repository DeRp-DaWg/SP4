package com.example.sp4.UI.CommandLine;

import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UIAnswer;

import java.util.ArrayList;
import java.util.Set;

public class UICommandLineAnswer implements UIAnswer {
    private String[] validOptions;
    private Survey chosenSurvey;
    private String name;
    private String email;
    private ArrayList<String> answersToQuestions;
    private UICommandLineScanner scan = new UICommandLineScanner();


    public UICommandLineAnswer() {// maybe put surveys parameter in UIShowAnswer instead?
        this.answersToQuestions = new ArrayList<>();
    }

    @Override
    public void UIShowAnswer(ArrayList<Survey> surveys) {
        this.validOptions = new String[surveys.size()];
        for (Survey s : surveys) {
            validOptions[surveys.indexOf(s)] = String.valueOf(surveys.indexOf(s));
        }

        int counter = 0;
        for (Survey s : surveys) {
            System.out.println(counter + ": " + s);
            counter++;
        }
        chosenSurvey = surveys.get(Integer.parseInt(scan.getUserInput("choose a survey from the list to answer by writing the corresponding number:(single choice)", validOptions)));

        name = scan.getUserInput("Write your name:");
        email = scan.getUserInput("Write your email");

        for (Question q : chosenSurvey.getQuestions()) {
            System.out.println(q);
            System.out.println(q.getAnswers().keySet());

            validOptions = q.getAnswers().keySet().toArray(new String[q.getAnswers().size()]);
            String choice = scan.getUserInput("Choose one of the listed answers to the above question by writing the answer:(single choice)", validOptions);
            if (q.getAnswers().containsKey(choice)) {
                int newChoiceValue = q.getAnswers().get(choice) + 1;
                answersToQuestions.add(choice);
                q.getAnswers().put(choice, newChoiceValue);
            }
        }
        System.out.println("Your answers:");
        for(String answers : answersToQuestions){
            System.out.println(answers);
        }
    }
}
