package PlanningApp.Model;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Day {

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

}
