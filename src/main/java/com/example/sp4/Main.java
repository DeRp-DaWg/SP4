package com.example.sp4;

import com.example.sp4.IO.IODatabase;
import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.UI.CommandLine.UICommandLineStart;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        Survey survey = new Survey("test","test");
        IODatabase ioDatabase = new IODatabase();
        ioDatabase.read("age survey");
        System.out.println(ioDatabase.getIdOfSurvey("Age survey"));

        Controller controller = new Controller();

      //Anders test
        HelloApplication helloApplication = new HelloApplication();
       helloApplication.hello = "Oh No";
       */
       // oscar test:
        ArrayList<Survey> surveys = new ArrayList<>();
        UICommandLineStart start = new UICommandLineStart();
        start.UIShowStart(surveys);
        System.out.println(surveys.get(0).getQuestions().get(0).getAnswers());


    }
}
