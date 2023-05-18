package PlanningApp.Controller;
import PlanningApp.Model.*;

public class TaskController {
    //create task and then plan it
    public void createTask(){

        SimpleTask task = new SimpleTask("Task1","30","10:00");
        User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd");

        String date = "2023-01-01Sunday";

        System.out.println(user.getCalendar().getDays().get(date).getDate() );
        user.planifyman(task,date);

    }

    }

