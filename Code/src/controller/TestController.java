package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.BarChartExample;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
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
    public void drawGraphMethod() {
        new BarChartExample(barChartOne);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Treeviewer that can be used for longer list of food items etc.. //Anton

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

        barChartOne.setTitle("Carbon emissions from your meal");

        mainroot.getChildren().addAll(root,root2,root3);
        root.getChildren().addAll(nodeA,nodeB,nodeC);
        root2.getChildren().addAll(nodeA2,nodeB2,nodeC2,nodeD2,nodeE2);
        root3.getChildren().addAll(nodeA3,nodeB3,nodeC3);
        treeviewID.setRoot(mainroot);
        treeviewID.setShowRoot(false);
    }
}