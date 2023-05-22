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
        String date ="2021-02-11Thursday";
        String date2 ="2021-02-12Friday";
        String date3 ="2021-02-13Saturday";
        String date4 = "2021-02-14Sunday";

        User user = new User("Amine","password");
        user.createCalendar(2021,2022,2,10,10,30);
        Task task = new SimpleTask("task1","02:00",date,"10:00",0);

//      System.out.println("name"+task.getName()+"duration"+task.getDuration()+"priority"+task.getPriority()+"starttime"+task.getStarttime()+"endtime"+task.getEndtime());
        user.addtimeslot(date,"07:00","08:00" );
        user.addtimeslot(date,"10:00","12:00" );
        user.addtimeslot(date,"14:00","16:00" );
        user.addtimeslot(date2,"02:00","05:00");
        user.addtimeslot(date2,"10:00","11:00");
        user.addtimeslot(date2,"14:00","16:00");
        user.addtimeslot(date3,"02:00","05:00");
        user.addtimeslot(date3,"10:00","11:00");
        user.addtimeslot(date3,"14:00","16:00");


        user.planifyman(task,date);
        System.out.println("time slots");

        App.ShowCalendar();
    }
}
