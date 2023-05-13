package PlanningApp.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Calendar implements TaskUser{
    private TreeMap<String, Day> Days;
    private String firstday ;
    private String lastday ;
    //private ArrayList<Task> tasks; if needed use it if not it's okat it's commented

    public Calendar(){
        // initialize the calendar with days for each day also call for the constructor
        // of the day class
        // add the days to the calendar
        this.Days = new TreeMap<String, Day>();
        DayMap dayMap = new DayMap();
        dayMap.fillYear(2023);
        this.Days = dayMap.getDays();
        System.out.println(dayMap.getDays());
        //System.out.println(dayMap.getDays().get("2023-01-01Sunday"));
    }

    public void addDay(String day){
        this.Days.put(day, new Day(day));
    }
    @Override
    public void planifyman(String time, String duration) {

    }
    public void planifyman(Task task, String day){
        // go to the programmed date in the calendar
        // check if there is a timeslot with the same date

        String[] parts = day.split(" ");
        String dateStr = parts[0];
        String weekday = parts[1];
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String key = date+weekday;
        System.out.println(this.Days.get(key) );
        this.Days.get(key).planifyman(task);

        this.Days.get(key).printDay();

    }
    @Override
    public void planifyauto(String startperiod, String endperiod) {

    }

    @Override
    public void postpone(String time) {

    }

    @Override
    public void replan(String time) {

    }

    @Override
    public void evaluate() {

    }

    public TreeMap<String, Day> getDays() {
        return Days;
    }
}
