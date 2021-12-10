package com.example.sp4.UI.JavaFX;

import com.example.sp4.IO.IO;
import com.example.sp4.IO.IOFile;
import com.example.sp4.Question.MultipleChoice;
import com.example.sp4.Question.Question;
import com.example.sp4.Survey;
import com.example.sp4.UI.UICreate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIJavaFXCreate extends UIJavaFX implements Initializable {
    //private ArrayList<ArrayList<TextField>> questionAnswersTextFields = new ArrayList<>();
    private HashMap<VBox, Integer> questionAnswersCounts = new HashMap<>();
    private HashMap<TextField[], ArrayList<TextField>> questionsAndAnswersContainer = new HashMap<>();
    
    @FXML
    private TextField surveyTitleField;
    
    @FXML
    private TextArea surveyDescriptionField;
    
    @FXML
    private VBox questionsVBox;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onAddNewQuestion();
    }
    
    @FXML
    private void onSendSurvey() {
        boolean isLegal = true;
        if (isLegal) {
            String surveyName = surveyTitleField.getText();
            String surveyDescription = surveyDescriptionField.getText();
            Survey survey = new Survey(surveyName, surveyDescription);
            for (TextField[] questionName : questionsAndAnswersContainer.keySet()) {
                String questionTitle = questionName[0].getText();
                String questionDescription = questionName[1].getText();
                Question question = new MultipleChoice(questionTitle, questionDescription);
                for (TextField textField : questionsAndAnswersContainer.get(questionName)) {
                    question.addAnswer(textField.getText());
                }
                survey.addQuestion(question);
            }
            /*System.out.println("Survey title = " + survey.getSurveyTitle());
            System.out.println("Survey description = " + survey.getSurveyDescription());
            for (Question question : survey.getQuestions()) {
                System.out.println("Question = " + question.getQuestionName());
                System.out.println("Question desc = " + question.getQuestionDescription());
                HashMap<String, Integer> answers = question.getAnswers();
                for (String str : answers.keySet()) {
                    System.out.println("Answer = " + str);
                }
            }*/
            IO io = new IOFile();
            io.save(survey);
            surveys.add(survey);
        }
        
        try {
            FXMLLoader createFXMLLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Scene scene = new Scene(createFXMLLoader.load(), 600, 400);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        stage.setScene(sceneHashMap.get("start"));
        
    }
    
    @FXML
    private void onAddNewQuestion() {
        VBox questionVBox = new VBox();
        VBox answersVBox = new VBox();
        TextField questionTitleTextField = new TextField();
        questionTitleTextField.setPromptText("Question title");
        TextField questionDescriptionTextField = new TextField();
        questionDescriptionTextField.setPromptText("Question description");
        Button button = new Button("Add new answer");
        //int finalQuestionsCount = questionsCount;
        
        ArrayList<TextField> answerTextFields = new ArrayList<>();
        questionAnswersCounts.put(answersVBox, 0);
        //questionAnswersTextFields.add(answerTextFields);
        
        TextField[] questionTextFields = {questionTitleTextField, questionDescriptionTextField};
        questionsAndAnswersContainer.put(questionTextFields, answerTextFields);
        
        
        button.setOnAction(actionEvent -> onAddNewAnswer(answersVBox, answerTextFields));
        
        answersVBox.getChildren().addAll(questionTitleTextField, questionDescriptionTextField, button);
        questionVBox.getChildren().addAll(answersVBox, button);
        
        questionsVBox.getChildren().add(questionVBox);
        //questionsCount++;
    }
    
    @FXML
    private void onAddNewAnswer(VBox vbox, ArrayList<TextField> textFields) {
        int count = questionAnswersCounts.get(vbox)+1;
        questionAnswersCounts.put(vbox, count);
        TextField answerTextField = new TextField();
        answerTextField.setPromptText("Answer "+count);
        vbox.getChildren().add(answerTextField);
        textFields.add(answerTextField);
    }
}
