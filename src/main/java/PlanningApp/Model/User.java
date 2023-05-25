package PlanningApp.Model;

import PlanningApp.Com.HistoryInterface;
import javafx.scene.chart.CategoryAxis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements TaskUser,TimeslotUser, Serializable, HistoryInterface {

    public static User currentuser ;
    //TODO do not forget to set the currentuser
    public static Calendar currentcalendar ;
    public ArrayList<Calendar> History ;
    public ArrayList<Calendar> getHistorylist(){
        return History;
    }
    private HashMap<Badge,Integer> badgemap ;

    public void initbadgemap(){
        badgemap = new HashMap<>() ;
        badgemap.put(Badge.Good,0);
        badgemap.put(Badge.Excellent,0);
        badgemap.put(Badge.VeryGood,0);
    }
    public void addbadge( Badge badge){
        badgemap.put(badge, badgemap.get(badge)+1);
    }
    public int getbadgenum(Badge badge){
        return badgemap.get(badge);
    }


    public void addCalendartoHistory(Calendar calendar){
        History.add(calendar);
        //then serialize the list to the file
        try {
            FileOutputStream fileOut = new FileOutputStream("History"+profile.getname()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(History);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in History.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    @Override
    public void bringbackcalendar( String startday, String endday){};

    public Calendar bringbackcalendar( String startday, String endday, String nothing){
        for (int i = 0; i < History.size(); i++) {
            if (History.get(i).getFirstday().equals(startday) && History.get(i).getLastday().equals(endday)){
                //currentcalendar = History.get(i);
                return History.get(i) ;
                //TODO: add view here to show the calendar
            }
        }
        return null ;
    }
    private String username;
    private String password;
    private ArrayList<Task> taskslist = new ArrayList<Task>(); //TODO this task list will be filled while the person creates tasks

    public ArrayList<Task> getTaskslist() {
        return taskslist;
    }
    public void addTask(Task task){
        this.taskslist.add(task);
    }
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
        this.calendar = new Calendar();
        this.profile.setname(username);
        this.profile.setpassword(password);
        User.currentuser = this;
        Category.initcategory();
        initbadgemap();
        History = new ArrayList<>() ;
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
        User.currentcalendar = this.calendar ;
    }
    public Task createTask(String name,String duration, String starttime, int priority, int repetition){
        //check if task is biger then this.minduration
        // if it's not return null
        if ( duration.compareTo(this.minduration) < 0 ){
            System.out.println("Task duration is smaller then the minimum duration");
            //TODO: add view
            return null ;
        }else{
            System.out.println("Task created");
            //TODO: add view here to show the task created say if you don't plan you will leave it
            Calendar.addtask(new SimpleTask(name,duration,priority,repetition));
        return new SimpleTask(name,duration,priority,repetition) ;
        }
    }
    @Override
    public boolean planifyman(String time, String duration) {
        // overloaded not usefull in this method
        return false;
    }
    public void planifyman(Task task, String day){
        // go to the calendar try to planify in the asked time
        // if it's not possible ask the user to choose another time
        // if it's possible planify it
        calendar.planifyman(task,day);
        // correct the day here
    };

    @Override
    public boolean planifyauto(String startperiod, String endperiod) {
            return User.currentcalendar.planifyauto(startperiod,endperiod);
    }
    @Override
    public void postpone(String time) {

    }

    @Override
    public void replan(String time) {

    }

    @Override
    public int evaluate(Object o) {
        return 0;
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

    public Profile getProfile() {
        return profile;
    }
    public void removetimeslot( String day, String start , String end ) {
        this.calendar.removetimeslot(day,start,end);
    }

    private int numfelecitations = 0 ;
    //setters and getters for this attribute
    public int getnumfelecitations(){
        return this.numfelecitations;
    }
    public void incnumfelecitations(){
        this.numfelecitations ++ ;
    }
    public void settaskasdone(Task task)
    {
        // set the state to done
        task.setState(State.completed);
        // increment the counter of done tasks in day
        calendar.getDays().get(task.getDay()).incrementdonetasks();
        // compare it to the min done tasks in the day to give a badge or not
        if ( calendar.getDays().get(task.getDay()).gettaskdone() >= calendar.getDays().get(task.getDay()).getNb_mintasks() ) {
            // give a badge

            incnumfelecitations();
            if ( getnumfelecitations() >=  5 ) {
                // give a badge
                this.addbadge(Badge.Good);
                setnumfelecitations(0);
                if ( getbadgenum(Badge.Good) >=  5 ) {
                    // give a badge
                    this.addbadge(Badge.VeryGood);
                    setbadgenum(0,Badge.Good);
                 if ( getbadgenum(Badge.VeryGood) >=  5 ) {
                    // give a badge
                    this.addbadge(Badge.Excellent);
                     setbadgenum(0,Badge.VeryGood);

                 }
                }
            }

        }

        // if give yes then give the badge and check how many badge of good he has
//        // ..etc
//        3andi un attribut f user ta3 nombre de felicitations et nombre de tache realisées
//        - when the user click on complete the task , i set it's ETAT to completed and then nbr de tache realisees++
//            -if nbr tache realisees >= nbr min de tache par jour , we send a felicitation message and nombre de felicitations++
//            - if nombre de felicitation == 5 the user will have a badge GOOD and nombre de felicitation must be reset To 0
//            - if we have 3 badges good we will have a badge veryGOOD and if we have 3 badges veryGOOd we will have a badge excellent
    }

    public void setnumfelecitations(int i) {
        this.numfelecitations = i ;
    }
    public void setbadgenum(int num, Badge badge){
        this.badgemap.put(badge,num);
    }
}
