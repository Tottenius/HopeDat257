package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.RunMain;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {



    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private void loginClick(){
        System.out.println("Klickat på login");
        System.out.println(userTextField.getText());
        System.out.println(passwordTextField.getText());
    }

    //When user presses register button, the selected name and password is stored in the database.

    @FXML
    private void registerClick(){

        System.out.println("Klickat på register");

        MainViewController.c.register(1,userTextField.getText(), passwordTextField.getText());



    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
