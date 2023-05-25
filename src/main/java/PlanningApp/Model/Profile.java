package PlanningApp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Profile implements Serializable {
    // contains personel infos

    public Profile(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.badge = null;
        this.projects = null;
        this.stats = null;
        this.calendarhistory = null;
    }
    private String name;
    private String surname;
    private String email;
    private String password;
    private String Id;
    private ArrayList<Badge> badge ;
    private HashMap<String,Project> projects=new HashMap<>();
    private Statistics stats;
    private Calendar calendarhistory;

    public Profile(){
    }

    public ArrayList<Badge> getBadge() {
        return badge;
    }

    public void addProject(Project project){
        projects.put(project.getName(),project);
    }
    public void addBadge(Badge badge){
        this.badge.add( badge);
    }
    public void addStats(Statistics stats){
        this.stats = stats;
    }
    public void addCalendar(Calendar calendar){
        this.calendarhistory = calendar;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    public String getpassword() {
        return password;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public void showProject() {
        for (String key : projects.keySet()) {
            System.out.println("\n________________________________________________________________ \n");
            System.out.println("Project Name: " + projects.get(key).getName());
            System.out.println("Project Description: " + projects.get(key).getDescription());
            projects.get(key).showTasks();
            System.out.println("\n________________________________________________________________ \n");
        }

    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    public void setProjects(HashMap<String, Project> projects) {
        this.projects = projects;
    }
}
