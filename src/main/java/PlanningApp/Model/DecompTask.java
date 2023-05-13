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
        this.state = State.SCHEDULED ;
        this.starttime =time;
        this.endtime = Integer.toString(Integer.parseInt(time) + Integer.parseInt(duration)) ;

    }
    @Override
    public void planifyauto(String startperiod, String endperiod, String day) {
        if (  Integer.toString((Integer.parseInt(startperiod) + Integer.parseInt(duration))).compareTo(endperiod) == 0 ){

            this.starttime =startperiod;
            this.endtime =endperiod ;
            this.state = State.SCHEDULED ;
        }else{
            System.out.println("Error in auto planification down in simple task model");
        }
    }
    @Override
    public void postpone(String time) {
        if (Integer.parseInt(time) > Integer.parseInt(starttime)){
            this.starttime =time;
            this.endtime = Integer.toString(Integer.parseInt(time) + Integer.parseInt(duration)) ;}
        else{
            System.out.println("Error in postponing down in simple task model");
        }
    }

    @Override
    public void replan(String time) {

        this.starttime =time;
        this.endtime = Integer.toString(Integer.parseInt(time) + Integer.parseInt(duration)) ;

    }
    @Override
    public void evaluate() {

    }



}
