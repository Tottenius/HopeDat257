package model;

import controller.FoodViewController;
import controller.WeightViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomCell extends TreeCell<String> {

    private int value = 12324;
    private UserData user;

    public CustomCell (UserData user) {
        this.user = user;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (isEmpty()) {
            setGraphic(null);
            setText(null);
        } else {
            if (this.getTreeItem().isLeaf()) {
                HBox cellBox = new HBox(10);

                Label label = new Label(item);
                Button button = new Button("+");

                button.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            FXMLLoader loader;
                            Parent parent = null;
                            Scene scene;

                            try {
                                loader = new FXMLLoader(getClass().getResource("/viewer/weightView.fxml"));
                                loader.setControllerFactory(c -> new WeightViewController(this.user));
                                parent = loader.load();
                            } catch (Exception e){
                                
                            }
                            scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();

                        });

                final Pane spacer = new Pane();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                cellBox.getChildren().addAll(label, spacer, button);

                setGraphic(cellBox);
                setText(null);
            } else {
                setGraphic(null);
                setText(item);
            }
        }
    }



}
