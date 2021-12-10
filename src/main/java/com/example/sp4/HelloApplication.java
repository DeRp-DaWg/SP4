package com.example.sp4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class HelloApplication extends Application {
  public HelloApplication(){

    }
   static ArrayList<ArrayList<String>> questions = new ArrayList<>();
   static ArrayList<String> ohNo = new ArrayList<>();
    String hello = "a";

    public static void setQuestions(ArrayList<ArrayList<String>> questions) {
        HelloApplication.questions = questions;
    }

    public void setOhNo(ArrayList<String> ohNo) {
        this.ohNo = ohNo;
    }


    public ArrayList<String> getOhNo() {
        return ohNo;
    }

    @Override
    public void start(Stage s) {
        ArrayList<Scene> scenes = new ArrayList<>();
        ArrayList<StackPane> stackPanes = new ArrayList<>();
        ohNo = getOhNo();
        // set title for the stage
        s.setTitle("creating buttons");
        ToggleGroup group = new ToggleGroup();
        ArrayList<RadioButton> buttonList = new ArrayList<>();
        for (int j = 0; j < questions.size(); j++) {
            StackPane r = new StackPane();
            stackPanes.add(r);
            if(j != questions.size()-1) {
                Button button = new Button();
               button.setTranslateX(button.getTranslateX() + 100);
                button.setText("Next Question");
                button.setOnAction(System.out::println);
                //button.setOnAction(e -> s.setScene( scenes.get(finalJ)));
                int event = j;
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        s.setScene(scenes.get(event+1));
                    }
                });
                stackPanes.get(j).getChildren().add(button);

            }
            for (int i = 0; i < questions.get(j).size(); i++) {
                RadioButton b = new RadioButton(questions.get(j).get(i));
                buttonList.add(b);
                b.setTranslateX(0);
                if (i != 0) {
                    b.setTranslateY(buttonList.get(i - 1).getTranslateY() + 20);

                }
                b.setToggleGroup(group);
                stackPanes.get(j).getChildren().add(b);
            }
             Scene sc = new Scene(r, 200, 200);
             scenes.add(sc);

            // set the scene

        }
        s.setScene(scenes.get(0));

        s.show();
    }
    public void launchHello(){
        launch();
    }
    
    public static void main(String[] args) {

        launch();
    }
}