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
    public void introducetaskstobeplanned(int numtasks, ArrayList<Task> tasks){
        //COPY TASK In taskstobeplanned
        for (int i = 0; i < numtasks; i++) {
            this.taskstobeplanned.add(tasks.get(i));
            //TODO in view enter the tasks and get only duration priority from 0 to 10 and name
        }
        Collections.sort(taskstobeplanned);
    }
    public void planifyauto(String startDate, String endDate, int numtasks, ArrayList<Task> tasks) {
        // IMPORTANT before planifying auto you have to fill tasks is an arraylist
//        if (this.tasks.isEmpty()){
//            throw new IllegalArgumentException("There is no tasks to be planned");
//        }else{
            Createtasklist(); // this is used to test
            //this.introducetaskstobeplanned(numtasks,tasks);

       // }
        //for each task in the list go search if there is time
        // iterate over the period of time
        // through days
        // and planify manually in that day
        // give a simple for loop
        ArrayList <Task> taskstobeplanned = new ArrayList<Task>();
        taskstobeplanned.addAll(tasks);
        // generate array list of days

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE");

        // Parse the start and end dates into LocalDate objects
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // Generate the list of days between the start and end dates
        ArrayList<String> daysList = new ArrayList<>();
        LocalDate currentDate = start;
        while (!currentDate.isAfter(end)) {
            daysList.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }

        // for tasks in the arraylist taskstobeplanned
        for (int j = 0; j < taskstobeplanned.size(); j++) {
            Task task = taskstobeplanned.get(j);
            // for each day in the arraylist dayslist
            int k = 0;
            while (k < daysList.size()) {
                Day day = Days.get(daysList.get(k));
                // Your code logic here
                AtomicBoolean planified = new AtomicBoolean(false);
                System.out.println(task.getStarttime()+" "+task.getEndtime()+" "+task.getDuration()+" "+task.getName());
                //TODO fix this
                if ( day.getTimeslot().isEmpty() ){
                    System.out.println("No timeslots");
                    //TODO here view
                }else{
                    for (int i = 0; i < day.getTimeslot().size() && !planified.get(); i++) {
                        TimeSlot timeSlot = day.getTimeslot().get(i) ;
                        if (timeSlot.getstart().compareTo(task.getStarttime()) == 0 && timeSlot.getend().compareTo(task.getEndtime()) == 0) {
                            // simply planify add to tasks arraylist + remove time from timeslots

                            this.tasks.add(task);
                            User.currentuser.addTask(task);
                            day.getTimeslot().remove(i);
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
                            day.getTimeslot().remove(i);
                            i--; // Decrement the index to adjust for the removed element
                            if (subtract(task.getStarttime(), timeSlot.getstart()).compareTo("00:30") > 0) {
                                addtimeslot(new TimeSlot(timeSlot.getstart(), add( timeSlot.getstart(), subtract(task.getStarttime(), timeSlot.getstart()))));
                                System.out.println("Time slot added");
                                System.out.println(day.getTimeslot().get(0).getstart()+day.getTimeslot().get(0).getend());
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

                k++;
            }

        }


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

    @Override
    public void postpone(String time) {

    }

    @Override
    public void replan(String time) {

    }

    @Override
    public int evaluate() {
        return 0 ;
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
        User.currentuser.addCalendar(this);
    }
}
