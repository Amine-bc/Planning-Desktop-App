package PlanningApp.Model;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;


public class Calendar implements TaskUser,TimeslotUser, Serializable {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    private LocalDate currentDate;
    private TreeMap<String, Day> Days;
    private String mintimeslot ;
    private String firstday ;
    private String lastday ;
    //private ArrayList<Task> tasks; if needed use it if not it's okat it's commented
    private ArrayList<Task> taskstobeplanned;
    public static void addtask(Task task){
        tasks.add(task);
    }
    public void introducetaskstobeplanned(int numtasks, ArrayList<Task> tasks){
        //COPY TASK In taskstobeplanned
        for (int i = 0; i < numtasks; i++) {
            this.taskstobeplanned.add(tasks.get(i));
            //TODO in view enter the tasks and get only duration priority from 0 to 10 and name
        }
        Collections.sort(taskstobeplanned);
    }
    public Calendar(){
        Days = new TreeMap<String, Day>();
        currentDate = LocalDate.now();
    };
    public Calendar( int startyear, int endyear, int startmonth, int startday, int endmonth, int endday, String mintimeslot){
        // initialize the calendar with days for each day also call for the constructor
        // of the day class
        // add the days to the calendar
        Days = new TreeMap<String, Day>();
        this.fillYear(startyear,endyear,startmonth,startday,endmonth,endday);
        this.mintimeslot = mintimeslot ;
        System.out.println(this.getDays());
        //System.out.println(dayMap.getDays().get("2023-01-01Sunday"));

        //Here is what I did:
        currentDate = LocalDate.now();
    }
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void goToPreviousMonth() {
        currentDate = currentDate.minusMonths(1);
    }

    public void goToNextMonth() {
        currentDate = currentDate.plusMonths(1);
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
    public void planifyauto(String startperiod, String endperiod, int numtasks, ArrayList<Task> tasks) {
        //check if there is a task in the list
        //for each task in the list go search if there is time
        this.introducetaskstobeplanned(numtasks,tasks);
        // iterate over the period of time
        // through days
        // and planify manually in that day

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
    public void setCalendar(TreeMap<String, Day> days) {
        Days = days;
    }
    public void Createtasklist(){
        //TODO to be deleted just created to test
        // use the system in to enter the tasks
        System.out.println("Enter the number of tasks you want to enter");
        Scanner scanner = new Scanner(System.in);
        int numtasks = scanner.nextInt();
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < numtasks; i++) {
            System.out.println("Enter the name of the task");
            String name = scanner.next();
            System.out.println("Enter the duration of the task");
            String duration = scanner.next();
            System.out.println("Enter the priority of the task");
            int priority = scanner.nextInt();
            // ask if decomposable or not
            System.out.println("Is the task decomposable? (yes/no)");
            String decomposable = scanner.next();
            if (decomposable.equals("yes")) {
                Task task = new DecompTask(name,duration,priority);
                taskstobeplanned.add(task);
            }else{
                Task task = new SimpleTask(name,duration,priority);
                tasks.add(task);
            }
        }
        Collections.sort(taskstobeplanned);
    }
}
