package PlanningApp.Controller;

import PlanningApp.Model.User;
import PlanningApp.Model.Day;
import PlanningApp.Model.Task;
import PlanningApp.Model.TimeSlot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class DayController {
    @FXML
    private VBox timeslotPane;

    @FXML
    private VBox taskPane;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Label titleLabel;



    public void Showday( String dayname) {

        titleLabel.setText(dayname);
        populateData();
    }


    private void populateData() {
        taskPane.getChildren().clear();
        timeslotPane.getChildren().clear();


        for (TimeSlot timeslot : AppController.currentday.getTimeslot()) {
            HBox timeslotBox = new HBox();
            Label startLabel = new Label("      "+timeslot.getstart());
            Label endLabel = new Label("  --   "+timeslot.getend()+"                   ");

            timeslotBox.getChildren().addAll(startLabel, endLabel);
            timeslotPane.getChildren().add(createStyledBox(timeslotBox));

            // Add remove button
            Button removeButton = new Button("");
            removeButton.setStyle("-fx-background-color: #0598FF; -fx-background-radius: 5; -fx-padding: 0;");
            removeButton.setPrefSize(10, 10);

            //button style
            ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/Assets/trash.png")));
            removeIcon.setFitWidth(10);
            removeIcon.setFitHeight(10);
            removeButton.setGraphic(removeIcon);
            removeButton.setCursor(Cursor.HAND);
            removeButton.setOnAction(e -> {
                // Remove the task from the task pane
                timeslotPane.getChildren().remove(timeslotBox);
                // Remove the task from the day's tasks
                AppController.currentday.getTimeslot().remove(timeslot);
                // update viw
                populateData();
            });
            timeslotBox.getChildren().addAll(removeButton);
        }


        for (Task task : AppController.currentday.getTasks()) {
            HBox taskBox = new HBox();
            Label nameLabel = new Label(task.getName());
            Label startLabel = new Label(task.getStarttime());
            Label endLabel = new Label("-->" + task.getEndtime());

            taskBox.getChildren().addAll(nameLabel, startLabel, endLabel);



            VBox styledBox = createStyledBox(taskBox);
            taskPane.getChildren().add(styledBox);

            // Add color picker functionality
            ColorPicker colorPicker = new ColorPicker(Color.web("#0598FF"));
            colorPicker.setPrefWidth(40);
            colorPicker.setPrefHeight(12);

            Button removeButton = new Button("");
            removeButton.setStyle("-fx-background-color:#0598FF ;-fx-padding: 0 0 0 20;");
            colorPicker.setOnAction(e -> {
                Color selectedColor = colorPicker.getValue();
                String colorCode = String.format("#%02x%02x%02x",
                        (int) (selectedColor.getRed() * 255),
                        (int) (selectedColor.getGreen() * 255),
                        (int) (selectedColor.getBlue() * 255));
                styledBox.setStyle("-fx-background-color: " + colorCode + "; -fx-padding: 5px; -fx-background-radius: 15");

                removeButton.setStyle("-fx-background-color:"+ colorCode +";-fx-padding: 0 0 0 20;");
            });

            taskBox.getChildren().add(colorPicker);
            // Add remove button

            removeButton.setPrefSize(15, 15);
            ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/Assets/trash.png")));
            removeIcon.setFitWidth(15);
            removeIcon.setFitHeight(15);
            removeButton.setGraphic(removeIcon);
            removeButton.setCursor(Cursor.HAND);
            removeButton.setOnAction(e -> {
                // Remove the task from the task pane
                taskPane.getChildren().remove(taskBox);
                // Remove the task from the day's tasks
                AppController.currentday.getTasks().remove(task);
                // update viw
                populateData();
            });

            taskBox.getChildren().addAll(removeButton);
        }

    }

    private VBox createStyledBox(HBox content) {
        VBox box = new VBox(content);
        box.setStyle("-fx-background-color: #0598FF; -fx-padding: 5px; -fx-background-radius: 15");
        box.setMaxWidth(Double.MAX_VALUE);
        box.setMaxHeight(Double.MAX_VALUE);

        content.setFillHeight(true);
        content.setSpacing(5);

        content.getChildren().forEach(node -> {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 45px;");
            }
        });

        return box;
    }

    public void AddTimeslot() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/CreateTimeslot.fxml"));
        Parent roo = loader.load();
          //hadi teftaha fi nafss el window:
        //Stage stage = new Stage();
        Stage stage=(Stage) titleLabel.getScene().getWindow();
        TimeslotController timeslotController = loader.getController();
        stage.setTitle("CreateTimeslot");
        stage.setScene(new Scene(roo));
        stage.show();
    }

    public void AddTask() throws IOException {
        // close the previous Page:
        Stage stage1 = (Stage) titleLabel.getScene().getWindow();
        stage1.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/CreateTask.fxml"));
        Parent roo = loader.load();
        Stage stage = new Stage();
        TaskController tasController = loader.getController();
        stage.setTitle("CreateTimeslot");
        stage.setScene(new Scene(roo));
        stage.show();
    }
}
