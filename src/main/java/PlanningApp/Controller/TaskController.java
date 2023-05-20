package PlanningApp.Controller;
import PlanningApp.Model.*;

public class TaskController {
    //create task and then plan it
    public void createTask(){

        SimpleTask task = new SimpleTask("Task1","30","2021-02-11Thursday","10:00",0);
        User user = new User("Amine","passwd");

        String date = "2023-01-01Sunday";

        System.out.println(user.getCalendar().getDays().get(date).getDate() );
        user.planifyman(task,date);

    }

    }

