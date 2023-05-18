package PlanningApp.Controller;

import PlanningApp.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import PlanningApp.Model.Day;
import PlanningApp.Model.TimeSlot;
import PlanningApp.Model.Task;

public class DayController {
    @FXML
    private VBox timeslotPane;

    @FXML
    private VBox taskPane;

    @FXML
    private SplitPane splitPane;

    public void Showday(User user, String dayname) {
        Day day = user.getCalendar().getDays().get(dayname);
        populateData(day);
    }


    private void populateData( Day day) {
        for (TimeSlot timeslot : day.getTimeslot()) {
            HBox timeslotBox = new HBox();
            Label startLabel = new Label(timeslot.getstart());
            Label endLabel = new Label(timeslot.getend());

            timeslotBox.getChildren().addAll(startLabel, endLabel);
            timeslotPane.getChildren().add(createStyledBox(timeslotBox));
        }

        for (Task task : day.getTasks()) {
            HBox taskBox = new HBox();
            Label nameLabel = new Label(task.getName());
            Label startLabel = new Label("Start: " + task.getStarttime());
            Label endLabel = new Label("End: " + task.getEndtime());

            taskBox.getChildren().addAll(nameLabel, startLabel, endLabel);
            taskPane.getChildren().add(createStyledBox(taskBox));
        }
    }

    private VBox createStyledBox(HBox content) {
        VBox box = new VBox(content);
        box.setStyle("-fx-background-color: #0598FF;  -fx-padding: 5px;");
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

}