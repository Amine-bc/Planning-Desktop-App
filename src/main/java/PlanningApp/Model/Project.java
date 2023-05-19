package PlanningApp.Model;

import java.util.HashSet;

public class Project implements Evaluation {

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


    @Override
    public int evaluate(Object evaluatable) {
        return 0 ;
    }
}
