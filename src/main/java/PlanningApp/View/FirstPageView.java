package PlanningApp.View;

import PlanningApp.Controller.DayController;
import PlanningApp.Model.Day;
import PlanningApp.Model.SimpleTask;
import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageView extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Authentification
//
//        FXMLLoader fxmlLoader = new FXMLLoader(FirstPageView.class.getResource("Calendar.fxml"));
//        Parent root = fxmlLoader.load();
//        stage.setTitle("Calendar Application");
//        stage.setScene(new Scene(root));
//        stage.show();

        //------------------------------------------------------------------------------------------


        FXMLLoader fxmlLoader = new FXMLLoader(FirstPageView.class.getResource("DayPage.fxml"));
        Parent root = fxmlLoader.load();

        // Access the DayController and set the Day object
        DayController dayController = fxmlLoader.getController();

        User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd");


        user.createTask("Task1","02:00","04:25");
        user.createCalendar(2021,2022,2,10,10,30);
        String date ="2021-02-11Thursday";
        user.addtimeslot("2021-02-11Thursday","02:00","04:25");
        user.addtimeslot("2021-02-11Thursday","10:00","12:00");
        user.addtimeslot("2021-02-11Thursday","01:00","02:00");
        user.addtimeslot("2021-02-11Thursday","13:00","14:00");
        user.addtimeslot("2021-02-11Thursday","14:00","16:25");
        user.addtimeslot("2021-02-11Thursday","17:00","19:00");
        Task task = user.createTask("Task1","02:00","04:25");
        user.planifyman(task,date);
        user.getCalendar().getDays().get(date).printDay();
        Day day = user.getCalendar().getDays().get(date);
        dayController.Showday(user,date);
        stage.setTitle("Day Application");
        stage.setScene(new Scene(root));
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
//
        stage.show();
//












//
//        FXMLLoader fxmlLoader = new FXMLLoader(FirstPageView.class.getResource("FirstPage.fxml"));
//
//         Scene scene = new Scene(fxmlLoader.load(), 520, 400);
//         stage.setTitle("PlanningApp");
//         stage.setScene(scene);
//         stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}