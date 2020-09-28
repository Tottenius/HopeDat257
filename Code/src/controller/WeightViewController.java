package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.UserData;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class WeightViewController extends Application {

    private int calculatorValue = 0;
    private UserData user;

    public WeightViewController(UserData user) {
        this.user = user;
    }

    @FXML
    TextField weightInput;

    @FXML
    public void submitButton(ActionEvent actionEvent) throws ParseException, IOException {
        // Get the input
        String input = weightInput.getText();
        // Parse the input
        int value = Integer.parseInt(input);
        System.out.println("när vi klickar på knappen: " + value);
        // Puts the value from the text field in the local variable
        this.calculator(value);
        this.user.addToEmissions(this.calculatorValue);

        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        if(value != 0) {
         stage.close();
        }
    }

    @Override
    public void start(Stage weightStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));
        Parent weightRoot = loader.load();

        weightStage.setScene(new Scene(weightRoot));
        //weightStage.initOwner(weightStage);
        weightStage.initModality(Modality.APPLICATION_MODAL);
        weightStage.showAndWait();
    }

    public void calculator(int value) {
        System.out.println("i weightviewcontroller "+value);
        this.calculatorValue = value;
    }

    public int getCalculatorValue() {
        return calculatorValue;
    }
}
