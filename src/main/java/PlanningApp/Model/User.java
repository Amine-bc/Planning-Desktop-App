package PlanningApp.Model;

public class User implements TaskUser{
    Calendar calendar ;
    Profile profile ;
    public String Id;

    public String minduration = "30";

    public User(String name, String surname, String email, String password, String Id) {
        this.profile = new Profile(name,surname,email,password,Id);
        this.calendar = new Calendar();
        this.Id = Id;
    }
    public Task createTask(String name,String duration, String starttime,String endtime,String state){
        return new SimpleTask(name,duration,starttime)    ;
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
}
