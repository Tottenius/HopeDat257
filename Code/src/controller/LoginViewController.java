package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.RunMain;
import model.UserData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.*;
import java.util.ResourceBundle;

/**
 * This class communicates with the server for login.
 */

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

    // Anropar contactServer för att fråga servern om login uppgifterna stämmer och loggar isf in
    /*
    @FXML
    private void loginClick() throws IOException {
        if(userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            System.out.println("Empty field");
            return;
        }
        String message = "login " + userTextField.getText() + " " + passwordTextField.getText();
        String response = contactServer(message);
        if(response.equals("true")) {
            // Gör vad som ska göras om en login lyckades
            MainViewController.setUserData(new UserData(this.userTextField.getText()));
            System.out.println("successful login");
        } else if(response.equals("false")) {
            // Gör vad som ska göras om en login misslyckades
            System.out.println("faulty credentials");
            this.passwordTextField.setText("");
        }
    }

    @FXML
    private void registerClick() throws IOException {
        if(userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            System.out.println("Empty field");
            return;
        }
        String message = "register " + userTextField.getText() + " " + passwordTextField.getText();
        String response = contactServer(message);
        if(response.equals("success")) {
            System.out.println("registered");
        } else if(response.equals("fail")) {
            System.out.println("register failed");
        }
    }



    // Skickar ett meddelande (message) till servern och returnerar ett svar från servern
    private String contactServer(String message) throws IOException {
        Socket socket = new Socket("217.209.131.86", 9999);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeUTF(message);
        String response = input.readUTF();
        socket.close();
        return response;
    }


*/


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
