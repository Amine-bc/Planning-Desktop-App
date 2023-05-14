package PlanningApp.Controller;

import PlanningApp.Model.Calendar;
import PlanningApp.View.FirstPageView;


public class LaunchApp {

        public static void main(String[] args) {
            //test

            TaskController taskController = new TaskController();
            taskController.createTask();
            FirstPageView page = new FirstPageView();

        }
}
