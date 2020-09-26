package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.RunMain;
import model.UserData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    public LoginViewController(){
    }

    private UserData data;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private void loginClick() throws IOException {
        System.out.println("Klickat på login");
        System.out.println(userTextField.getText());
        System.out.println(passwordTextField.getText());

        // Om lösenordet är rätt så loggar vi in
        if(MainViewController.c.login(this.userTextField.getText(), this.passwordTextField.getText())) {
            MainViewController.setUserData(new UserData(this.userTextField.getText()));
        }
        // Felmedelande
        else{
            this.passwordTextField.setText("");
        }
    }

    //When user presses register button, the selected name and password is stored in the database.

    @FXML
    private void registerClick(){
        System.out.println("Klickat på register");
        MainViewController.c.register(1,userTextField.getText(), passwordTextField.getText());
    }

    private MainViewController loadRootController()  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/mainView.fxml"));
        MainViewController controller = loader.getController();
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}