package PlanningApp.Model;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day implements TaskUser,TimeCalcs,TimeslotUser{
     private String mintimeslot;
    public Day(String date, String mintimeslot){
        this.date = date;
        this.timeslot = new ArrayList<TimeSlot>();
        this.tasks = new ArrayList<Task>();
//        LocalTime time = LocalTime.parse("00:00"); // Parse the initial time
//
//        String[] timeParts = this.mintimeslot.split(":");
//
//        int hours = Integer.parseInt( timeParts[0] );
//
//        int minutes = Integer.parseInt(timeParts[1]);
//
//        // Convert the time to minutes since midnight
//
//        int minutesSinceMidnight = hours * 60 + minutes;
//
//        // Divide the minutes by the time slot to get the number of time slots
//        int numTimeSlots = 24*60 / minutesSinceMidnight;
//        for (int i = 0; i < numTimeSlots  ; i++) { // Loop 10 times to add 30 minutes each time
//            String formattedTime0 = time.format(DateTimeFormatter.ofPattern("HH:mm")); // Format the time as "HH:mm"
//            time = time.plusMinutes(Integer.parseInt(this.mintimeslot)); // Add minutes to the time
//            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm")); // Format the time as "HH:mm"
//            this.timeslot.add(new TimeSlot(formattedTime,formattedTime,this.mintimeslot));
//        }
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        this.timeslot.add(new TimeSlot( LocalTime.parse(this.timeslot.get(numTimeSlots).getTask().getStarttime()).plus(Duration.parse("PT" + this.timeslot.get(numTimeSlots).getTask().getStarttime().replace(':', 'H') + "M")).format(formatter) ,"23:59", LocalTime.parse("23:59").minus(Duration.parse("PT" + this.timeslot.get(numTimeSlots).getTask().getStarttime().replace(':', 'H') + "M")).format(formatter) ));
//
        //System.out.println(this.timeslot);

    }


    private String date ;
    private String dayname ;
    private int nb_mintasks = 0;
    private ArrayList <TimeSlot> timeslot ;
    private ArrayList<Task> tasks ;
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
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
        if ( task.getDuration().compareTo(this.mintimeslot) > 0 ) {
            if ( this.timeslot.isEmpty() ){

                System.out.println("No timeslots");
                //TODO here view

            }else{

                this.timeslot.forEach( timeSlot -> {
                    if ( timeSlot.getstart().compareTo(task.getStarttime()) < 0 && timeSlot.getend().compareTo(task.getEndtime()) > 0 ){
                        // simply planify add to tasks arraylist + remove time from timeslots
                        timeSlot.setend(subtract(timeSlot.getend(),task.getDuration()));
                    }
                } );

            }
        }else{
            // TODO problem in duration Exeption
        }

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


    @Override
    public void addtimeslot(TimeSlot timeSlot){
        //TODO add the cases where there is chauvechement
        AtomicBoolean inserted = new AtomicBoolean(false);
        this.timeslot.forEach( timeSlot1 -> {
            if (  timeSlot1.getstart().compareTo(timeSlot.getstart()) >= 0 && timeSlot1.getend().compareTo(timeSlot.getend()) <= 0 ){
                // change it
                timeSlot1.setstart(timeSlot.getstart());
                timeSlot1.setend(timeSlot.getend());
                timeSlot1.setduration( subtract(timeSlot1.getend(),timeSlot1.getstart()) );
                inserted.set(true);
            }
        } );
        if ( !inserted.get() ){
            this.timeslot.add(timeSlot);
        }
    }

    public void removetimeslot( TimeSlot timeSlot){
        this.timeslot.remove(timeSlot);
    }

    public void printTimeslots(){
        this.timeslot.forEach( timeSlot -> {
            System.out.println("Start"+timeSlot.getstart()+"End"+timeSlot.getend()+"Duration"+timeSlot.getduration());
        } );
    }

    public void printDay() {
        System.out.println("Day [date=" + date + ", dayname=" + dayname + ", nb_mintasks=" + nb_mintasks + ", timeslot is "+ ( timeslot.isEmpty() ? "Empty ]": "contains data here it is...") );
        this.printTimeslots() ;
    }
}
