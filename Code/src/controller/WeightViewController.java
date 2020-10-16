package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.BarChartEmissions;
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;
import sql.DatabaseClient;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Text
 */
public class WeightViewController implements Initializable {

    private ListView insertedItemList;
    private BarChartEmissions chart;
    private UserData user;
    private FoodsEnum foodsEnum;
    private Date date;

    public WeightViewController(UserData user, FoodsEnum foodsEnum, Date date, BarChartEmissions chart, ListView insertedItemList) {
        this.user = user;
        this.foodsEnum = foodsEnum;
        this.date = date;
        this.chart = chart;
        this.insertedItemList = insertedItemList;
    }

    // Add to the list in the bottom right corner.
    private void addToList(String date, String food){
        double emissions = 0;
        // If there are any outputs for the day, print them out
        if(this.user.getUserData().containsKey(date)) {
            emissions = this.user.getEmissions(date);
        }
        // Else use 0 as because nothing exists
        insertedItemList.getItems().add(date+  ": " + food + ":  " + new DecimalFormat("#.###").format(emissions));
    }

    @FXML
    private TextField weightInput;

    @FXML
    public synchronized void submitButton(ActionEvent actionEvent) throws IOException {
        // Get the input
        String input = weightInput.getText();
        // Parse the input
        int value = Integer.parseInt(input);

        if(DatabaseClient.addEmission(user.getUser(), date.toString(), foodsEnum.toString(), value)) {
            System.out.println("food was added");
        }

   //     System.out.println("när vi klickar på knappen: " + value);
        // Puts the value from the text field in the local variable
        this.user.addToUserData(date.toString(), new Foods(value, foodsEnum));
        // Update the graph and infobox with a task
        this.updateGraph(date.toString(), foodsEnum.toString());
        //startTask();

        // get node
        Node  source = (Node)  actionEvent.getSource();
        // get stage
        Stage stage  = (Stage) source.getScene().getWindow();
        // close stage
        stage.close();
    }

    public void updateGraph(String date, String food){
        // Take the data from the user and add it to the graph
        this.chart.addToChart(date, user.getEmissions(date.toString()));
        //Add it to the list in the left corner
        this.addToList(date, food);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
