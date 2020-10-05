package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.UserData;

public class SettingsViewController {

    private UserData data;

    public SettingsViewController(UserData data){
        this.data = data;
    }

    @FXML
    private Button changePasswordButton;

    @FXML
    private TextField oldPassword;

    @FXML
    private TextField newPassword;

    @FXML
    private void changePasswordButtonClick(){
        MainViewController.c.changePassword(this.data.getUser(), this.oldPassword.getText(), this.newPassword.getText() );
        System.out.println("Current username: " + this.data.getUser());
    }

}
