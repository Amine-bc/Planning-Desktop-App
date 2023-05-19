package PlanningApp.Model;

public class DecompTask extends Task{

        public DecompTask(){
            super();
        };
        public DecompTask(String name,String duration, String starttime){
            super(name,duration,starttime,0);
            // this constructor is used once someone clicks on new task
            // first we ask if he wants to auto plan it or manually
            // second we ask for its type decomp or simple
            // Then create an instance then
        }
        public DecompTask(String name,String duration,int Priority){
            super(name,duration,Priority);
            // this constructor is used once someone clicks on new task
            // first we ask if he wants to auto plan it or manually
            // second we ask for its type decomp or simple
            // Then create an instance then
        }

    @Override
    public void planifyman(String time, String duration) {

    }

    @Override
    public void planifyauto(String startperiod, String endperiod) {

    }

    @Override
    public void replan(String time) {

    }

    @Override
    public void postpone(String time) {

    }

    @Override
    public void evaluate() {

    }
}
