package PlanningApp.Model;

public class User implements TaskUser,TimeslotUser{

    private String username;
    private String password;

    Calendar calendar ;
    Profile profile ;
    public String Id;

    public String minduration = "30";



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
    }

    public User(String name, String surname, String email, String password, String Id) {
        this.profile = new Profile(name,surname,email,password,Id);
        this.Id = Id;
    }
    public void createCalendar( int startyear, int endyear, int startmonth, int startday, int endmonth, int endday ){
        this.calendar = new Calendar(startyear,endyear ,startmonth, startday, endmonth, endday, this.minduration);
    }
    public Task createTask(String name,String duration, String starttime){
        return new SimpleTask(name,duration,starttime) ;
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
}
