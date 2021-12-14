package com.example.sp4.UI.JavaFX;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionContainer {
    private VBox questionVBox = new VBox();
    private TextField questionTitleTextField = new TextField();
    private TextField questionDescriptionTextField = new TextField();
    private VBox answersVBox = new VBox();
    private ArrayList<TextField> answerTextFields = new ArrayList<>();
    private HashMap<TextField, Button> textFieldButtonHashMap = new HashMap<>();
    private Button onAddNewAnswerButton = new Button("Add new answer");
    
    public QuestionContainer(HBox hBox) {
        questionTitleTextField.setPromptText("Question title");
        removeStyles(questionTitleTextField);
        questionTitleTextField.setOnKeyTyped(KeyEvent -> removeStyles(questionTitleTextField));
        questionDescriptionTextField.setPromptText("Question description");
        removeStyles(questionDescriptionTextField);
        onAddNewAnswerButton.setOnAction(actionEvent -> onAddNewAnswer());
        questionVBox.getChildren().addAll(questionTitleTextField, questionDescriptionTextField, answersVBox, onAddNewAnswerButton);
        hBox.getChildren().add(questionVBox);
        onAddNewAnswer();
        onAddNewAnswer();
    }
    
    private void onAddNewAnswer() {
        HBox answerHBox = new HBox();
        TextField answerTextField = new TextField();
        answerTextField.setPromptText("Answer "+(answerTextFields.size()+1));
        removeStyles(answerTextField);
        answerTextField.setOnKeyTyped(keyEvent -> removeStyles(answerTextField));
        answerTextFields.add(answerTextField);
        answerHBox.getChildren().add(answerTextField);
        Button removeAnswerButton = new Button("X");
        textFieldButtonHashMap.put(answerTextField, removeAnswerButton);
        removeAnswerButton.setOnAction(actionEvent -> onRemoveAnswer(answerHBox, answerTextField));
        answerHBox.getChildren().add(removeAnswerButton);
        answersVBox.getChildren().add(answerHBox);
        if (answerTextFields.size() > 2) {
            setDisableOnButtons(false);
        }
        else {
            removeAnswerButton.setDisable(true);
        }
    }
    
    private void onRemoveAnswer(HBox answerHBox, TextField answerTextField) {
        int index = answerTextFields.indexOf(answerTextField);
        answerTextFields.remove(answerTextField);
        for (int i = index; i < answerTextFields.size(); i++) {
            answerTextFields.get(i).setPromptText("Answer "+(i+1));
        }
        answersVBox.getChildren().remove(answerHBox);
        if (answerTextFields.size() <= 2) {
            setDisableOnButtons(true);
        }
    }
    
    private void setDisableOnButtons(boolean state) {
        for (TextField textField : textFieldButtonHashMap.keySet()) {
            textFieldButtonHashMap.get(textField).setDisable(state);
        }
    }
    
    public TextField getQuestionTitleTextField() {
        return questionTitleTextField;
    }
    
    public TextField getQuestionDescriptionTextField() {
        return questionDescriptionTextField;
    }
    
    public ArrayList<TextField> getAnswerTextFields() {
        return answerTextFields;
    }
    
    public static void removeStyles(TextField textField) {
        textField.setStyle("-fx-border-color: lightgray");
    }
    
    public ArrayList<TextField> findIllegalTextFields() {
        ArrayList<TextField> textFields = new ArrayList<>();
        if (questionTitleTextField.getText().equals("")) textFields.add(questionTitleTextField);
        for (TextField answerTextField : answerTextFields) {
            if (answerTextField.getText().equals("")) textFields.add(answerTextField);
        }
        return textFields;
    }
}
