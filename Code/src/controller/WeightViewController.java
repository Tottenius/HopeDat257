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

    Stage weightStage;

    @FXML
    TextField weightInput;

    @FXML
    public int submitButton() {
        String input = weightInput.getText();
        return Integer.parseInt(input);

    }

    @Override
    public void start(Stage weightStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));
        Parent weightRoot = loader.load();
        weightStage.setScene(new Scene(weightRoot));



        weightStage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
