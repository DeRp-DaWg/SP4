package com.example.sp4.UI.JavaFX;

import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIJavaFXCreate extends UIJavaFX implements Initializable {
    private HashMap<TextField[], ArrayList<TextField>> questionsAndAnswersContainer = new HashMap<>();
    private ArrayList<QuestionContainer> questionContainers = new ArrayList<>();
    private HashMap<QuestionContainer, Button> questionContainerButtonHashMap = new HashMap<>();
    private String invalidStyle = "-fx-border-color: red";
    private boolean saveToDatabase;
    
    @FXML
    private TextField surveyTitleField;
    
    @FXML
    private TextArea surveyDescriptionField;
    
    @FXML
    private VBox questionsVBox;
    
    @FXML
    private CheckBox saveToDatabaseCheckBox;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveToDatabase = saveToDatabaseCheckBox.isSelected();
        onAddNewQuestion();
        surveyTitleField.setOnKeyTyped(keyEvent -> QuestionContainer.removeStyles(surveyTitleField));
    }
    
    @FXML
    private void onSendSurvey() {
        boolean isLegal = true;
        if (surveyTitleField.getText().equals("")) {
            surveyTitleField.setStyle(invalidStyle);
            surveyTitleField.setStyle("-fx-border-color: red");
            isLegal = false;
        }
        for (QuestionContainer questionContainer : questionContainers) {
            for (TextField illegalTextField : questionContainer.findIllegalTextFields()) {
                isLegal = false;
                illegalTextField.setStyle(invalidStyle);
            }
        }
        
        if (isLegal) {
            String surveyName = surveyTitleField.getText();
            String surveyDescription = surveyDescriptionField.getText();
            Survey survey = new Survey(surveyName, surveyDescription);
            for (QuestionContainer questionContainer : questionContainers) {
                String questionName = questionContainer.getQuestionTitleTextField().getText();
                String questionDescription = questionContainer.getQuestionDescriptionTextField().getText();
                Question question = new MultipleChoice(questionName, questionDescription);
                for (TextField answerTextField : questionContainer.getAnswerTextFields()) {
                    question.addAnswer(answerTextField.getText());
                }
                survey.addQuestion(question);
            }
            if (saveToDatabase) {
                ioDatabase.save(survey);
                survey.setFromDB(true);
            }
            else {
                ioFile.save(survey);
            }
            surveys.add(survey);
            try {
                FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
                Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
                stage.setScene(scene);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void onAddNewQuestion() {
        HBox questionContainerHBox = new HBox();
        QuestionContainer questionContainer = new QuestionContainer(questionContainerHBox);
        Button removeQuestionButton = new Button("X");
        removeQuestionButton.setOnAction(actionEvent -> onRemoveQuestion(questionContainer, questionContainerHBox));
        questionContainerHBox.getChildren().add(removeQuestionButton);
        questionsVBox.getChildren().add(questionContainerHBox);
        questionContainers.add(questionContainer);
        questionContainerButtonHashMap.put(questionContainer, removeQuestionButton);
        if (questionContainers.size() > 1) {
            questionContainerButtonHashMap.get(questionContainers.get(0)).setDisable(false);
        }
        else {
            removeQuestionButton.setDisable(true);
        }
    }
    
    @FXML
    private void onSaveToDatabase() {
        saveToDatabase = saveToDatabaseCheckBox.isSelected();
    }
    
    @FXML
    private void onCancel() {
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void onRemoveQuestion(QuestionContainer questionContainer, HBox questionContainerHBox) {
        questionContainers.remove(questionContainer);
        questionContainerButtonHashMap.remove(questionContainer);
        questionsVBox.getChildren().remove(questionContainerHBox);
        if (questionContainers.size() <= 1) {
            questionContainerButtonHashMap.get(questionContainers.get(0)).setDisable(true);
        }
    }
}
