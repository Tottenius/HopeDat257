package model;

import controller.WeightViewController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class CustomCell extends TreeCell<String> {

    public CustomCell () {
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
                            WeightViewController weightViewController = new WeightViewController();
                            try {
                                weightViewController.start(new Stage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
