package PlanningApp.Model;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Day implements TaskUser{

    public Day(String date){
        this.date = date;
        this.timeslot = new ArrayList<TimeSlot>();
        LocalTime time = LocalTime.parse("00:00"); // Parse the initial time
        for (int i = 0; i < 10; i++) { // Loop 10 times to add 30 minutes each time
            String formattedTime0 = time.format(DateTimeFormatter.ofPattern("HH:mm")); // Format the time as "HH:mm"
            time = time.plusMinutes(30); // Add 30 minutes to the time
            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm")); // Format the time as "HH:mm"
            this.timeslot.add(new TimeSlot(formattedTime,formattedTime,"30"));
        }
        //System.out.println(this.timeslot);
    }
    private String date ;
    private String dayname ;
    private int nb_mintasks = 0;
    private ArrayList <TimeSlot> timeslot ;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public ArrayList<TimeSlot> getTimeslot() {
        return timeslot;
    }
    public void setTimeslot(ArrayList<TimeSlot> timeslot) {
        this.timeslot = timeslot;
    }
    public String getDayname() {
        return dayname;
    }
    public void setDayname(String dayname) {
        this.dayname = dayname;
    }
    public int getNb_mintasks() {
        return nb_mintasks;
    }
    public void setNb_mintasks(int nb_mintasks) {
        this.nb_mintasks = nb_mintasks;
    }
    @Override
    public void planifyman(String time, String duration) {
        // TODO Auto-generated method stub

    }
    public void planifyman(Task task){
        //planify in the first time in the day
        this.timeslot.get(0).setTask(task);
        System.out.println(this.timeslot.get(0).getTask().getName());

    }
    @Override
    public void planifyauto(String startperiod, String endperiod) {
        // TODO Auto-generated method stub

    }
    @Override
    public void postpone(String time) {
        // TODO Auto-generated method stub

    }
    @Override
    public void replan(String time) {
        // TODO Auto-generated method stub

    }

    public void evaluate(){
        // TODO Auto-generated method stub

    }

    public void printDay() {
        System.out.println("Day [date=" + date + ", dayname=" + dayname + ", nb_mintasks=" + nb_mintasks + ", timeslot="
                + timeslot + "]");
    }
}
