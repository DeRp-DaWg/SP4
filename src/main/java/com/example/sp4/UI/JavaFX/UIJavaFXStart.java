package com.example.sp4.UI.JavaFX;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UIStart;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIJavaFXStart extends UIJavaFX implements Initializable {
    private String IOType = "file";

    public String getIOType() {
        return IOType;
    }
    
    @FXML
    private VBox surveysBox;
    
    @FXML
    private Label surveyTitleLabel;
    
    @FXML
    private Label surveyDescriptionLabel;
    
    @FXML
    private Button openSurveyButton;
    
    @FXML
    private Button openSurveyResultsButton;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (surveys == null) return;
        Collections.sort(surveys);
        for (int i = 0; i < surveys.size(); i++) {
            Button button = new Button(surveys.get(i).getSurveyTitle());
            button.setMinHeight(Double.NEGATIVE_INFINITY);
            button.setMinWidth(Double.NEGATIVE_INFINITY);
            button.setMnemonicParsing(false);
            button.setPrefHeight(40);
            button.setMinWidth(213);
            int finalI = i;
            button.setOnAction(actionEvent -> changeActiveSurvey(finalI));
            surveysBox.getChildren().add(button);
        }
        changeActiveSurvey(0);
    }
    
    private void changeActiveSurvey(int i) {
        survey = surveys.get(i);
        surveyTitleLabel.setText(survey.getSurveyTitle());
        surveyDescriptionLabel.setText(survey.getSurveyDescription());
    }
    
    @FXML
    private void onOpenSurvey() {
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Answer.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //stage.setScene(sceneHashMap.get("answer"));
    }
    
    @FXML
    private void onCreateNewSurvey() {
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Create.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
