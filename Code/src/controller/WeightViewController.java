package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class WeightViewController implements Initializable {

    private int calculatorValue = 0;
    private UserData user;
    private FoodsEnum foodsEnum;
    private Date date;
    private long today =System.currentTimeMillis();

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public WeightViewController(UserData user, FoodsEnum foodsEnum, Date date) {
        this.user = user;
        this.foodsEnum = foodsEnum;
        this.date = date;
    }

    private Date getDate(int i) {
        date = new Date(today + i * (1000*60*60*24));
        return this.date;
    }

    @FXML
    TextField weightInput;

    @FXML
    public void submitButton(ActionEvent actionEvent){
        // Get the input
        String input = weightInput.getText();
        // Parse the input
        int value = Integer.parseInt(input);
        System.out.println("när vi klickar på knappen: " + value);
        // Puts the value from the text field in the local variable
        this.user.addToUserData(this.date, new Foods(value, foodsEnum));

        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public double calculator(int value) {
        return value * foodsEnum.getEmission();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
