package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> root = new TreeItem<>("Root");

        TreeItem<String> nodeA = new TreeItem<>("Node A");
        TreeItem<String> nodeB = new TreeItem<>("Node B");
        TreeItem<String> nodeC = new TreeItem<>("Node C");

        root.getChildren().addAll(nodeA,nodeB,nodeC);
        treeviewID.setRoot(root);

    }
}