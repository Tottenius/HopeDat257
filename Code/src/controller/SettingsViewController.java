package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;

/**
 * This class is for settings. You can currently only change password here.
 */

public class SettingsViewController {

    private final UserData data;

    public SettingsViewController(UserData data){
        this.data = data;
    }

    @FXML
    private TextField oldPassword;

    @FXML
    private TextField newPassword;

    @FXML
    private void changePasswordButtonClick() throws IOException {
        if(oldPassword.getText().isEmpty() || newPassword.getText().isEmpty()) {
            System.out.println("Empty field");
            return;
        } else if(oldPassword.getText().equals(newPassword.getText())) {
            System.out.println("old password is equal to new password");
        }
        boolean passChangeOutcome = DatabaseClient.changePassword(data.getUser(), oldPassword.getText(), newPassword.getText());
        if(passChangeOutcome) {
            System.out.println("success, password has changed");
        } else {
            System.out.println("failure, password could not be changed");
            System.out.println("Current username: " + this.data.getUser());
        }
    }
}