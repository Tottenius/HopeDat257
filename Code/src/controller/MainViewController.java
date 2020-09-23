package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class MainViewController {

    //Right side
    @FXML
    private ScrollPane rightPane;

    //Left side
    @FXML
    private Button foods;
    @FXML
    private Button friends;
    @FXML
    private Button settings;

    @FXML
    private void foodsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/foodView.fxml"));
        //Set it's controller to the right one
        loader.setControllerFactory( c->{
            return new FoodViewController();
        });
        // Load it in to the ScrollPane
        this.rightPane.setContent(loader.load());

    }

}
