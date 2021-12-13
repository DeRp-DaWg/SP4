package com.example.sp4.UI.JavaFX;

import com.example.sp4.Comparators.LocationComparator;
import com.example.sp4.Comparators.QuestionsComparator;
import com.example.sp4.Comparators.TitleComparator;
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
    private LinkedHashMap<String, Comparator<Survey>> choiceBoxItemss = new LinkedHashMap<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxItemss.put("Sort by title", new TitleComparator());
        choiceBoxItemss.put("Sort by questions", new QuestionsComparator());
        choiceBoxItemss.put("Sort by save location", new LocationComparator());
        
        observableList = FXCollections.observableArrayList(choiceBoxItemss.keySet().toArray(new String[0]));
        choiceBox.setItems(observableList);
        choiceBox.setValue(choiceBoxItemss.keySet().toArray()[0]);
        choiceBox.setOnAction(actionEvent -> onChoiceBoxItemChange());
        
        showLocal = showLocalCheckBox.isSelected();
        showDatabase = showDatabaseCheckBox.isSelected();
        
        if (surveys == null) return;
        surveys.sort(choiceBoxItemss.get("Sort by title"));
        updateSurveysBox();
        changeActiveSurvey(0);
    }
    
    private void updateSurveysBox() {
        surveysBox.getChildren().clear();
        for (int i = 0; i < surveys.size(); i++) {
            boolean fromDB = surveys.get(i).isFromDB();
            boolean fromFile = !fromDB;
            if ((fromFile == showLocal || fromDB == showDatabase) && (showLocal || showDatabase)) {
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
        }
    }
    
    private void changeActiveSurvey(int i) {
        survey = surveys.get(i);
        surveyTitleLabel.setText(survey.getSurveyTitle());
        surveyDescriptionLabel.setText(survey.getSurveyDescription());
        surveyAmountOfQuestionsLabel.setText("Questions: "+survey.getQuestions().size());
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
        String[] choiceBoxItemKeys = choiceBoxItemss.keySet().toArray(new String[0]);
        for (int i = 0; i < choiceBoxItemKeys.length; i++) {
            if (choiceBoxItemKeys[i].equals(choiceBox.getValue())) {
                surveys.sort(choiceBoxItemss.get(choiceBoxItemKeys[i]));
            }
        }
        surveysBox.getChildren().clear();
        updateSurveysBox();
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
