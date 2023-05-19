package PlanningApp.Model;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day implements TaskUser,TimeCalcs,TimeslotUser, Serializable {
    private String mintimeslot;
    public Day(String date, String mintimeslot){
        this.date = date;
        this.timeslot = new ArrayList<TimeSlot>();
        this.tasks = new ArrayList<Task>();
        this.mintimeslot = mintimeslot;
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
//        System.out.println(this.timeslot);

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
    public void planifyman(String time, String duration) { }
    public void planifyman(Task task){
        //  planify in the first time in the day
        //  System.out.println(task.getDuration()+" "+this.mintimeslot);
        AtomicBoolean planified = new AtomicBoolean(false);
        System.out.println(task.getStarttime()+" "+task.getEndtime()+" "+task.getDuration()+" "+task.getName());
        //TODO fix this
        if ( this.timeslot.isEmpty() ){
                System.out.println("No timeslots");
                //TODO here view
            }else{
            for (int i = 0; i < timeslot.size() && !planified.get(); i++) {
                TimeSlot timeSlot = timeslot.get(i) ;
                if (timeSlot.getstart().compareTo(task.getStarttime()) == 0 && timeSlot.getend().compareTo(task.getEndtime()) == 0) {
                    // simply planify add to tasks arraylist + remove time from timeslots

                    this.tasks.add(task);
                    User.currentuser.addTask(task);
                    timeslot.remove(i);
                    i--; // Decrement the index to adjust for the removed element
                    Calendar.addtask(task);
                    System.out.println("----------------------------------------------------Task created--------------------------------------------------------------------");
                    System.out.println(" starttime" + task.getStarttime() + " " + task.getEndtime() + " " + task.getDuration() + " " + task.getName());
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

                    planified.set(true);
                } else if (timeSlot.getstart().compareTo(task.getStarttime()) < 0 && timeSlot.getend().compareTo(task.getEndtime()) > 0) {
                    System.out.println("Here the task starttime" + task.getStarttime() + " " + task.getEndtime() + " " + task.getDuration() + " " + task.getName());
                    this.tasks.add(task);
                    User.currentuser.addTask(task);
                    timeslot.remove(i);
                    i--; // Decrement the index to adjust for the removed element
                    if (subtract(task.getStarttime(), timeSlot.getstart()).compareTo("00:30") > 0) {
                        addtimeslot(new TimeSlot(timeSlot.getstart(), add( timeSlot.getstart(), subtract(task.getStarttime(), timeSlot.getstart()))));
                        System.out.println("Time slot added");
                        System.out.println(this.timeslot.get(0).getstart()+this.timeslot.get(0).getend());
                    }
                    if (subtract(task.getEndtime(), timeSlot.getend()).compareTo("00:30") < 0) {
                        addtimeslot(new TimeSlot(timeSlot.getend(), add (timeSlot.getend(),subtract(task.getEndtime(), timeSlot.getend()))));
                    }
                    planified.set(true);
                    System.out.println("Task created");
                    System.out.println(" starttime" + task.getStarttime() + " " + task.getEndtime() + " " + task.getDuration() + " " + task.getName());
                } else {
                    // Error message or exception
                    System.out.println("Error Cannot planify task");

                }
            }
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

    public int evaluate(){
        // number of tasks completed over number of all tasks
        int nb_tasks = this.tasks.size();
        int nb_taskscompleted = 0;
        for (int i = 0; i < nb_tasks; i++) {
            if (this.tasks.get(i).getState() == State.completed){
                nb_taskscompleted++;
            }
        }
        return nb_taskscompleted/nb_tasks;
    }


    @Override
    public void addtimeslot(TimeSlot timeSlot){
        //TODO add the cases where there is chauvechement
        AtomicBoolean inserted = new AtomicBoolean(false);
        for (int i = 0; i < timeslot.size()-1; i++) {
            TimeSlot timeSlot1 = timeslot.get(i);
            TimeSlot nextSlot = timeslot.get(i + 1);
            // print the tested values in the next condition for testing purposes
            //System.out.println("timeSlot1.getstart() " + timeSlot1.getstart() + " timeSlot1.getend() " + timeSlot1.getend() + " nextSlot.getstart() " + nextSlot.getstart() + " nextSlot.getend() " + nextSlot.getend() + " timeSlot.getstart() " + timeSlot.getstart() + " timeSlot.getend() " + timeSlot.getend());
            //print the values of each comparaison for testing purposes
            //System.out.println("timeSlot1.getstart().compareTo(timeSlot.getstart()) " + timeSlot1.getstart().compareTo(timeSlot.getstart()) + " timeSlot1.getend().compareTo(timeSlot.getstart()) " + timeSlot1.getend().compareTo(timeSlot.getstart()) + " nextSlot.getstart().compareTo(timeSlot.getend()) " + nextSlot.getstart().compareTo(timeSlot.getend()) + " nextSlot.getend().compareTo(timeSlot.getend()) " + nextSlot.getend().compareTo(timeSlot.getend()));

            if ( timeSlot1.getstart().compareTo(timeSlot.getstart()) <= 0 && timeSlot1.getend().compareTo(timeSlot.getstart()) >= 0 && nextSlot.getstart().compareTo(timeSlot.getend()) <= 0 && nextSlot.getend().compareTo(timeSlot.getend()) >= 0) {
                timeSlot1.setend(timeSlot.getend());
                timeSlot1.setduration(subtract(timeSlot1.getend(),timeSlot1.getstart()));
                timeslot.remove(i + 1);
                inserted.set(true);
                //System.out.println("-----------------------------------------------------------------------------TEDKHOUL");
                //TODO FIX this
            }else {
                if (timeSlot1.getstart().compareTo(timeSlot.getstart()) >= 0 && timeSlot1.getend().compareTo(timeSlot.getend()) <= 0) {
                    // change it
                    timeSlot1.setstart(timeSlot.getstart());
                    timeSlot1.setend(timeSlot.getend());
                    timeSlot1.setduration(subtract(timeSlot1.getend(), timeSlot1.getstart()));
                    inserted.set(true);
                }else {
                    //TODO throw exeption here
                    

                }
            }
        }
        if ( !inserted.get() ){
            this.timeslot.add(timeSlot);
        }
    }

    public void removetimeslot( String start , String end ){
        TimeSlot timeSlot = new TimeSlot(start,end) ;
        this.timeslot.remove(timeSlot);
        System.out.println("Time slot removed");
        //TODO add the cases where there is chauvechement and where the slot to remove is in the middle of a slot
        }

public void printTasks(){
        this.tasks.forEach( task -> {
            System.out.println("Start"+task.getStarttime()+"End"+task.getEndtime()+"Duration"+task.getDuration()+"Name"+task.getName());
        } );
}
    public void printTimeslots(){
        this.timeslot.forEach( timeSlot -> {
            System.out.println("Start"+timeSlot.getstart()+"End"+timeSlot.getend()+"Duration"+timeSlot.getduration());
        } );
    }
    public void printDay() {
        System.out.println("Day [date=" + date + ", dayname=" + dayname + ", nb_mintasks=" + nb_mintasks + ", timeslot is "+ ( timeslot.isEmpty() ? "Empty ]": "contains data here it is...") );
        this.printTimeslots() ;
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks");
        } else {
            System.out.println("Tasks are:");
        }
        this.printTasks();
    }
}



