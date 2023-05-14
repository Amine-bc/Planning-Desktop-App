package PlanningApp.Model;
import java.util.TreeSet ;
public class DecompTask extends Task{

    private TreeSet<String> Setoftimeslots;

    String totalduration ;
    String totalstart ;
    String totalend ;
    public DecompTask(String name,String duration, String starttime,String endtime,String state){
        this.name = name;
        this.duration =duration ;
        this.totalstart =starttime;
        this.totalend = endtime ;
        this.totalduration =duration ;
        this.state = State.valueOf(state.toUpperCase())     ;


    }


    @Override
    public void planifyman(String time, String duration) {
        this.state = State.notRealized ;
        this.starttime =time;
        this.duration = duration ;

    }
    @Override
    public void planifyauto(String startperiod, String endperiod) {
        if (  Integer.toString((Integer.parseInt(startperiod) + Integer.parseInt(duration))).compareTo(endperiod) == 0 ){

            this.starttime =startperiod;
            this.duration = duration ;
            this.state = State.notRealized ;
        }else{
            System.out.println("Error in auto planification down in simple task model");
        }
    }
    @Override
    public void postpone(String time) {
        if (Integer.parseInt(time) > Integer.parseInt(starttime)){
            this.starttime =time;
            this.duration  = duration ;
        }
        else{
            System.out.println("Error in postponing down in simple task model");
        }
    }

    @Override
    public void replan(String time) {

        this.starttime =time;

    }
    @Override
    public void evaluate() {

    }



}
