package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import model.UserData;
import sql.ServerConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    protected static ServerConnection c;

    //Database on
    private boolean dbON = true;

    // Default User
    private UserData user = new UserData("Anton");
    //Change away static later
    private static UserData userDATA = new UserData("DEFAULT");

    //Right side
    @FXML
    private ScrollPane rightPane;


    //Left side
    @FXML
    private Button foods;
    @FXML
    private Button friends;
    @FXML
    private Button settings;

    @FXML
    private void foodsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/foodViewRework.fxml"));
        //Set it's controller to the right one
        if(dbON){
            if(userDATA.getLoggedIn()) {
                loader.setControllerFactory(c -> new FoodViewController(this.userDATA));
                this.rightPane.setContent(loader.load());
            }
        }
        else {
            loader.setControllerFactory(c -> new FoodViewController(this.user));
            this.user.setLoggedIn(true);
            this.rightPane.setContent(loader.load());

        }
        // Load it in to the ScrollPane

    }

    @FXML
    private void friendsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/friendsView.fxml"));
        //Set it's controller to the right one
        loader.setControllerFactory( c-> new FriendsViewController(this.userDATA));
        // Load it in to the ScrollPane
        System.out.println("other user name: " + this.user.getUser());
        this.rightPane.setContent(loader.load());
    }

    @FXML
    private void settingsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/settingsView.fxml"));
        //Set it's controller to the right one
        loader.setControllerFactory( c-> new SettingsViewController(this.userDATA));
        // Load it in to the ScrollPane
        this.rightPane.setContent(loader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/loginView.fxml"));
        // om databasen är på så skickar vi med användaren till login för att kunna skapa en ny
        if(this.dbON) {
            loader.setControllerFactory(c -> new LoginViewController());
        }
        try {
            c = new ServerConnection();
            this.rightPane.setContent(loader.load());
        } catch (IOException | SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }


    }

    public static void setUserData ( UserData d){
        userDATA = d;
        d.setLoggedIn(true);

    }

    public UserData getUserData() {
        return user;
    }


}

