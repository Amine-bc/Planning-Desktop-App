package PlanningApp.Model;

public class TimeSlot implements Evaluation{

    private String start;
    private String state ;
    private String end;
    private String duration;
    private Task task;
    public TimeSlot(String start, String end, String duration){
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.state = "free";
    }

    public String checkstate(){
        return this.state;
    };
    public void addTask(Task task){
        this.task = task;
    };
    public void evaluate(){

    };

}
