package PlanningApp.Model;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Calendar implements TaskUser,TimeslotUser, Serializable, TimeCalcs {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    private LocalDate currentDate;
    private TreeMap<String, Day> Days;
    private String mintimeslot ;
    private String firstday ;
    private String lastday ;

    public void setTaskstobeplanned(ArrayList<Task> taskstobeplanned) {
        this.taskstobeplanned = taskstobeplanned;
    }


    //private ArrayList<Task> tasks; if needed use it if not it's okat it's commented
    private ArrayList<Task> taskstobeplanned;
    private ArrayList<Project> Projects;
    public static void addtask(Task task){
        tasks.add(task);
    }
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
    public static void setTasks(ArrayList<Task> tasks) {
        Calendar.tasks = tasks;
    }

    public String getFirstday() {
        return firstday;
    }

    public String getLastday() {
        return lastday;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setFirstday(String firstday) {
        this.firstday = firstday;
    }

    public void setLastday(String lastday) {
        this.lastday = lastday;
    }

    public Calendar(){
        Days = new TreeMap<String, Day>();
        currentDate = LocalDate.now();
        taskstobeplanned = new ArrayList<Task>();
    };
    public Calendar( int startyear, int endyear, int startmonth, int startday, int endmonth, int endday, String mintimeslot){
        // initialize the calendar with days for each day also call for the constructor
        // of the day class
        // add the days to the calendar
        Days = new TreeMap<String, Day>();
        this.fillYear(startyear,endyear,startmonth,startday,endmonth,endday);
        this.mintimeslot = mintimeslot ;
        System.out.println(this.getDays());
        taskstobeplanned = new ArrayList<Task>();
        //System.out.println(dayMap.getDays().get("2023-01-01Sunday"));

        //Here is what I did:
        currentDate = LocalDate.now();
    }
    public LocalDate getCurrentDate() {
        return currentDate;
    }
    public ArrayList<Task> getTaskstobeplanned() {
        return taskstobeplanned;
    }
    public void goToPreviousMonth() {
        currentDate = currentDate.minusMonths(1);
    }

    public void goToNextMonth() {
        currentDate = currentDate.plusMonths(1);
    }
    public ArrayList<Project> getProjects() {
        return Projects;
    }
    public void setProjects(ArrayList<Project> projects) {
        Projects = projects;
    }

    public void addDay(String day){
        this.Days.put(day, new Day(day,mintimeslot));
    }
    @Override
    public boolean planifyman(String time, String duration) {
        return false;
    }
    public void planifyman(Task task, String day){
        // go to the programmed date in the calendar
        // check if there is a timeslot with the same date
        String key = day;

        try {
            LocalTime period = LocalTime.parse(task.getDuration());
            LocalTime starttime = LocalTime.parse(task.getStarttime());
            LocalTime endtime = LocalTime.parse(task.getEndtime());
            boolean planifyed = this.Days.get(day).planifyman(task);
            if (planifyed){System.out.println("Task planified");}
        } catch (Exception e) {
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
    public void introducetaskstobeplanned(int numtasks, ArrayList<Task> tasks){
        //COPY TASK In taskstobeplanned
        for (int i = 0; i < numtasks; i++) {
            this.taskstobeplanned.add(tasks.get(i));
            //TODO in view enter the tasks and get only duration priority from 0 to 10 and name
        }
        Collections.sort(taskstobeplanned);
    }
    public boolean planifyauto(String startDate, String endDate) {
        // IMPORTANT before planifying auto you have to fill tasks is an arraylist
//        if (this.tasks.isEmpty()){
//            throw new IllegalArgumentException("There is no tasks to be planned");
//        }else{
            //this.introducetaskstobeplanned(numtasks,tasks);

       // }
        //for each task in the list go search if there is time
        // iterate over the period of time
        // through days
        // and planify manually in that day
        // give a simple for loop

//        ArrayList <Task> taskstobeplanned = new ArrayList<Task>();
//        taskstobeplanned.addAll(tasks);
        // for tasks in the arraylist taskstobeplanned
        boolean planified = true ;
        while ( !taskstobeplanned.isEmpty()) {
            System.out.println("\n\n\nThe tasks to be planned are");
            taskstobeplanned.forEach((n) -> System.out.println(n.getName()));
            Task task = taskstobeplanned.get(0);
            // for each day in the arraylist dayslist
            planified = task.planifyauto(startDate,endDate) && planified;
            System.out.println("\n\n\n");
        }
        return planified;

    }
    public void Createtasklist(){
        //TODO to be deleted just created to test
        // use the system in to enter the tasks
        System.out.println("Enter the number of tasks you want to enter");
        Scanner scanner = new Scanner(System.in);
        int numtasks = scanner.nextInt();
        for (int i = 0; i < numtasks; i++) {
            System.out.println("Enter the name of the task");
            String name = scanner.next();
            System.out.println("Enter the duration of the task");
            String duration = scanner.next();
            System.out.println("Enter the priority of the task");
            int priority = scanner.nextInt();
            // ask how many times repeated
            System.out.println("Enter the repetition");
            int repetition = scanner.nextInt() ;
            // ask if decomposable or not
            System.out.println("Is the task decomposable? (yes/no)");
            String decomposable = scanner.next();
            if (decomposable.equals("yes")) {
                Task task = new DecompTask(name,duration,priority,repetition);
                taskstobeplanned.add(task);
            }else{
                Task task = new SimpleTask(name,duration,priority, repetition);
                taskstobeplanned.add(task);
            }
        }
        Collections.sort(taskstobeplanned);
        //print this arraylist
    }

    @Override
    public void postpone(String time) {

    }

    @Override
    public void replan(String time) {

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

    public void History(){
        //TODO add it to history arraylist in user
        User.currentuser.addCalendartoHistory(this);
    }
    @Override
    public int evaluate(Object o) {
        return 0;
    }
}
