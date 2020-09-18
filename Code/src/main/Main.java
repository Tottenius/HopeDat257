package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//test1234

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/viewer/sample.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewer/sample2.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Hope");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    //Handle events such as button clicks etc...
    public void handle(ActionEvent event){




    }


    public static void main(String[] args) {
        System.out.println("Test");
        //Method that starts the program as a JavaFx application
        launch(args);
    }
}
