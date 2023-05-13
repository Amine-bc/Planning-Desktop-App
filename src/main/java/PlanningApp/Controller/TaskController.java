package PlanningApp.Controller;
import PlanningApp.Model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import PlanningApp.Model.DayMap.*;
public class TaskController {
    //create task and then plan it
    public void createTask(){

        SimpleTask task = new SimpleTask("Task1","30","10:00","Scheduled");
        User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd","5633");

        String str = "2023-01-01Sunday";

        System.out.println(user.getCalendar().getDays().get(str).getDate() );

        }
        //user.planifyman(task,str);

    }

