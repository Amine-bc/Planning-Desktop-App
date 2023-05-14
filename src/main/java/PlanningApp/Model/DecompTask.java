package PlanningApp.Model;
//import java.util.TreeSet ;
//public class DecompTask extends Task{
//
//    private TreeSet<String> Setoftimeslots;
//
//    String totalduration ;
//    String totalstart ;
//    String totalend ;
//    public DecompTask(String name,String duration, String starttime,String endtime,String state){
//        this.setName( name);
//        this.setDuration(duration) ;
//        this.totalstart =starttime;
//        this.totalend = endtime ;
//        this.totalduration =duration ;
//        this.setState(State.valueOf(state.toUpperCase()))     ;
//
//
//    }
//
//
//    @Override
//    public void planifyman(String time, String duration) {
//        this.setState(State.notRealized) ;
//        this.setStarttime(time);
//        this.setDuration(duration) ;
//
//    }
//    @Override
//    public void planifyauto(String startperiod, String endperiod) {
//
//    }
//    @Override
//    public void postpone(String time) {
//        if (Integer.parseInt(time) > Integer.parseInt(this.getStarttime())){
//            this.setStarttime(time);
//        }
//        else{
//            System.out.println("Error in postponing down in simple task model");
//        }
//    }
//
//    @Override
//    public void replan(String time) {
//
//        this.setStarttime(time);
//
//    }
//    @Override
//    public void evaluate() {
//
//    }
//
//
//
//}
