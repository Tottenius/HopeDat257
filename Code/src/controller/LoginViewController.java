package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML
    private Label logLabel;

    @FXML
    private Label regLabel;

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
            logLabel.setText("Logged In");
            regLabel.setText("");
            System.out.println("successful login");
        } else {
            System.out.println("faulty credentials");

            regLabel.setText("");
            logLabel.setText("Faulty credentials");
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
            logLabel.setText("");
            regLabel.setText("Registered");
        } else {
            System.out.println("register failed");
            logLabel.setText("");
            regLabel.setText("faulty credentials");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}