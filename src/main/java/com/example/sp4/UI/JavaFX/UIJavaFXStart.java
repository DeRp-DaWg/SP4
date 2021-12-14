package com.example.sp4.UI.JavaFX;

import com.example.sp4.Comparators.LocationComparator;
import com.example.sp4.Comparators.QuestionsComparator;
import com.example.sp4.Comparators.TitleComparator;
import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Survey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UIJavaFXStart extends UIJavaFX implements Initializable {
    private boolean showLocal;
    private boolean showDatabase;
    
    @FXML
    private VBox surveysBox;
    
    @FXML
    private Label surveyTitleLabel;
    
    @FXML
    private Label surveyDescriptionLabel;
    
    @FXML
    private Label surveyAmountOfQuestionsLabel;
    
    @FXML
    private Button openSurveyButton;
    
    @FXML
    private Button openSurveyResultsButton;
    
    @FXML
    private CheckBox showLocalCheckBox;
    
    @FXML
    private CheckBox showDatabaseCheckBox;
    
    @FXML
    private ChoiceBox choiceBox;
    
    private ObservableList<String> observableList;
    private LinkedHashMap<String, Comparator<Survey>> choiceBoxItems = new LinkedHashMap<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxItems.put("Sort by title", new TitleComparator());
        choiceBoxItems.put("Sort by questions", new QuestionsComparator());
        choiceBoxItems.put("Sort by save location", new LocationComparator());
        
        observableList = FXCollections.observableArrayList(choiceBoxItems.keySet().toArray(new String[0]));
        choiceBox.setItems(observableList);
        choiceBox.setValue(choiceBoxItems.keySet().toArray()[0]);
        choiceBox.setOnAction(actionEvent -> onChoiceBoxItemChange());
        
        showLocal = showLocalCheckBox.isSelected();
        showDatabase = showDatabaseCheckBox.isSelected();
        
        if (surveys.size() > 0) {
            surveys.sort(choiceBoxItems.get("Sort by title"));
            updateSurveysBox();
            changeActiveSurvey(surveys.get(0));
        }
        else {
            noSurveys();
        }
    }
    
    private void noSurveys() {
        openSurveyButton.setDisable(true);
        openSurveyResultsButton.setDisable(true);
        surveyTitleLabel.setText("");
        surveyDescriptionLabel.setText("");
        surveyAmountOfQuestionsLabel.setText("");
    }
    
    private void updateSurveysBox() {
        if (surveys.size() == 0) noSurveys();
        surveysBox.getChildren().clear();
        for (int i = 0; i < surveys.size(); i++) {
            boolean fromDB = surveys.get(i).isFromDB();
            boolean fromFile = !fromDB;
            if ((fromFile == showLocal || fromDB == showDatabase) && (showLocal || showDatabase)) {
                HBox buttonHBox = new HBox();
                Button removeButton = new Button("X");
                Button button = new Button(surveys.get(i).getSurveyTitle());
                buttonHBox.getChildren().addAll(button, removeButton);
                button.setMinHeight(Double.NEGATIVE_INFINITY);
                button.setMinWidth(Double.NEGATIVE_INFINITY);
                button.setMnemonicParsing(false);
                button.setPrefHeight(40);
                button.setMinWidth(213);
                int finalI = i;
                Survey buttonSurvey = surveys.get(finalI);
                button.setOnAction(actionEvent -> changeActiveSurvey(buttonSurvey));
                
                removeButton.setPrefHeight(40);
                removeButton.setOnAction(actionEvent -> removeSurvey(buttonSurvey));
                //int finalI = i;
                //button.setOnAction(actionEvent -> changeActiveSurvey(finalI));
                surveysBox.getChildren().add(buttonHBox);
            }
        }
    }
    
    private void changeActiveSurvey(Survey buttonSurvey) {
        survey = buttonSurvey;
        surveyTitleLabel.setText(survey.getSurveyTitle());
        surveyDescriptionLabel.setText(survey.getSurveyDescription());
        surveyAmountOfQuestionsLabel.setText("Questions: "+survey.getQuestions().size());
    }
    
    private void removeSurvey(Survey survey) {
        if (survey.isFromDB()) {
            ioDatabase.remove(surveys, survey);
        }
        else {
            ioFile.remove(surveys, survey);
        }
        surveys.remove(survey);
        updateSurveysBox();
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
    
    @FXML
    private void onChoiceBoxItemChange() {
        if (surveys == null) return;
        String[] choiceBoxItemKeys = choiceBoxItems.keySet().toArray(new String[0]);
        for (int i = 0; i < choiceBoxItemKeys.length; i++) {
            if (choiceBoxItemKeys[i].equals(choiceBox.getValue())) {
                surveys.sort(choiceBoxItems.get(choiceBoxItemKeys[i]));
            }
        }
        surveysBox.getChildren().clear();
        updateSurveysBox();
    }
    // NIX PILLE
    @FXML
    private void onOpenSurveyResults(){
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Read.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onShowLocal() {
        showLocal = showLocalCheckBox.isSelected();
        updateSurveysBox();
    }
    
    @FXML
    private void onShowDatabase() {
        showDatabase = showDatabaseCheckBox.isSelected();
        updateSurveysBox();
    }
}
