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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.BarChartEmissions;
import model.FoodPackage.Foods;
import model.FoodPackage.FoodsEnum;
import model.UserData;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.scene.input.*;

public class FoodViewController implements Initializable {

    public FoodViewController(UserData user){
        this.user = user;
    }

    // Gives the date compared of today plus i in days
    private Date getDate(int i) {
        try {
            return formatter.parse(formatter.format(new Date(this.date.getTime() + (1000 * 60 * 60 * 24) * i)));
        }
        catch (ParseException e){
            System.out.println("Fel i parse av datum returnar dagens datum");
        }
        return this.date;
    }

    //Current user
    private UserData user;
    // Date
    private Date date;
    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    // Barchart emission class
    private BarChartEmissions barChartEmissions;

    @FXML
    private ListView insertedItemsList;

    @FXML
    private TextField breakfastTextField;

    @FXML
    private void breakfastTextFieldAction() {
        System.out.println(this.breakfastTextField.getText());
    }

    @FXML
    TreeView <FoodsEnum> treeviewID;

    @FXML
    private Button drawGraphs;

    @FXML
    private BarChart barChartOne;

    @FXML
    public void drawGraphMethod() throws ParseException {
        this.updateBarChart();
        // För test
        this.addToList();
        /*
        // Add emissions today
        Date today = this.getDate(0);
        this.user.addToEmissions(45, today);
        // Add emissions tomorrow
        Date tomorrow = this.getDate(1);
        this.user.addToEmissions(27, tomorrow);
        // Paint to chart today
        this.barChartEmissions.addToChart(today, this.user.getEmissions(today));
        // Paint to chart tomorrow
        this.barChartEmissions.addToChart(tomorrow, this.user.getEmissions(tomorrow));

         */
    }
    /*public void drawGraphMethod() {
        new BarChartExample(barChartOne);
    }
    */
    private void updateBarChart(){
        // get the map with values
        Map<Date,List<Foods>> userData = this.user.getUserData();
        // Get the
        Set keys = userData.keySet();
        // Temp date
        Date tempDate;
        Iterator i = keys.iterator();
        // Loop through map
        while (i.hasNext()){
            tempDate = (Date) i.next();

            //List<Foods> todaysList = userData.get(tempDate);
            double todaysEmission = user.getEmissions(tempDate);

            // paint the days that exist
            this.barChartEmissions.addToChart(tempDate, todaysEmission);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init chart and menu");

        // Set to today's date;
        try {
            this.date = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with the dateparsing");
        }
        //Connect our fxml barchart to the class for easier modifications
        this.barChartEmissions = new BarChartEmissions(this.barChartOne);
        // Update barchart with old values if there are any
        this.updateBarChart();

        //Treeviewer that can be used for longer list of food items etc.. //Anton

        /*
        TreeItem<String> mainroot = new TreeItem<>("x");

        TreeItem<String> root = new TreeItem<>("Meats");

        TreeItem<String> nodeA = new TreeItem<>("Beef");

        TreeItem<String> nodeB = new TreeItem<>("Chicken");
        TreeItem<String> nodeC = new TreeItem<>("Lamb");

        TreeItem<String> root2 = new TreeItem<>("Spices");

        TreeItem<String> nodeA2 = new TreeItem<>("Salt");
        TreeItem<String> nodeB2 = new TreeItem<>("Pepper");
        TreeItem<String> nodeC2 = new TreeItem<>("Sugar");
        TreeItem<String> nodeD2 = new TreeItem<>("Oregano");
        TreeItem<String> nodeE2 = new TreeItem<>("Basil");

        TreeItem<String> root3 = new TreeItem<>("Carbs");

        TreeItem<String> nodeA3 = new TreeItem<>("Pasta");
        TreeItem<String> nodeB3 = new TreeItem<>("Rice");
        TreeItem<String> nodeC3 = new TreeItem<>("Mashed Potatoes");


        mainroot.getChildren().addAll(root,root2,root3);
        root.getChildren().addAll(beef,nodeB,nodeC);
        root2.getChildren().addAll(nodeA2,nodeB2,nodeC2,nodeD2,nodeE2);
        root3.getChildren().addAll(nodeA3,nodeB3,nodeC3);
        treeviewID.setRoot(mainroot);
        treeviewID.setShowRoot(false);
        */

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

        //foodView.getChildren().add(treeviewID);
    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            FoodsEnum foodClicked = (FoodsEnum) ((TreeItem)treeviewID.getSelectionModel().getSelectedItem()).getValue();
            System.out.println("Node click: " + foodClicked.getEmission());

            FXMLLoader loader;
            Parent parent = null;
            Scene scene;

            try {
                loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));
                loader.setControllerFactory(c -> new WeightViewController(this.user, foodClicked));
                parent = loader.load();
            } catch (Exception e){
                System.out.println("you fucked up");
            }
            scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


        }
    }

    private void addToList(){
       double emissions = this.user.getEmissions(this.date);
        insertedItemsList.getItems().add(emissions);
    }

    public UserData getUserData(){
        return this.getUserData();
    }

    public void setUserData(UserData data){
       this.user = data;
    }
}