package com.example.sp4.UI.JavaFX;

import com.example.sp4.Survey;
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
        for (Survey i : surveys) {
            Button button = new Button(i.getSurveyTitle());
            Label test = new Label(i.getSurveyTitle());
            vBox.getChildren().add(test);
        }
    }
}
