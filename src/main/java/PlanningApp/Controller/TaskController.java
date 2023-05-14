package PlanningApp.Controller;
import PlanningApp.Model.*;

public class TaskController {
    //create task and then plan it
    public void createTask(){
        User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd","5633");
        Task task = user.createTask("Task1","00:30","10:00");
        user.createCalendar(2021,2022,2,10,10,30);
        String date ="2021-02-11Thursday";
        user.getCalendar().getDays().get(date).printDay();
        user.addtimeslot(date,"02:00","04:25");
        user.addtimeslot(date,"00:00","10:00");
        user.getCalendar().getDays().get(date).printDay();

        //user.planifyman(task,date);

        // System.out.println(user.getCalendar().getDays().get(date).getTimeslot().get(0).getTask().getName() );


    }

    }

