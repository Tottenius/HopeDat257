package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BarChartEmissions;
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * This class handles adding foods to the list and updating the graph.
 */
public class WeightViewController implements Initializable {

    private final ListView insertedItemList;
    private final BarChartEmissions chart;
    private final UserData user;
    private final FoodsEnum foodsEnum;
    private final Date date;

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
        if(user.getUserData().containsKey(date)) {
            emissions = user.getEmissions(date);
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

        if(!input.matches("\\d+")) {
            return;
        }
        int value = Integer.parseInt(input);
        DatabaseClient.addEmission(user.getUser(), date.toString(), foodsEnum.toString(), value);

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

    /**
     * Updates the graph with a new food on a set date
     * @param date The date we add food to
     * @param food The food added
     */
    public void updateGraph(String date, String food){
        // Take the data from the user and add it to the graph
        this.chart.addToChart(date, user.getEmissions(date));
        //Add it to the list in the left corner
        this.addToList(date, food);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
