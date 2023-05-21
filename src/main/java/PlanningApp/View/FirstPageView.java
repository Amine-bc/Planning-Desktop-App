package PlanningApp.View;

import PlanningApp.Controller.AppController;
import PlanningApp.Controller.DayController;
import PlanningApp.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppController.setFilePath("C:\\Users\\admin\\IdeaProjects\\PlanningAppJavaProject\\src\\main\\java\\PlanningApp\\Files\\users.ser");

        App.ReadfromDb(AppController.FilePath);
        System.out.println("hmmmmm");
        //Authentification
        FXMLLoader fxmlLoader = new FXMLLoader(FirstPageView.class.getResource("FirstPage.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Application");
        stage.setScene(new Scene(root));
        stage.show();

        //------------------------------------------------------------------------------------------

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