package PlanningApp.Model;

public abstract class Task implements TaskUser {

    String name ;
    State state ;
    String duration ;
    String starttime;
    String endtime ;


    public String getTaskname() {
        return this.name;
    }
}
