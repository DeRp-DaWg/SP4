package com.example.sp4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Label hell;
    private ArrayList<Label> listOfLabels = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}