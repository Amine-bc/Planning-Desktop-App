package PlanningApp.Model;

interface TaskUser {

    public void planifyman(String time, String duration) ;
    public void planifyauto(String startperiod, String endperiod, String day);
    public void replan(String time);
    public void postpone(String time);
    public void evaluate();


}
