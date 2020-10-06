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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.util.*;
import javafx.scene.input.*;

public class FoodViewController implements Initializable {

    public FoodViewController(UserData user){
        this.user = user;
    }

    // Gives the date compared of today plus i in days
    /*
    private Date getDate(int i) {
        try {
            return formatter.parse(formatter.format(new Date(this.date.getTime() + (1000 * 60 * 60 * 24) * i)));
        }
        catch (ParseException e){
            System.out.println("Fel i parse av datum returnar dagens datum");
        }
        return this.date;
    }
    */

    //Current user
    private UserData user;
    // Date
    private Date date;
    //Today
    private long today =System.currentTimeMillis();
    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
    private void nextDayButtonClick(){
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
    public void drawGraphMethod() throws ParseException {
        this.updateBarChart();
        // Til llistan längst ner till vänster
        this.addToList();
    }

    private void updateBarChart(){
        // get the map with values
        Map<String, List<Foods>> userData = this.user.getUserData();
        // Get the
        Set keys = userData.keySet();
        // Temp date
        String tempDate;
        Iterator i = keys.iterator();
        // Loop through map
        while (i.hasNext()){
            tempDate = (String) i.next();

            //List<Foods> todaysList = userData.get(tempDate);
            Date convDate = Date.valueOf(tempDate);
            double todaysEmission = user.getEmissions(convDate);

            // paint the days that exist
            this.barChartEmissions.addToChart(convDate, todaysEmission);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.date = new Date(today);
        this.dayOffset = 0;
        //Connect our fxml barchart to the class for easier modifications
        this.barChartEmissions = new BarChartEmissions(this.barChartOne);
        // Update barchart with old values if there are any
        this.updateBarChart();

        this.date.setTime(today + dayOffset * (1000*60*60*24));
        this.dateTextField.setText(""+ this.date);
        //Treeviewer that can be used for longer list of food items etc.. //Anton

        /*
        TreeItem<String> mainroot = new TreeItem<>("x");

        TreeItem<String> root = new TreeItem<>("Meats");

        TreeItem<String> nodeA = new TreeItem<>("Beef");

        //nodeA.setValue("0,0144");
        TreeItem<String> nodeB = new TreeItem<>("Pork");
        TreeItem<String> nodeC = new TreeItem<>("Chicken");
        //nodeB.setValue("0,0025");
        TreeItem<String> nodeD = new TreeItem<>("Lamb");
        TreeItem<String> nodeE = new TreeItem<>("Veal");
        TreeItem<String> nodeF = new TreeItem<>("Venison");
        TreeItem<String> nodeG = new TreeItem<>("Fish");


        TreeItem<String> root2 = new TreeItem<>("Spices");

        TreeItem<String> nodeA2 = new TreeItem<>("Salt");
        TreeItem<String> nodeB2 = new TreeItem<>("Pepper");
        TreeItem<String> nodeC2 = new TreeItem<>("Sugar");
        TreeItem<String> nodeD2 = new TreeItem<>("Oregano");
        TreeItem<String> nodeE2 = new TreeItem<>("Basil");

        TreeItem<String> root3 = new TreeItem<>("Carbs");

        TreeItem<String> nodeA3 = new TreeItem<>("Pasta");
        TreeItem<String> nodeB3 = new TreeItem<>("Rice");
        TreeItem<String> nodeC3 = new TreeItem<>("Potatoes");
        TreeItem<String> nodeD3 = new TreeItem<>("Noodles");

        TreeItem<String> root4 = new TreeItem<>("Dairy");
        TreeItem<String> nodeA4 = new TreeItem<>("Milk");
        TreeItem<String> nodeB4 = new TreeItem<>("Cream");
        TreeItem<String> nodeC4 = new TreeItem<>("Butter");
        TreeItem<String> nodeD4 = new TreeItem<>("Yoghurt");



        barChartOne.setTitle("Carbon emissions from your meal");

        mainroot.getChildren().addAll(root,root2,root3,root4);
        root.getChildren().addAll(nodeA,nodeB,nodeC,nodeD,nodeE,nodeF,nodeG);

        root2.getChildren().addAll(nodeA2,nodeB2,nodeC2,nodeD2,nodeE2);
        root3.getChildren().addAll(nodeA3,nodeB3,nodeC3,nodeD3);
        root4.getChildren().addAll(nodeA4,nodeB4,nodeC4,nodeD4);
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

               FoodsEnum foodClicked = (FoodsEnum) ((TreeItem) treeviewID.getSelectionModel().getSelectedItem()).getValue();
               System.out.println("Node click: " + foodClicked.getEmission());

            FXMLLoader loader;
            Parent parent = null;
            Scene scene;

            try {
                loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));

                loader.setControllerFactory(c -> new WeightViewController(this.user,  foodClicked, this.date));
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