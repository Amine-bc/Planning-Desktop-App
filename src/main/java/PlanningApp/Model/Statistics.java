package PlanningApp.Model;

import javafx.collections.ArrayChangeListener;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class Statistics {

    static ArrayList<Tasksstates> taskstate = new ArrayList<>();
    static ArrayList<Float> dayseval = new ArrayList<>();
    public static Statistics Createstats( String day){
        Statistics stats = new Statistics();
        // iterate over the tasks in the day
        // generate taskstate
       Day daytostats = User.currentuser.calendar.getDays().get(day) ;
       System.out.println("Daytostats: \nTask state");
       System.out.println("Number of tasks in this day: "+daytostats.getTasks().size());
       int numtasksdone = 0 ;
        for (Task task: daytostats.getTasks()) {
            taskstate.add(new Tasksstates(task.getName(),task.getState())) ;
            //print taskstate
            System.out.println("task name: "+task.getName()+" task state: "+task.getState());
            if ( task.getState() == State.completed ){
                numtasksdone ++ ;
            }
        }
        float evaluation = (float) numtasksdone / (float)daytostats.getTasks().size() ;
        Statistics.dayseval.add( evaluation);
        // no projects stats in a day
        return stats;
    }

    
    public static Statistics Createstats( String startday, String endday){
        // do the Creatstats up for each day in the period
        // generate the stats for each day
        // then Projects
        // iterate over projects
        // generate projectstate
        String day = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE");

        // Parse the start and end dates into LocalDate objects
        LocalDate start = LocalDate.parse(startday, formatter);
        LocalDate end = LocalDate.parse(endday, formatter);

        // Generate the list of days between the start and end dates
        ArrayList<String> daysList = new ArrayList<>();
        LocalDate currentDate = start;
        while (!currentDate.isAfter(end)) {
            daysList.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }
        Statistics statsforday = null ;
        int eval = 0;
        String minday = startday;
        String maxday = startday ;
        for (String daytostats: daysList) {
            statsforday = Statistics.Createstats(daytostats);
            eval += User.currentuser.calendar.getDays().get(daytostats).evaluate();
            if ( User.currentuser.calendar.getDays().get(daytostats).evaluate() > User.currentuser.calendar.getDays().get(maxday).evaluate() ){
                maxday = daytostats;
            }
            if ( User.currentuser.calendar.getDays().get(daytostats).evaluate() < User.currentuser.calendar.getDays().get(minday).evaluate() )
            {
                minday = daytostats;
            }
        }
        eval = eval / daysList.size();
        System.out.println("Projects state");
        for (Project project: User.currentuser.calendar.getProjects()) {
            System.out.println("project name: "+project.getName()+" project state: "+project.getState());
        }
        System.out.println("Badges:\nThere are "+ User.currentuser.getProfile().getBadge().size() +"Badges");
        for (Badge badge: User.currentuser.getProfile().getBadge()) {
            System.out.println("Badge: "+badge);
        }
        // evaluation of days now
        System.out.println("Evaluation of days: "+ eval +"\nMost active day: "+maxday+"\nLeast active day: "+minday);


        // find the day where he was the most active and the day where he was the least active


        //add stats of each categorie
        String categorymax = "";
        for (Map.Entry<String, Integer> entry : Category.getCategories().entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println("Category: " + k + " Evaluation: " + v);
            if ( v > Category.getCategories().get(categorymax) ) {
                categorymax = k;
            }
        };


        return statsforday;
    }
    private static class Tasksstates{
        public Tasksstates(String taskName, State state) {
            TaskName = taskName;
            this.state = state;
        }
        String TaskName;
        State state;

    }

}
