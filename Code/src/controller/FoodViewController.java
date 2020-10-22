package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.BarChartEmissions;
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.*;

/**
 * This class is a controller for the food view.
 */

public class FoodViewController implements Initializable {

    public FoodViewController(UserData user){
        this.user = user;
    }

    //Current user
    private final UserData user;
    // Date
    private Date date;
    //Today
    private final long today = System.currentTimeMillis();
    // Barchart emission class
    private BarChartEmissions barChartEmissions;

    @FXML
    private ListView insertedItemsList;

    @FXML
    private TextField breakfastTextField;

    @FXML
    private Label dateTextField;

    private int dayOffset = 0;

    @FXML
    private void nextDayButtonClick() {
        dayOffset++;
        this.date.setTime(today + dayOffset * (1000*60*60*24));

        //Update the textfield of the date
        this.dateTextField.setText(""+ this.date);

        // Set this date to the barchart
        this.barChartEmissions.setDate(this.date);
        //Update the barchart
        this.barChartEmissions.updateBarChart( this.user, this.date);

    }

    @FXML
    private void previousDayButtonClick(){
        dayOffset--;
        this.date.setTime(today + dayOffset * (1000*60*60*24));

        //Update the textfield of the date
        this.dateTextField.setText(""+ this.date);

        // Set this date to the barchart
        this.barChartEmissions.setDate(this.date);
        //Update the barchart
        this.barChartEmissions.updateBarChart( this.user, this.date);
    }

    @FXML
    private void breakfastTextFieldAction() {
        System.out.println(this.breakfastTextField.getText());
    }

    @FXML
    private TreeView <FoodsEnum> treeviewID;

    @FXML
    private BarChart barChartOne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set date to today
        date = new Date(today);
        // Set the day offset from today to 0
        dayOffset = 0;
        //Connect our fxml barchart to the class for easier modifications
        barChartEmissions = new BarChartEmissions(barChartOne, date);
        // Update barchart with old values if there are any
        this.barChartEmissions.updateBarChart(this.user, this.date);
        //this.updateBarChart();
        // Set the text to graph
        barChartOne.setTitle("Carbon emissions from your meal");

        // Set the date textfield to today's date
        dateTextField.setText(""+ date);

        // Set up the mouseEvent to make the TreeView clickable
        EventHandler<MouseEvent> mouseEventHandle = this::handleMouseClicked;
        // Connect it to the TreeView
        treeviewID.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

        // Initialize the TreeView
        TreeItem<FoodsEnum> mainRoot = new TreeItem<>();
        // Add all different foods to the TreeView
        for (FoodsEnum foods : FoodsEnum.values()) {
            TreeItem<FoodsEnum> food = new TreeItem<>(foods);
            mainRoot.getChildren().add(food);
        }
        // Connect it to the root
        treeviewID.setRoot(mainRoot);
        // Make the root not visible
        treeviewID.setShowRoot(false);

        // Try to get the user's from the data from the server
        try {
            this.initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() throws IOException {
        String[] emission = DatabaseClient.getEmission(user.getUser());
        if(emission[0].isEmpty() || emission[0].equals("fail")) {
            return;
        }
        for(int i = 0; i < emission.length; i += 3) {
            FoodsEnum foodsEnum = FoodsEnum.valueOf(emission[i]);
            user.addToUserData(emission[i + 2], new Foods(Integer.parseInt(emission[i + 1]), foodsEnum));
            new WeightViewController(user, foodsEnum, date, barChartEmissions, insertedItemsList).updateGraph(emission[i + 2], emission[i]);
        }
    }

    private void loadInWeightView(){
        // cast the enum
        FoodsEnum foodClicked = (FoodsEnum) ((TreeItem) treeviewID.getSelectionModel().getSelectedItem()).getValue();
        System.out.println("Node click: " + foodClicked.getEmission());

        FXMLLoader loader;
        Parent parent = null;
        Scene scene;

        // Try to load in the view and the controller
        try {
            loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));

            loader.setControllerFactory(c -> new WeightViewController(this.user, foodClicked, this.date, this.barChartEmissions, this.insertedItemsList));
            parent = loader.load();
        } catch (Exception e){
            System.out.println("you fucked up");
        }
        // Create scene
        scene = new Scene(parent);
        // Create stage
        Stage stage = new Stage();
        // Put the scene in the stage
        stage.setScene(scene);
        // Make it modal
        stage.initModality(Modality.APPLICATION_MODAL);
        // Show it
        stage.show();

    }

    //Listener for the add food menu
    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            //load in the view
            loadInWeightView();
        }
    }
}