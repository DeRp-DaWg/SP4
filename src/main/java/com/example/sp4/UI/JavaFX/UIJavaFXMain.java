package com.example.sp4.UI.JavaFX;

import com.example.sp4.Controller;
import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIJavaFXMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<Survey> surveys = new ArrayList<>();
        try {
            IO io = new IOFile();
            surveys.addAll(io.read());
        }
        catch (Exception e) {
            System.out.println("Files not loaded");
        }
        try {
            IO io = new IODatabase();
            surveys.addAll(io.read());
        }
        catch (Exception e) {
            System.out.println("Database not loaded");
        }
        UIJavaFX.setSurveys(surveys);
    
        FXMLLoader startFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Scene startScene = new Scene(startFXMLLoader.load(), 600, 400);
        
        
        UIJavaFX.setStage(stage);
        
        stage.setTitle("Survey program");
        
        stage.setScene(startScene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        
    }
    public void launchButton(){
        launch();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
