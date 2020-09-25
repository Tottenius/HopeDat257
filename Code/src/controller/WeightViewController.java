package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WeightViewController extends Application {

    @FXML
    TextField weightInput;

    @FXML
    public void submitButton() {
        String input = weightInput.getText();
        int value = Integer.parseInt(input);
        calculator(value);
    }

    @Override
    public void start(Stage weightStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));
        Parent weightRoot = loader.load();

        weightStage.setScene(new Scene(weightRoot));
        weightStage.show();
    }

    public void calculator(int value) {
        System.out.println(value);
    }
}
