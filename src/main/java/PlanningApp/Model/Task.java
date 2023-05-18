package PlanningApp.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Task implements TaskUser, TimeCalcs {

    public Task(){}; // default constructor
    public Task(String name, String duration, String starttime) {
        this.name = name;
        this.duration = duration;
        this.starttime = starttime;
        this.endtime = add(starttime, duration) ;
        this.state = State.notRealized;

    }
    private String name ;
    private State state ;
    private String duration ;
    private String starttime;
    private String endtime;

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEndtime() {
        return endtime;
    }

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
