package com.example.sp4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class HelloApplication extends Application {
    String hello = "";
    @Override
    public void start(Stage s){
// set title for the stage
        s.setTitle("creating buttons");
        StackPane r = new StackPane();
        ArrayList<Button> buttonList = new ArrayList<>();
        // create a button
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            String lol = i+ hello;
            Button b = new Button(lol);
            buttonList.add(b);
            if (i != 0) {
                b.setTranslateY(buttonList.get(i - 1).getTranslateY() + 50);
            }
            r.getChildren().add(b);
        }
        // create a stack pane

        // add button

        // create a scene
        Scene sc = new Scene(r, 200, 200);

        // set the scene
        s.setScene(sc);

        s.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}