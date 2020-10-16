package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;

/**
 * Controller for the friends tab in the program. Currently handles the single function of adding another user as a friend.
 */

public class FriendsViewController {


    private UserData data;

    public FriendsViewController(UserData data){
        this.data = data;
    }


    @FXML
    private Button addFriendButtonID;

    @FXML
    private TextField addFriendTextID;

    @FXML
    private void addFriendButtonClick() throws IOException {
        if(addFriendTextID.getText().isEmpty()) {
            System.out.println("Field is empty");
            return;
        }
        boolean passChangeOutcome = DatabaseClient.addFriend(data.getUser(), addFriendTextID.getText());
        if(passChangeOutcome) {
            System.out.println("succes, friend was added");
        } else {
            System.out.println("failure, friend could not be added");
        }
    }
}