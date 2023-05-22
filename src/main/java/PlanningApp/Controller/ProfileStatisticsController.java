package PlanningApp.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileStatisticsController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;

    private final String[] days = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};
    private final float[] values = {0.4f, 0.6f, 0.5f, 1f, 0.8f, 0.3f, 0.9f};

    @FXML
    private void handleMyWeekInfoClicked(MouseEvent event) {
        lineChart.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLineChart();
    }

    private void setupLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(true);
        lineChart.setLegendVisible(false);
        lineChart.setVisible(false); // Initially hidden

        xAxis.setCategories(FXCollections.observableArrayList(days));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < days.length; i++) {
            series.getData().add(new XYChart.Data<>(days[i], values[i]));
        }

        lineChart.getData().add(series);
        lineChart.setAnimated(false);
    }
}
