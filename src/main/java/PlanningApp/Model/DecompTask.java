package PlanningApp.Model;

public class DecompTask extends Task{

        public DecompTask(){
            super();
        };
        public DecompTask(String name,String duration, String starttime, String day, int repetition){
            super(name,duration,starttime,0,day,repetition);
            // this constructor is used once someone clicks on new task
            // first we ask if he wants to auto plan it or manually
            // second we ask for its type decomp or simple
            // Then create an instance then
        }
        public DecompTask(String name,String duration,int Priority, int repetition){
            super(name,duration,Priority,repetition);
            // this constructor is used once someone clicks on new task
            // first we ask if he wants to auto plan it or manually
            // second we ask for its type decomp or simple
            // Then create an instance then
        }

    @Override
    public boolean planifyman(String time, String duration) {
        return false;
    }

    @Override
    public boolean planifyauto(String startperiod, String endperiod) {
        return false;
    }

    @Override
    public void replan(String time) {

    }

    @Override
    public void postpone(String time) {

    }


    @Override
    public int evaluate(Object o) {
        return 0;
    }


}
