package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.UserData;
import sql.DatabaseClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the friends tab in the program. Currently handles the single function of adding another user as a friend.
 */

public class FriendsViewController {


    private UserData data;

    public FriendsViewController(UserData data) {
        this.data = data;
    }


    @FXML
    private Button addFriendButtonID;

    @FXML
    private TextField addFriendTextID;

    @FXML
    private TableColumn currentFriendsTableColumn;

    @FXML
    private ListView currentFriendsListView;


    @FXML
    private void addFriendButtonClick() throws IOException {
        if (addFriendTextID.getText().isEmpty()) {
            System.out.println("Field is empty");
            return;
        }
        String addFriendUsername = addFriendTextID.getText().toLowerCase();
        boolean passChangeOutcome = DatabaseClient.addFriend(data.getUser(), addFriendUsername);
        if (passChangeOutcome) {
            System.out.println("succes, friend was added");
        } else {
            System.out.println("failure, friend could not be added");
        }
    }


    @FXML
    private void showFriendsButtonClick() throws IOException {


        String[] friends = DatabaseClient.getFriends(data.getUser());

        if(friends.length == 0){

            return;
        }

        List<String> friendsList = new ArrayList<String>(Arrays.asList(friends));
        ListProperty<String> listProperty = new SimpleListProperty<>();

        listProperty.set(FXCollections.observableArrayList(friendsList));
        currentFriendsListView.itemsProperty().bind(listProperty);
    }
}