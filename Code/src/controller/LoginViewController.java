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
            logLabel.setText("Empty feild");
            regLabel.setText("");
            return;
        } else if(userTextField.getText().contains(" ") || passwordTextField.getText().contains(" ")) {
            logLabel.setText("Has whitespace");
            regLabel.setText("");
            return;
        }
        String username = userTextField.getText().toLowerCase();
        boolean loginOutcome = DatabaseClient.login(username, passwordTextField.getText());
        if(loginOutcome) {
            MainViewController.setUserData(new UserData(username));
            logLabel.setText("Logged In");
            regLabel.setText("");
        } else {

            regLabel.setText("");
            logLabel.setText("Faulty credentials");
            this.passwordTextField.setText("");
        }
    }

    @FXML
    private void registerClick() throws IOException {
        if(userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            regLabel.setText("Empty feild");
            logLabel.setText("");
            return;
        } else if(userTextField.getText().contains(" ") || passwordTextField.getText().contains(" ")) {
            regLabel.setText("Has whitespace");
            logLabel.setText("");
            return;
        }
        String username = userTextField.getText().toLowerCase();
        boolean registerOutcome = DatabaseClient.register(username, passwordTextField.getText());
        if(registerOutcome) {
            logLabel.setText("");
            regLabel.setText("Registered");
        } else {
            logLabel.setText("");
            regLabel.setText("faulty credentials");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}