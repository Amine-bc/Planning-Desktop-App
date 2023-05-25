package PlanningApp.Controller;

import PlanningApp.Model.*;
import PlanningApp.View.FirstPageView;
import java.util.Scanner;

public class LaunchApp2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = new User(name, password);


        System.out.println("Calendar Creation:");
        System.out.print("Enter start year: ");

        int startYear = scanner.nextInt();

        System.out.print("Enter end year: ");
        int endYear = scanner.nextInt();

        System.out.print("Enter start month: ");
        int startMonth = scanner.nextInt();

        System.out.print("Enter end month: ");
        int endMonth = scanner.nextInt();

        System.out.print("Enter start day: ");
        int startDay = scanner.nextInt();

        System.out.print("Enter end day: ");
        int endDay = scanner.nextInt();


        user.createCalendar(startYear, endYear, startMonth, endMonth, startDay, endDay);

        scanner.nextLine(); // Consume newline character

        System.out.println("Enter task details:");
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter task duration: ");
        String taskDuration = scanner.nextLine();

        System.out.print("Enter task start date (yyyy-MM-dd): ");
        String taskStartDate = scanner.nextLine();

        System.out.print("Enter task start time (HH:mm): ");
        String taskStartTime = scanner.nextLine();

        System.out.print("Enter task priority: ");
        int taskPriority = scanner.nextInt();

        Task task = new SimpleTask(taskName, taskDuration, taskStartDate, taskStartTime, taskPriority);

        user.getCalendar().getTaskstobeplanned().add(task);

        System.out.print("Enter start date for planification (yyyy-MM-dd): ");
        String planifyStartDate = scanner.nextLine();

        System.out.print("Enter end date for planification (yyyy-MM-dd): ");
        String planifyEndDate = scanner.nextLine();

        user.planifyauto(planifyStartDate, planifyEndDate);

        Statistics.Createstats(planifyStartDate, planifyEndDate);

        User.ShowCalendar();

        scanner.close();
    }
}

