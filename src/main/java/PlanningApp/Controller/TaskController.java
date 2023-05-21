package PlanningApp.Controller;

import PlanningApp.Model.SimpleTask;
import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskController {
    //create task and then plan it
    @FXML
    private TextField TaskName;
    @FXML
    private TextField TaskStartTime;


    @FXML
    private TextField TaskDuration;


    @FXML
    private CheckBox CheckBox;

    @FXML
    private MenuButton TaskIsDecomp;

    @FXML
    private MenuButton TaskPriority;

    @FXML
    private MenuButton TaskPeriode;

    @FXML
    private BorderPane hero;

    @FXML
    private void handleCustomSettingsToggle() {
        boolean customSettingsEnabled = CheckBox.isSelected();

        TaskIsDecomp.setVisible(!customSettingsEnabled);
        TaskIsDecomp.setDisable(customSettingsEnabled);

        TaskPriority.setVisible(!customSettingsEnabled);
        TaskPriority.setDisable(customSettingsEnabled);

        TaskPeriode.setVisible(!customSettingsEnabled);
        TaskPeriode.setDisable(customSettingsEnabled);

    }

    @FXML
    private void handleAddTask() throws IOException {
        String name = TaskName.getText();
        String startTime = TaskStartTime.getText();
        String duration = TaskDuration.getText();
        System.out.println(name + " " + startTime + " " + duration);

        try {
            //User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd");
            System.out.println("i'm here 1");

            Task task2 = new SimpleTask(name, duration ,AppController.currentday.getDayname(),startTime,0);
            System.out.println(task2.getName()+" "+task2.getStarttime()+" "+task2.getDuration());
            //Task task2 = AppController.user.createTask("Task1","02:00","04:25");

            //System.out.println("hnaaya");
            //SimpleTask task = new SimpleTask();
            //task.setName("eheh");
            //task.setDuration("01:00");
            //task.setStarttime("01:00");
            //System.out.println("9bel matetkhelet");

            //those also are not working here i d'ont know why :
            AppController.currentuser.planifyman(task2, "2021-02-11Thursday");

            //AppController.user.planifyman(task,"2021-02-11Thursday");





            Stage stage = (Stage) hero.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/DayPage.fxml"));

            Parent root = loader.load();


            // Access the DayController and set the Day object
            DayController dayController = loader.getController();
            dayController.Showday(AppController.currentday.getDayname());


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



        } catch (Exception e) {
            Warning();
        }
    }

    private void Warning(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Time entered");
        alert.setHeaderText(null);
        alert.setContentText("Ensure that time is HH:MM like this : 02:00");
        alert.showAndWait();
    }
}

