package PlanningApp.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FirstPageView.class.getResource("Calendar.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Calendar Application");
        stage.setScene(new Scene(root));
        stage.show();
         //Scene scene = new Scene(fxmlLoader.load(), 520, 400);
         //stage.setTitle("PlanningApp");
         //stage.setScene(scene);
         //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}