package PlanningApp.Model;

import java.util.HashMap;
import java.util.HashSet;

public class Profile {
    // contains personel infos

    public Profile(String name, String surname, String email, String password, String Id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.Id = Id;
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
    private Badge badge ;
    private HashMap<String,Project> projects;
    private Statistics stats;
    private Calendar calendarhistory;

    public void addProject(Project project){
        projects.put(project.getName(),project);
    }
    public void addBadge(Badge badge){
        this.badge = badge;
    }
    public void addStats(Statistics stats){
        this.stats = stats;
    }
    public void addCalendar(Calendar calendar){
        this.calendarhistory = calendar;
    }

}
