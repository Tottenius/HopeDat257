package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import model.UserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class used for the main user window
 */
public class MainViewController implements Initializable {


    //Change away static later
    private static UserData userDATA = new UserData("DEFAULT");

    //Right side
    @FXML
    private ScrollPane rightPane;


    @FXML
    private void foodsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/foodViewRework.fxml"));
        //Set it's controller to the right one
            if(userDATA.getLoggedIn()) {
                loader.setControllerFactory(c -> new FoodViewController(userDATA));
                this.rightPane.setContent(loader.load());
            }
    }

    @FXML
    private void friendsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/friendsView.fxml"));
        //Set it's controller to the right one
        if(userDATA.getLoggedIn()) {
            loader.setControllerFactory(c -> new FriendsViewController(userDATA));
            // Load it in to the ScrollPane
            rightPane.setContent(loader.load());
        }
    }

    @FXML
    private void settingsClick() throws IOException {
        //Load in the new fxml document
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/settingsView.fxml"));
        //Set it's controller to the right one
        if(userDATA.getLoggedIn()) {
            loader.setControllerFactory(c -> new SettingsViewController(userDATA));
            // Load it in to the ScrollPane
            rightPane.setContent(loader.load());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/loginView.fxml"));
        // om databasen är på så skickar vi med användaren till login för att kunna skapa en ny
        loader.setControllerFactory(c -> new LoginViewController());
        try {
            rightPane.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the user data and the user as logged in
     * @param d The user that gets logged in and gets its data loaded
     */
    public static void setUserData ( UserData d){
        userDATA = d;
        d.setLoggedIn(true);
    }
}