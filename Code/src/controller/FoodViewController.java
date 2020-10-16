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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Text
 */

public class FoodViewController implements Initializable {

    public FoodViewController(UserData user){
        this.user = user;
    }

    //Current user
    private UserData user;
    // Date
    private Date date;
    //Today
    private long today = System.currentTimeMillis();
    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // Barchart emission class
    private BarChartEmissions barChartEmissions;

    @FXML
    private ListView insertedItemsList;

    @FXML
    private TextField breakfastTextField;

    @FXML
    private Label dateTextField;
    @FXML
    private Button nextDayButton;
    @FXML
    private Button previousDayButton;

    private int dayOffset = 0;

    @FXML
    private void nextDayButtonClick() {
        dayOffset++;
        this.date.setTime(today + dayOffset * (1000*60*60*24));
        this.dateTextField.setText(""+ this.date);
    }

    @FXML
    private void previousDayButtonClick(){
        dayOffset--;
        this.date.setTime(today + dayOffset * (1000*60*60*24));
        this.dateTextField.setText(""+ this.date);
    }

    @FXML
    private void breakfastTextFieldAction() {
        System.out.println(this.breakfastTextField.getText());
    }

    @FXML
    private TreeView <FoodsEnum> treeviewID;

    @FXML
    private Button drawGraphs;

    @FXML
    private BarChart barChartOne;

    @FXML
    private Tab breakfastTab;

    @FXML
    private Tab lunchTab;

    @FXML
    private Tab dinnerTab;


    @FXML
  /*  public void drawGraphMethod() {
        // Till listan längst ner till vänster
        this.addToList();
        // Update the graph
        this.updateBarChart();
    }*/

    private synchronized void updateBarChart(){
        //Reset the barchart values
        barChartEmissions.clearEmissionCollection();
        // get the map with values
        Map<String, List<Foods>> userData = user.getUserData();
        // Get the dates
        Set keys = userData.keySet();
        // Temp date
        String stringDate;
        Iterator i = keys.iterator();
        // Loop through map
        while (i.hasNext()){
            stringDate = (String) i.next();

            //List<Foods> todaysList = userData.get(tempDate);
            Date convDate = Date.valueOf(stringDate);
            double todaysEmission = user.getEmissions(convDate.toString());

            // paint the days that exist
            barChartEmissions.addToChart(convDate.toString(), todaysEmission);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        date = new Date(today);
        dayOffset = 0;
        //Connect our fxml barchart to the class for easier modifications
        barChartEmissions = new BarChartEmissions(barChartOne);
        // Update barchart with old values if there are any
        updateBarChart();

        date.setTime(today + dayOffset * (1000*60*60*24));
        dateTextField.setText(""+ date);

        EventHandler<MouseEvent> mouseEventHandle = this::handleMouseClicked;

        treeviewID.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

        TreeItem<FoodsEnum> mainRoot = new TreeItem<>();

        for (FoodsEnum foods : FoodsEnum.values()) {
            TreeItem<FoodsEnum> food = new TreeItem<>(foods);
            mainRoot.getChildren().add(food);
        }

        treeviewID.setRoot(mainRoot);
        treeviewID.setShowRoot(false);

        barChartOne.setTitle("Carbon emissions from your meal");

        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //foodView.getChildren().add(treeviewID);
    }


    public void initData() throws IOException {
        String[] emission = DatabaseClient.getEmission(user.getUser());
        if(emission[0].isEmpty()) {
            return;
        }
        for(int i = 0; i < emission.length; i += 3) {
            FoodsEnum foodsEnum = FoodsEnum.valueOf(emission[i]);
            user.addToUserData(emission[i + 2], new Foods(Integer.parseInt(emission[i + 1]), foodsEnum));
            new WeightViewController(user, foodsEnum, date, barChartEmissions, insertedItemsList).updateGraph(emission[i+2], emission[i]);
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

        /*
        // When stage is "closed"
        stage.setOnHidden(windowEvent -> {
            Platform.runLater(()->{
                //Add the bargraph
                this.updateBarChart();
                //this.barChartEmissions.addToChart(this.date, this.user.getEmissions(this.date));
                //Add it to the list in the left corner
                this.addToList();
            });
        });
        */

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

    // Add to the list in the bottom right corner.
  /*  private void addToList(String date){
        double emissions = 0;
        // If there are any outputs for the day, print them out
        if(this.user.getUserData().containsKey(date)) {
            emissions = this.user.getEmissions(date);
        }
        // Else use 0 as because nothing exists
        insertedItemsList.getItems().add("" + date + ": " + emissions);

    }*/
}