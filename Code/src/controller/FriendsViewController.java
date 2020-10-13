package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.UserData;

/**
 * Text
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
    private void addFriendButtonClick(){

        MainViewController.c.addFriend(this.data.getUser(), addFriendTextID.getText());
        System.out.println(addFriendTextID.getText());
    }

}
