package PlanningApp.Controller;

import PlanningApp.Model.App;
import PlanningApp.Model.SimpleTask;
import PlanningApp.Model.User;
import PlanningApp.View.FirstPageView;
import  PlanningApp.Model.Task;


public class LaunchApp {

    public static void main(String[] args) {
        // FirstPageController.authen
        //LoginController loginController = new LoginController();

        //demo for planify auto here:

        String date ="2023-05-15Monday";
        String date2 ="2023-05-29Monday";
        String date3 ="2021-02-13Saturday";
        String date4 = "2021-02-14Sunday";

        User user = new User("Amine","password");
        user.createCalendar(2022,2023,2,10,10,30);
        Task task = new SimpleTask("task1","02:00",date,"10:00",0);

//      System.out.println("name"+task.getName()+"duration"+task.getDuration()+"priority"+task.getPriority()+"starttime"+task.getStarttime()+"endtime"+task.getEndtime());
        user.addtimeslot(date,"07:00","09:00" );
        user.addtimeslot(date,"10:00","12:00" );
        user.addtimeslot(date2,"02:00","05:00");
        user.addtimeslot(date2,"10:00","11:00");

        Task task1 = new SimpleTask("task1","02:00",0,0);
        Task task2 = new SimpleTask("task2","02:00",0,0);
        user.getCalendar().getTaskstobeplanned().add(task1);
        user.getCalendar().getTaskstobeplanned().add(task2);
        user.planifyauto(date,date2);


        App.ShowCalendar();
    }
}
