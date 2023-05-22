package PlanningApp.Controller;

import PlanningApp.Model.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoTaskController implements Initializable {

    @FXML
    private BorderPane hero;

    @FXML
    private TextField TaskName;

    @FXML
    private TextField TaskStartTime;

    @FXML
    private TextField TaskDuration;

    @FXML
    private MenuButton TaskCategory;

    @FXML
    private MenuButton TaskPriority;

    @FXML
    private MenuButton TaskPeriode;

    @FXML
    private MenuButton TaskIsDecomp;

    @FXML
    private ColorPicker TaskColor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set initial values
        TaskName.setText(AppController.currenttask.getName());
        TaskStartTime.setText(AppController.currenttask.getStarttime());
        TaskDuration.setText(AppController.currenttask.getDuration());
        TaskColor.setValue(Color.web("#0598FF"));
        // ... set initial values for other fields

        // Disable fields
        TaskName.setDisable(true);
        TaskStartTime.setDisable(true);
        TaskDuration.setDisable(true);
        TaskColor.setDisable(true);
        TaskCategory.setDisable(true);
        TaskPeriode.setDisable(true);
        TaskIsDecomp.setDisable(true);
        TaskPriority.setDisable(true);
        // ... disable other fields
    }

    @FXML
    public void handleModifyButtonClick() {
        TaskName.setDisable(false);
        TaskStartTime.setDisable(false);
        TaskDuration.setDisable(false);

    }
    @FXML
    public void handleDone() throws IOException {
        AppController.currenttask.setName(TaskName.getText());
        AppController.currenttask.setStarttime(TaskStartTime.getText());
        AppController.currenttask.setDuration(TaskDuration.getText());


        App.SaveToDb("src/main/java/PlanningApp/Files/users.ser");

        Stage stage1 = (Stage) hero.getScene().getWindow();
        stage1.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/DayPage.fxml"));

        Parent root = loader.load();


        // Access the DayController and set the Day object
        DayController dayController = loader.getController();

        dayController.Showday( AppController.currentday.getDayname());


        // Set the new FXML file as the scene
        Scene scene = new Scene(root);

        // Get the primary stage
        Stage primaryStage = (Stage) hero.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(400);
        primaryStage.show();

    }
}
