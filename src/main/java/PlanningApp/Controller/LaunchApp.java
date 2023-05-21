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

        User user = new User("Amine","password");
        user.createCalendar(2021,2022,2,10,10,30);
        Task task = new SimpleTask("task1","01:00",date,"03:00",0);
//        System.out.println("name"+task.getName()+"duration"+task.getDuration()+"priority"+task.getPriority()+"starttime"+task.getStarttime()+"endtime"+task.getEndtime());
        user.addtimeslot(date,"02:00","04:30");
        user.addtimeslot(date,"10:00","12:00");
        user.addtimeslot(date,"14:00","16:00");
        user.addtimeslot(date2,"02:00","05:00");
        user.addtimeslot(date2,"10:00","11:00");
        user.addtimeslot(date2,"14:00","16:00");
        user.addtimeslot(date3,"02:00","05:00");
        user.addtimeslot(date3,"10:00","11:00");
        user.addtimeslot(date3,"14:00","16:00");
//        user.planifyman(task,date2);
//        user.planifyman(task,date);
        System.out.println("Beforeplanifying");
        System.out.println("Day1"+date+"\n");
        user.getCalendar().getDays().get(date).printDay();
//        System.out.println("Day2"+date2+"\n");

        user.getCalendar().getDays().get(date2).printDay();
        user.planifyman(task,date);
        System.out.println("Afterplanifying");
        user.getCalendar().getDays().get(date).printDay();


//
//        User.currentcalendar.Createtasklist();
//        User.currentcalendar.getTaskstobeplanned().forEach(
//                k-> System.out.println(k.getName()+" "+k.getDuration()+" "+k.getPriority()+" "+k.getStarttime()+" "+k.getEndtime())
//        );
//        user.planifyauto(date,date3);
//        user.getCalendar().getDays().get(date).printDay();
//        System.out.println("Afterplanifying");
//        System.out.println("\n\n\n\n\n\n");
//        App.ShowCalendar();
    }
}
