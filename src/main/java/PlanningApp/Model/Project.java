package PlanningApp.Model;

import java.util.HashSet;

public class Project {

    private String name;
    private String description;
    private HashSet<Task> tasks;
    private State state ;

    public Project(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.tasks = new HashSet<Task>();
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public void removeTask(Task task){
        tasks.remove(task);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public HashSet<Task> getTasks(){
        return this.tasks;
    }

    public void showTasks() {
        System.out.println("Tasks for Project: " + this.name);
        for (Task task : tasks) {
            System.out.println("Task Name: " + task.getName());
            System.out.println("Task Duration: " + task.getDuration());
            System.out.println("Task Priority: " + task.getPriority());
            System.out.println("Task Start Time: " + task.getStarttime());
            System.out.println("Task End Time: " + task.getEndtime());
            System.out.println("-----------------------------");
        }
    }



}
