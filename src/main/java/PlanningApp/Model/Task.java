package PlanningApp.Model;

public abstract class Task implements TaskUser {

    String name ;
    State state ;
    String duration ;
    String starttime;



    // setters and getters
    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getName() {
        return name;
    }
    public State getState() {
        return state;
    }
    public String getDuration() {
        return duration;
    }
    public String getStarttime() {
        return starttime;
    }

}
