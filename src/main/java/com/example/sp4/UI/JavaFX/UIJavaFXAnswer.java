package com.example.sp4.UI.JavaFX;

import com.example.sp4.IO.IO;
import com.example.sp4.IO.IODatabase;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UIAnswer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIJavaFXAnswer extends UIJavaFX implements Initializable {
    @FXML
    private VBox questionBox;
    @FXML
    private Label questionTitle;
    @FXML
    private Label questionDescription;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestions();
    }
    
    @FXML
    public void loadQuestions(){
        for(int i = 0; i < survey.getQuestions().size();i++){
            ToggleGroup toggleGroup = new ToggleGroup();
            VBox questionBox = new VBox();
            Insets insets = new Insets(10);
            questionBox.setPadding(insets);
            questionBox.setSpacing(20);
            Label questionTitle = new Label();
            //The string in the new font dosen't change anything, I chose to write something because it looked weird when it was blank
            Font font = new Font("System", 24);
            questionTitle.setFont(font);
            Label questionDescription = new Label();
            questionTitle.setText(survey.getQuestions().get(i).getQuestionName());
            questionBox.getChildren().add(questionTitle);
            questionDescription.setText(survey.getQuestions().get(i).getQuestionDescription());
            questionBox.getChildren().add(questionDescription);
            String[] answers = survey.getQuestions().get(i).getAnswers().keySet().toArray(new String[0]);
            for (int j = 0; j < survey.getQuestions().get(i).getAnswers().size(); j++){
                RadioButton radioButton = new RadioButton();
                radioButton.setText(answers[j]);
                radioButton.setToggleGroup(toggleGroup);
                questionBox.getChildren().add(radioButton);
            }
            this.questionBox.getChildren().add(questionBox);
        }
    }
    @FXML
    public void loadAnswers(){
        for (int i = 0; i < questionBox.getChildren().size(); i++){
            if (questionBox.getChildren().get(i) instanceof VBox){
                for (int j = 0; j < ((VBox) questionBox.getChildren().get(i)).getChildren().size(); j++){
                    if(((VBox) questionBox.getChildren().get(i)).getChildren().get(j) instanceof RadioButton){
                        RadioButton result = ((RadioButton) ((VBox) questionBox.getChildren().get(i)).getChildren().get(j));
                        if (result.isSelected()){
                            survey.getQuestions().get(i).addResult(result.getText());
                        }
                    }
                }
            }
        }
        // this foreach loop is used to check if answers get registered, can be removed when were done.
        for (Question question : survey.getQuestions()){
            System.out.println(question.getQuestionName());
            for (String name : question.getAnswers().keySet()){
                String key = name.toString();
                String value = question.getAnswers().get(name).toString();
                System.out.println(key + " " + value);
            }
        }
    }
    @FXML
    public void endSurvey(){
        loadAnswers();
        if(survey.isFromDB()){
            ioDatabase.update(survey);
        }
        else {
            ioFile.update(survey);
        }
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void test(){
    }
}
