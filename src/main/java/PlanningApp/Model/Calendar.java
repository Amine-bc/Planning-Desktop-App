package PlanningApp.Model;

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
        System.out.println(dayMap.getDays());
    }

    public void addDay(String day){
        this.Days.put(day, new Day(day));
    }
    @Override
    public void planifyman(String time, String duration) {

    }
    public void planifyman(Task task){
        // go to the programmed date in the calendar
        // check if there is a timeslot with the same date

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
}
