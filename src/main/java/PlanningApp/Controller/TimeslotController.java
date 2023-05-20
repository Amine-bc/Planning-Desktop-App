package PlanningApp.Controller;

import PlanningApp.Model.Day;
import PlanningApp.Model.TimeSlot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

public class TimeslotController {
    @FXML
    private BorderPane Home;

    @FXML
    private ChoiceBox<String> startingTimeChoiceBox;

    @FXML
    private ChoiceBox<String> endingTimeChoiceBox;

    @FXML
    private Button createButton;



    @FXML
    public void AddTimeslot() throws IOException {
            String startTime = startingTimeChoiceBox.getValue();
            String endTime = endingTimeChoiceBox.getValue();

            if (startTime != null && endTime != null) {
                // Create the timeslot using the selected start and end times
                TimeSlot timeSlot = new TimeSlot(startTime, endTime);

                AppController.day.addtimeslot(timeSlot);

                // Optionally, you can perform additional actions after creating the timeslot

                // Reset the choice boxes to their initial state
                startingTimeChoiceBox.getSelectionModel().clearSelection();
                endingTimeChoiceBox.getSelectionModel().clearSelection();
            }
        AppController.day.printTimeslots();

        Stage stage = (Stage) Home.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/DayPage.fxml"));

        Parent root = loader.load();


        // Access the DayController and set the Day object
        DayController dayController = loader.getController();
        dayController.Showday(AppController.day.getDayname());


        // Set the new FXML file as the scene
        Scene scene = new Scene(root);

        // Get the primary stage
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(400);
        primaryStage.show();


    }




}

