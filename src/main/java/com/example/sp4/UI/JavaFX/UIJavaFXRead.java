package com.example.sp4.UI.JavaFX;

import com.example.sp4.Question.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class UIJavaFXRead extends UIJavaFX implements Initializable {

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int value = -1;
        for(Question i : survey.getQuestions()){
            ObservableList<PieChart.Data> pieChart = null;
            ArrayList<PieChart.Data> pieChartAdd = new ArrayList<>();
            Label questions = new Label(i.getQuestionName());
            Font font = new Font("system", 24);
            questions.setFont(font);
            questions.setPadding(new Insets(0, 0, 10, 0));
            vBox.getChildren().add(questions);
            for(Map.Entry<String, Integer> b : i.getAnswers().entrySet()){
                String key = b.getKey().toString();
                value = Integer.parseInt(i.getAnswers().get(b.getKey()).toString());
                System.out.println("boi" + value);

                Label answers = new Label(value + " people answered " + b.getKey());
                Font font1 = new Font("system", 14);
                answers.setFont(font1);
                answers.setPadding(new Insets(0, 0, 5, 0));
                vBox.getChildren().add(answers);

                pieChartAdd.add(new PieChart.Data(b.getKey(), value));
            }
            pieChart = FXCollections.observableArrayList(pieChartAdd);

            PieChart chart = new PieChart(pieChart);
            chart.setTitle("Graph");
            vBox.getChildren().add(chart);
        }


        Button bt = new Button("Back");
        vBox.getChildren().add(bt);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                backButton();
            }
        });
    }

    @FXML
    protected void backButton(){
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
