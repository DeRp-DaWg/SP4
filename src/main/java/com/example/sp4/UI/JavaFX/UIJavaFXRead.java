package com.example.sp4.UI.JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UIJavaFXRead extends UIJavaFX implements Initializable {

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < surveys.size(); i++) {
            Button button = new Button(surveys.get(i).getSurveyTitle());
            Label test = new Label("Hello, world");
            vBox.getChildren().add(test);
        }
    }
}
