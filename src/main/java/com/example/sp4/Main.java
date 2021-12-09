package com.example.sp4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Controller controller = new Controller();
      //Anders test
        ArrayList<String> ohNo = new ArrayList<>();
        ArrayList<String> ohNo1 = new ArrayList<>();
        ArrayList<String> ohNo2 = new ArrayList<>();
        ArrayList<ArrayList<String>> questions = new ArrayList<>();
        questions.add(ohNo);
        questions.add(ohNo1);
        questions.add(ohNo2);
        String type = "";
        ohNo.add("Pizza");
        ohNo.add("Pasta");
        ohNo.add("Pumpkin");
        ohNo1.add("Potion");
        ohNo1.add("Pancake");
        ohNo2.add("Hello");
        ohNo2.add("ohNo");
        ohNo2.add("average");
        ohNo2.add("goblin");

       /* Scanner scanner = new Scanner(System.in);
        while(!type.equals("no")){
            type = scanner.nextLine();
            if(!type.equals("no")){
                ohNo.add(type);
            }
        }*/
       HelloApplication helloApplication = new HelloApplication();
       helloApplication.setQuestions(questions);
       helloApplication.launchHello();
    }
}
