package com.example.sp4.UI.JavaFX;

import com.example.sp4.Survey;
import com.example.sp4.UI.UIAnswer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
            Label questionTitle = new Label();
            Label questionDescription = new Label();
            questionTitle.setId(survey.getQuestions().get(i).getQuestionName());
            questionBox.getChildren().add(questionTitle);
            questionDescription.setId(survey.getQuestions().get(i).getQuestionDescription());
            questionBox.getChildren().add(questionDescription);
            for (int j = 0; j < survey.getQuestions().get(i).getAnswers().size(); j++){
                RadioButton radioButton = new RadioButton();
                radioButton.setId(survey.getQuestions().get(i).getAnswers().toString());
                radioButton.setToggleGroup(toggleGroup);
                questionBox.getChildren().add(radioButton);
            }
            this.questionBox.getChildren().add(questionBox);
        }
    }
    @FXML
    public void endSurvey(){
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
