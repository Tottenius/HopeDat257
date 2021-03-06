package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/viewer/sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/mainView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Hope");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Method that starts the program as a JavaFx application
        launch(args);
    }
}
