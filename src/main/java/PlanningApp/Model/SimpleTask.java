package PlanningApp.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import PlanningApp.Model.TimeSlot;
public class SimpleTask extends Task{

    public SimpleTask(){
        super();
    };
    public SimpleTask(String name,String duration, String day, String starttime, int repetition){
        super(name,duration,starttime,0,day,repetition);
        // this constructor is used once someone clicks on new task
        // first we ask if he wants to auto plan it or manually
        // second we ask for its type decomp or simple
        // Then create an instance then
    }
    public SimpleTask(String name,String duration,int Priority, int repetition){
        super(name,duration,Priority, repetition);
        // this constructor is used once someone clicks on new task
        // first we ask if he wants to auto plan it or manually
        // second we ask for its type decomp or simple
        // Then create an instance then
    }


    @Override
    public boolean planifyman(String time,String duration) {
        return false;
    };

    @Override
    public boolean planifyauto(String startDate, String endDate) {
        // generate array list of days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE");

        // Parse the start and end dates into LocalDate objects
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Generate the list of days between the start and end dates
        ArrayList<String> daysList = new ArrayList<>();
        LocalDate currentDate = start;
        while (!currentDate.isAfter(end) && !User.currentcalendar.getDays().get(currentDate.format(formatter)).getTimeslot().isEmpty()) {
            daysList.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }

        // now we have the list of days
        // we need to check if the task can be done in one day or not
//        System.out.println("--------------------------------------------------------tt3aaaaaaaaaawed");
        boolean planned = false;
            int cpt = 0;
            while (!planned && cpt < daysList.size()) {
                String day = daysList.get(cpt);
                if (!this.getCalendar().getDays().get(day).getTimeslot().isEmpty()) {
                    // Create a copy of the timeslot list
                    List<TimeSlot> timeSlots = this.getCalendar().getDays().get(day).getTimeslot();
                    // Search for the first free time slot
                    Iterator<TimeSlot> iterator = timeSlots.iterator();
                    while (iterator.hasNext() && !planned) {
                        TimeSlot timeSlot = iterator.next();
                        if (timeSlot.getduration().compareTo(this.getDuration()) >= 0) {
                            Task taskToPlan = new SimpleTask(this.getName(), this.getDuration(), day, timeSlot.getstart(), this.getRepetition());
                            if (this.getRepetition() ==0 ){
                                planned = this.getCalendar().getDays().get(day).planifyman(taskToPlan);

                            }else{
                                int i = 0 ;
                                while ( i < daysList.size()){
                                int repet = 1;
                                planned = this.getCalendar().getDays().get(day).planifyman(taskToPlan);
                                while ( repet < this.getRepetition()+1){
                                    System.out.println("Repetition"+repet);
                                    System.out.println("day"+day);
                                    day = getNextDay(day);
                                    repet++;
                                }
                                taskToPlan.setDay(day);
                                i += this.getRepetition() ;
                            }
                            }
//                             remove the task from taskstobeplanified
                            User.currentcalendar.getTaskstobeplanned().remove(this);
                            break;
                        }
                    }
                }
                cpt++;
            }

        return planned;
    }


    @Override
    public void postpone(String time) {
        //change the start time and end time
        if (Integer.parseInt(time) > Integer.parseInt(this.getStarttime())) {
            this.setStarttime(time);
        }else{
                System.out.println("Error in postponing down in simple task model");
            }
        }


    @Override
    public void replan(String time) {
        //change the start time and end time
        this.setStarttime(time);

    }
    @Override
    public int evaluate(Object o) {
        return 0;
    }

    // setter and getters for the attributes

}
