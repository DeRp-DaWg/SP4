package com.example.sp4.UI.JavaFX;

import com.example.sp4.Controller;
import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.*;
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

public class UIJavaFXMain extends Application implements UIInit, UIStart, UIAnswer, UICreate {
    private Stage stage;
    private ArrayList<Survey> surveys = new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws Exception {
        UI ui = new UI();
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
        this.stage = stage;
        
        FXMLLoader startFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
        Scene startScene = new Scene(startFXMLLoader.load(), 600, 400);
        
        
        UIJavaFX.setStage(stage);
        
        this.stage.setTitle("Survey program");
        
        this.stage.setScene(startScene);
        this.stage.show();
        this.stage.setMinWidth(stage.getWidth());
        this.stage.setMinHeight(stage.getHeight());
    
        
    }
    public void launchButton(){
        launch();
    }

    public static void main(String[] args) {
        Application.launch();
    }
    
    @Override
    public void initt() {
        Application.launch();
    }
    
    @Override
    public Survey UIShowAnswer(Survey survey) {
        return null;
    }
    
    @Override
    public Survey UIShowCreate() {
        return null;
    }
    
    @Override
    public Survey UIShowStart(Survey[] surveys) {
        return null;
    }
    
    @Override
    public String getIOType() {
        return null;
    }
}
