package PlanningApp.Model;

public class SimpleTask extends Task{

    public SimpleTask(){
        super();
    };
    public SimpleTask(String name,String duration, String starttime){
        super(name,duration,starttime,0);
        // this constructor is used once someone clicks on new task
        // first we ask if he wants to auto plan it or manually
        // second we ask for its type decomp or simple
        // Then create an instance then
    }
    public SimpleTask(String name,String duration,int Priority){
        super(name,duration,Priority);
        // this constructor is used once someone clicks on new task
        // first we ask if he wants to auto plan it or manually
        // second we ask for its type decomp or simple
        // Then create an instance then
    }


    @Override
    public void planifyman(String time,String duration) {
    };

    @Override
    public void planifyauto(String startperiod, String endperiod) {


    }
    @Override
    public void postpone(String time) {
        //change the start time and end time
        if (Integer.parseInt(time) > Integer.parseInt(this.getStarttime())) {
            this.setStarttime(time);
        }else{
                System.out.println("Error in postponing down in simple task model");
            }
        }


    @Override
    public void replan(String time) {
        //change the start time and end time
        this.setStarttime(time);

    }
    @Override
    public void evaluate() {

    }

    // setter and getters for the attributes

}
