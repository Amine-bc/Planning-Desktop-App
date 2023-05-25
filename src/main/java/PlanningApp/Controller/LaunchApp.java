package PlanningApp.Controller;

import PlanningApp.Model.*;
import PlanningApp.View.FirstPageView;


public class LaunchApp {

    public static void main(String[] args) {
        // FirstPageController.authen
        //LoginController loginController = new LoginController();

        //demo for planify auto here:

        String date ="2022-02-10Thursday";
        String date8 ="2022-02-11Friday";
        String date9="2022-02-12Saturday";
        String date2 ="2022-02-18Friday";
        String date3 ="2022-03-05Saturday";
        String date4 = "2021-02-14Sunday";

        User user = new User("Amine","password");
        user.createCalendar(2022,2023,2,10,10,30);
        Task task = new SimpleTask("task1","02:00",date,"10:00",0);

//      System.out.println("name"+task.getName()+"duration"+task.getDuration()+"priority"+task.getPriority()+"starttime"+task.getStarttime()+"endtime"+task.getEndtime());
        user.addtimeslot(date,"07:00","09:00" );
        user.addtimeslot(date,"10:00","12:00" );
        user.addtimeslot(date8,"10:00","12:00" );
        user.addtimeslot(date8,"14:00","16:00" );
        user.addtimeslot(date9,"10:00","12:00");
        user.addtimeslot(date2,"02:00","05:00");
        user.addtimeslot(date2,"10:00","11:00");

        Task task1 = new SimpleTask("task1","02:00",0,1);
        Task task2 = new SimpleTask("task2","02:00",0,0);
        user.getCalendar().getTaskstobeplanned().add(task1);
        user.getCalendar().getTaskstobeplanned().add(task2);
        user.planifyauto(date,date2);
        //Statistics.Createstats("2023-05-15Monday","2023-05-29Monday");


       // User.ShowCalendar();


        System.out.println("-------------------------------------------------------------------");
        System.out.println("---------------------* Projects*-----------------------------------");
        Project prj1=new Project("firstproject","this is my first project","0000");
        Project prj2=new Project("secondtproject","this is my second project","0001");
        Project prj3=new Project("thirdproject","this is my third project","0010");
        Project prj4=new Project("fourthproject","this is my fourth project","0011");
        user.getProfile().addProject(prj1);

        String[] startTimes = {"10:00", "11:30", "13:00", "14:30", "16:00", "17:30", "19:00", "20:30"};
        String[] durations = {"02:00", "01:30", "01:30", "01:30", "02:00", "01:30", "01:30", "02:00"};

        for (int i = 0; i < 8; i++) {
            String taskName = "task" + (i + 1);
            String duration = durations[i];
            String startTime = startTimes[i];
            int priority = 0;

            Task tasko = new SimpleTask(taskName, duration, date, startTime, priority);
            user.getProfile().getProjects().get(prj1.getName()).addTask(tasko);
        }

        user.getProfile().addProject(prj2);
        user.getProfile().addProject(prj3);
        user.getProfile().addProject(prj4);

        //user.getProfile().showProject();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("--------------------* History *--------------------------------------");
        user.addCalendartoHistory(user.getCalendar());
        user.setCalendar(null);

        user.ReadfromDbHistory("History"+user.getProfile().getname()+".ser");
        user.setCalendar(user.getHistorylist().get(0));
        User.ShowCalendar();



    }
}
