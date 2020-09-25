package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import main.BarChartExample;
import model.BarChartEmissions;
import model.UserData;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private TextField breakfastTextField;

    @FXML
    private void breakfastTextFieldAction() {
        System.out.println(this.breakfastTextField.getText());
    }

    @FXML
    TreeView <String> treeviewID;

    @FXML
    private Button drawGraphs;

    @FXML
    private BarChart barChartOne;

    @FXML
    public void drawGraphMethod() throws ParseException {
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
    }
    /*public void drawGraphMethod() {
        new BarChartExample(barChartOne);
    }
    */
    private void updateBarChart(){
        // get the map with values
        Map hm = this.user.getEmissionsMap();
        // Get the
        Set keys = hm.keySet();
        // Temp date
        Date tempDate;
        Iterator i = keys.iterator();
        // Loop through map
        while (i.hasNext()){
            tempDate = (Date) i.next();
            // paint the days that exist
            this.barChartEmissions.addToChart(tempDate, this.user.getEmissions(tempDate));
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

        TreeItem<String> mainroot = new TreeItem<>("x");

        TreeItem<String> root = new TreeItem<>("Meats");

        TreeItem<String> nodeA = new TreeItem<>("Beef");
        nodeA.setValue("0,0144");
        TreeItem<String> nodeB = new TreeItem<>("Chicken");
        nodeB.setValue("0,0025");
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



        barChartOne.setTitle("Carbon emissions from your meal");

        mainroot.getChildren().addAll(root,root2,root3);
        root.getChildren().addAll(nodeA,nodeB,nodeC);
        root2.getChildren().addAll(nodeA2,nodeB2,nodeC2,nodeD2,nodeE2);
        root3.getChildren().addAll(nodeA3,nodeB3,nodeC3);
        treeviewID.setRoot(mainroot);
        treeviewID.setShowRoot(false);

        treeviewID.setCellFactory(e -> new CustomCell());

        //foodView.getChildren().add(treeviewID);
    }
}

class CustomCell extends TreeCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (isEmpty()) {
            setGraphic(null);
            setText(null);
        } else {
            if (this.getTreeItem().isLeaf()) {
                HBox cellBox = new HBox(10);

                Label label = new Label(item);
                Button button = new Button("+");

                cellBox.getChildren().addAll(label, button);

                setGraphic(cellBox);
                setText(null);
            } else {
                setGraphic(null);
                setText(item);
            }
        }
    }



}