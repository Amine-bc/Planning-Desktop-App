package PlanningApp.Controller;

import PlanningApp.Model.User;
import PlanningApp.View.FirstPageView;
import  PlanningApp.Model.Task;


public class LaunchApp {

        public static void main(String[] args) {
            // FirstPageController.authen

            //After login and signup
            User user = new User("Amine","Bouchoucha","lm_bouchoucha@esi.dz","passwd","5633");
            Task task = user.createTask("Task1","02:00","10:00");

            // just to test here it will be edited
            user.createTask("Task1","02:00","10:00");
            user.createCalendar(2021,2022,2,10,10,30);
            String date ="2021-02-11Thursday";
            user.addtimeslot("2021-02-11Thursday","02:00","04:25");
            user.addtimeslot("2021-02-11Thursday","10:00","12:00");
            user.getCalendar().getDays().get(date).printDay();
            user.removetimeslot("2021-02-11Thursday","02:00","04:25");
            user.getCalendar().getDays().get(date).printDay();
            user.planifyman(task,date);
            user.getCalendar().getDays().get(date).printDay();
        }
}
