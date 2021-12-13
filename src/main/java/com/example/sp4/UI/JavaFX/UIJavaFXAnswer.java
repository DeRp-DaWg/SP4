package com.example.sp4.UI.JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UIJavaFXAnswer extends UIJavaFX implements Initializable {

    @FXML
    private VBox questionBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surveys.get(1);
    }
    public void loadQuestions(){
        for(int i = 0; i < surveys.get(8).getQuestions().size();i++){

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
}
