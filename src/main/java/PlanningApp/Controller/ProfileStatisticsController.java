package PlanningApp.Controller;

import PlanningApp.Model.App;
import PlanningApp.Model.Statistics;
import PlanningApp.Model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProfileStatisticsController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;

    private   String[] days = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};
    private  ArrayList<Float> values ;





    @FXML
    private void handleMyWeekInfoClicked(MouseEvent event) {
        lineChart.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE");
        String formattedDate = currentDate.format(outputFormatter);
        System.out.println(formattedDate);

        LocalDate currentDatemoins = currentDate.minusDays(3);
        String formattedDatemoins = currentDatemoins.format(outputFormatter);

// Add three days
        LocalDate currentDateplus = currentDate.plusDays(3);
        String formattedDateplus = currentDateplus.format(outputFormatter);

        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println(formattedDateplus);
        System.out.println(formattedDatemoins);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        User.currentuser= App.currentuser;
        Statistics.Createstats(formattedDatemoins, formattedDateplus);

        System.out.println(Statistics.dayseval.size());
        values=Statistics.dayseval;
        for(float val:values){
            System.out.println("val="+val);
        }
        setupLineChart();
        //values.add(0.6f);
        //values.add(0.8f);
        //values.add(0.5f);
        //values.add(0.9f);
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
        for (int i = 0; i < values.size(); i++) {
            series.getData().add(new XYChart.Data<>(days[i], values.get(i)));
        }

        lineChart.getData().add(series);
        lineChart.setAnimated(false);
    }





}

