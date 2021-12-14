package com.example.sp4.UI.JavaFX;

import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Survey;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class UIJavaFX {
//    protected static HashMap<String, Scene> sceneHashMap;
//    protected static HashMap<String, UIJavaFX> controllerHashMap;
    protected static Stage stage;
    protected static Survey survey;
    protected static ArrayList<Survey> surveys;
    protected static IO ioFile = new IOFile();
    protected static IO ioDatabase = new IODatabase();
    
//    public static void setSceneHashMap(HashMap<String, Scene> sceneHashMap) {
//        UIJavaFX.sceneHashMap = sceneHashMap;
//    }
    
//    public static void setControllerHashMap(HashMap<String, UIJavaFX> controllerHashMap) {
//        UIJavaFX.controllerHashMap = controllerHashMap;
//    }
    
    public static void setStage(Stage stage) {
        UIJavaFX.stage = stage;
    }
    
    public static void setSurvey(Survey survey) {
        UIJavaFX.survey = survey;
    }
    
    public static void setSurveys(ArrayList<Survey> surveys) {
        UIJavaFX.surveys = surveys;
    }
}
