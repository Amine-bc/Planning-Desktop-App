package PlanningApp.Controller;

import PlanningApp.Model.App;
import PlanningApp.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PeriodeController {

    @FXML
    private BorderPane Periode;

    @FXML
    private ChoiceBox<String> startingDayChoiceBox;

    @FXML
    private ChoiceBox<String> startingMonthChoiceBox;

    @FXML
    private ChoiceBox<String> startingYearChoiceBox;

    @FXML
    private ChoiceBox<String> endingDayChoiceBox;

    @FXML
    private ChoiceBox<String> endingMonthChoiceBox;

    @FXML
    private ChoiceBox<String> endingYearChoiceBox;

    @FXML
    private Button startButton;



    @FXML
    private void handleStartButton() throws IOException {
        //try{
        int startingDay = Integer.parseInt(startingDayChoiceBox.getValue());
        int startingMonth = Integer.parseInt(startingMonthChoiceBox.getValue());
        int startingYear = Integer.parseInt(startingYearChoiceBox.getValue());
        int endingDay = Integer.parseInt(endingDayChoiceBox.getValue());
        int endingMonth = Integer.parseInt(endingMonthChoiceBox.getValue());
        int endingYear = Integer.parseInt(endingYearChoiceBox.getValue());


           AppController.currentuser.createCalendar(startingYear,endingYear,startingMonth,startingDay,endingMonth,endingDay);
           User.currentcalendar=AppController.currentuser.getCalendar();
           App.users.put(AppController.currentuser.getname(),AppController.currentuser);
            App.SaveToDb("src/main/java/PlanningApp/Files/users.ser");

            Stage stage = (Stage) Periode.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/Calendar.fxml"));

            Parent root = loader.load();
            Stage stage1=new Stage();
            stage1.setTitle("Calendar Application");
            stage1.setScene(new Scene(root));
            stage1.show();





        //}catch (Exception e){

        //    System.out.println("exception in period");
        //}

    }
}

