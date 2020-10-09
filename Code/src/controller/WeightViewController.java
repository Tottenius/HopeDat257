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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

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
    private void addToList(){
        double emissions = 0;
        // If there are any outputs for the day, print them out
        if(this.user.getUserData().containsKey(this.date.toString())) {
            emissions = this.user.getEmissions(this.date);
        }
        // Else use 0 as because nothing exists
        insertedItemList.getItems().add(""+this.date+ ": " + emissions);
    }

    @FXML
    private TextField weightInput;

    @FXML
    public synchronized void submitButton(ActionEvent actionEvent){
        // Get the input
        String input = weightInput.getText();
        // Parse the input
        int value = Integer.parseInt(input);
        System.out.println("när vi klickar på knappen: " + value);
        // Puts the value from the text field in the local variable
        this.user.addToUserData(this.date, new Foods(value, foodsEnum));
        // Update the graph and infobox with a task
        startTask();

        // get node
        Node  source = (Node)  actionEvent.getSource();
        // get stage
        Stage stage  = (Stage) source.getScene().getWindow();
        // close stage
        stage.close();
    }

    private void startTask(){
        // Create a Runnable
        Runnable task = () -> runTask();
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    private void updateGraph(){
        // Take the data from the user and add it to the graph
        this.chart.addToChart(this.date, this.user.getEmissions(this.date));
        //Add it to the list in the left corner
        this.addToList();
    }

    private void runTask(){
        try{
            Platform.runLater(() -> updateGraph());
            Thread.sleep(1000);
        }
        catch(InterruptedException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
