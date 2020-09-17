package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TestController {

    @FXML
    private TextField breakfastTextField;

    @FXML
    private void breakfastTextFieldAction(){
        System.out.println(this.breakfastTextField.getText());
    }

}
