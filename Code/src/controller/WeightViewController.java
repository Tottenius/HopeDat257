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
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class WeightViewController implements Initializable {

    private int calculatorValue = 0;
    private UserData user;
    private FoodsEnum foodsEnum;
    private Date date;

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public WeightViewController(UserData user, FoodsEnum foodsEnum) {
        this.user = user;
        this.foodsEnum = foodsEnum;
    }

    private Date getDate(int i) {
        try {
            return formatter.parse(formatter.format(new Date(this.date.getTime() + (1000 * 60 * 60 * 24) * i)));
        }
        catch (ParseException e){
            System.out.println("Fel i parse av datum returnar dagens datum");
        }
        return this.date;
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
        this.user.addToUserData(getDate(0), new Foods(value, foodsEnum));

        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public double calculator(int value) {
        return value * foodsEnum.getEmission();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set to today's date;
        try {
            this.date = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with the dateparsing");
        }
    }
}
