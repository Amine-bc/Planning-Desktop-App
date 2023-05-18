package PlanningApp.Model;

import java.io.Serializable;
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
    private Badge badge ;
    private HashMap<String,Project> projects;
    private Statistics stats;
    private Calendar calendarhistory;

    public Profile(){
    }

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
}
