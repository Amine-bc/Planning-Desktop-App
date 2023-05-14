package PlanningApp.Model;

import java.util.ArrayList;

public class TimeSlot implements Evaluation,TimeCalcs{

    private String start;
    private String state ;
    private String end;
    private String duration;
    public TimeSlot(String start, String end){
        this.start = start;
        this.end = end;
        this.duration = subtract(this.end,this.start);
    }

    public String checkstate(){
        return this.state;
    };

    public void evaluate(){

    };

    public String getstart(){
        return this.start ;
    }

    public String getend(){
        return this.end ;
    }

    public void setend(String end){
        this.end = end ;
    }

    public void setstart(String start){
        this.start = start ;
    }

    public String getduration(){
        return this.duration ;
    }

    public void setduration(String getduration) {
        this.duration = getduration;
    }
}
