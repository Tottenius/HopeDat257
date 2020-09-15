package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestController {

    @FXML
    private Button testButton;

    @FXML
    private void testButtonClicked(){
        System.out.println("Scroll scroll");
    }

}
