package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

/**
 * This class communicates with the server for login.
 */

public class LoginViewController implements Initializable {

    public LoginViewController(){
    }

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passwordTextField;

    // Anropar contactServer för att fråga servern om login uppgifterna stämmer och loggar isf in
    @FXML
    private void loginClick() throws IOException {
        if(userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            System.out.println("Empty field");
            return;
        }
        String username = userTextField.getText().toLowerCase();
        boolean loginOutcome = DatabaseClient.login(username, passwordTextField.getText());
        if(loginOutcome) {
            MainViewController.setUserData(new UserData(username));
            System.out.println("successful login");
        } else {
            System.out.println("faulty credentials");
            this.passwordTextField.setText("");
        }
    }

    @FXML
    private void registerClick() throws IOException {
        if(userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            System.out.println("Empty field");
            return;
        } else if(userTextField.getText().contains(" ") || passwordTextField.getText().contains(" ")) {
            System.out.println("Contains whitespaces");
            return;
        }
        String username = userTextField.getText().toLowerCase();
        boolean registerOutcome = DatabaseClient.register(username, passwordTextField.getText());
        if(registerOutcome) {
            System.out.println("registered");
        } else {
            System.out.println("register failed");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}