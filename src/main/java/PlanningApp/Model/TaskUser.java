package PlanningApp.Model;

interface TaskUser {

    public boolean planifyman(String time, String duration) ;

    public boolean planifyauto(String startperiod, String endperiod);

    public void replan(String time);
    public void postpone(String time);

    int evaluate(Object o);
}
