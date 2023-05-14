package PlanningApp.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;

public class Calendar implements TaskUser,TimeslotUser{
    private TreeMap<String, Day> Days;
    private String mintimeslot ;
    private String firstday ;
    private String lastday ;
    //private ArrayList<Task> tasks; if needed use it if not it's okat it's commented

    public Calendar( int startyear, int endyear, int startmonth, int startday, int endmonth, int endday, String mintimeslot){
        // initialize the calendar with days for each day also call for the constructor
        // of the day class
        // add the days to the calendar
        Days = new TreeMap<String, Day>();
        this.fillYear(startyear,endyear,startmonth,startday,endmonth,endday);
        this.mintimeslot = mintimeslot ;
        System.out.println(this.getDays());
        //System.out.println(dayMap.getDays().get("2023-01-01Sunday"));
    }



    public void addDay(String day){
        this.Days.put(day, new Day(day,mintimeslot));
    }
    @Override
    public void planifyman(String time, String duration) {

    }
    public void planifyman(Task task, String day){
        // go to the programmed date in the calendar
        // check if there is a timeslot with the same date
        String key = day;

        try {
            LocalTime period = LocalTime.parse(task.getDuration());
            LocalTime starttime = LocalTime.parse(task.getStarttime());
            LocalTime endtime = LocalTime.parse(task.getEndtime());
            this.Days.get(day).planifyman(task);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time string: " + task.getDuration());
        }

    }

    public void fillYear(int startyear , int endyear,  int startmonth, int startday, int endmonth, int endday) {
        LocalDate startDate = LocalDate.of(startyear, startmonth, startday);
        LocalDate endDate = LocalDate.of(endyear, endmonth, endday);
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            String key = date+date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            Days.put(key.toString(), new Day(date.toString(),mintimeslot));
        }
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
    @Override
    public void addtimeslot(TimeSlot timeSlot){};

    public void addtimeslot(TimeSlot timeSlot, String day){
        this.Days.get(day).addtimeslot(timeSlot);
    };
    @Override
    public void removetimeslot(String start , String end){};
    public void removetimeslot(String day, String start , String end ) {
     this.Days.get(day).removetimeslot(start,end);
    }
    }
