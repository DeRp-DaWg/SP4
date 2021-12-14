package com.example.sp4.UI.JavaFX;

import com.example.sp4.Question.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class UIJavaFXRead extends UIJavaFX implements Initializable {

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int counter;
        ObservableList<PieChart.Data> pieChart = null;
        for(Question i : survey.getQuestions()){
            counter = 0;
            Label questions = new Label(i.getQuestionName());
            Font font = new Font("system", 24);
            questions.setFont(font);
            questions.setPadding(new Insets(0, 0, 10, 0));
            vBox.getChildren().add(questions);
            for(Map.Entry<String, Integer> b : i.getAnswers().entrySet()){
                Label answers = new Label(b.getKey());
                Font font1 = new Font("system", 14);
                answers.setFont(font1);
                answers.setPadding(new Insets(0, 0, 5, 0));
                vBox.getChildren().add(answers);
                counter++;
                pieChart = FXCollections.observableArrayList(new PieChart.Data(i.getQuestionName(), counter));
                PieChart chart = new PieChart(pieChart);
                chart.setTitle("Graf");
                vBox.getChildren().add(chart);
            }
        }
    }
}
