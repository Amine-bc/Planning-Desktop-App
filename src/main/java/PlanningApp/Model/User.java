package PlanningApp.Model;

import java.io.*;

public class User implements TaskUser,TimeslotUser, Serializable {

    private String username;
    private String password;

    Calendar calendar ;
    Profile profile ;
    public String minduration = "00:30";



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = new Profile();
        this.profile.setname(username);
        this.profile.setpassword(password);
    }
    // getters and setters
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
   // get username
    public String getname(){
        return this.profile.getname();
    }
    public void setname(String name){
        this.profile.setname(name);
    }


    public void Fillinfo(String name, String surname, String email, String password) {
        this.profile = new Profile(name,surname,email,password);
    }
    public void createCalendar( int startyear, int endyear, int startmonth, int startday, int endmonth, int endday ){
        this.calendar = new Calendar(startyear,endyear ,startmonth, startday, endmonth, endday, this.minduration);
    }
    public Task createTask(String name,String duration, String starttime){
        //check if task is biger then this.minduration
        // if it's not return null
        if ( duration.compareTo(this.minduration) < 0 ){
            System.out.println("Task duration is smaller then the minimum duration");
            //TODO: add view
            return null ;
        }else{
            System.out.println("Task created");
            //TODO: add view here to show the task created say if you don't plan you will leave it
        return new SimpleTask(name,duration,starttime) ;
        }
    }
    @Override
    public void planifyman(String time, String duration) {
        // overloaded not usefull in this method
    }
    public void planifyman(Task task, String day){
        // go to the calendar try to planify in the asked time
        // if it's not possible ask the user to choose another time
        // if it's possible planify it
        calendar.planifyman(task,day);
        // correct the day here

    } ;

    @Override
    public void planifyauto(String startperiod, String endperiod) {


    }
    public void planifyauto(String startperiod, String endperiod, String day) {

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

    public Calendar getCalendar() {
        return calendar;
    }
    @Override
    public void addtimeslot(TimeSlot timeSlot){};

    public void addtimeslot(String day,String start, String end ){
        if ( start.compareTo(end) < 0 ){
            TimeSlot timeslot = new TimeSlot(start, end);
            this.calendar.addtimeslot(timeslot,day);
        }else{
            System.out.println("Error invalid input");
            //TODO exeption time invalid
        }

        };
    public void removetimeslot( String start , String end ) {
    }
    public void removetimeslot( String day, String start , String end ) {
        this.calendar.removetimeslot(day,start,end);
    }


}
