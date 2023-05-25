package PlanningApp.Controller;


import PlanningApp.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;


public class AutoTask {

    //periode page

    @FXML
    private BorderPane Periode;

    @FXML
    private BorderPane Pes;

    @FXML
    private BorderPane hero;


    @FXML
    private ChoiceBox<String> startingDayChoiceBox;

    @FXML
    private ChoiceBox<String> startingMonthChoiceBox;

    @FXML
    private ChoiceBox<String> startingYearChoiceBox;

    @FXML
    private ChoiceBox<String> endingDayChoiceBox;

    @FXML
    private ChoiceBox<String> endingMonthChoiceBox;

    @FXML
    private ChoiceBox<String> endingYearChoiceBox;


    //variables:
    static int  startingDay;
    static int  startingMonth;
    static int  startingYear;
    static int  endingDay;
    static int  endingMonth;
    static int  endingYear;


    //Task count page :
    @FXML
    private TextField TaskNum;






    //loop page
        @FXML
        private Button PerBut;

        @FXML
        private Button button;

        private  static int reloadCount ;

        private static int numnum;

        @FXML
        public void PerTask() throws IOException {
         try{
            //lazem ykounou static , wel nchoufou kifeh nreturniwhom mconcatinyin b virgule wela
             startingDay = Integer.parseInt(startingDayChoiceBox.getValue());
             startingMonth = Integer.parseInt(startingMonthChoiceBox.getValue());
             startingYear = Integer.parseInt(startingYearChoiceBox.getValue());
             endingDay = Integer.parseInt(endingDayChoiceBox.getValue());
             endingMonth = Integer.parseInt(endingMonthChoiceBox.getValue());
             endingYear = Integer.parseInt(endingYearChoiceBox.getValue());
        }catch (Exception e){
        System.out.println("i'm here");
    }



            Stage stage = (Stage) Periode.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/TaskCount.fxml"));

            Parent root = loader.load();
            Stage stage1=new Stage();
            stage1.setTitle("Calendar Application");
            stage1.setScene(new Scene(root));
            stage1.show();



        }




        @FXML
        public void NumTask() throws IOException{

            try {
                numnum = Integer.parseInt(TaskNum.getText());
                reloadCount=numnum-1;
            }catch (Exception e){
                System.out.println("i'm here");
            }
            Stage stage = (Stage) Pes.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/AutoTask.fxml"));

            Parent root = loader.load();
            Stage stage1=new Stage();
            stage1.setTitle("Calendar Application");
            stage1.setScene(new Scene(root));
            stage1.show();

        }


        @FXML
        public void LoopTask() throws IOException{
            System.out.println("numnum = " + numnum);
            System.out.println("reloadCount = " + reloadCount);

            String name ="hero";
            String duration ="02:00";
            int priority =0;
            int repetition = 1;
            String decomposable = "NO";
            //if (decomposable.equals("yes")) {
            //  Task task = new DecompTask(name,duration,priority,repetition);
            // AppController.currentuser.getCalendar().getTaskstobeplanned().add(task);
            //}else{
            Task task = new SimpleTask(name,duration,priority, repetition);
            System.out.println("ref ="+AppController.currentuser.getCalendar());
            AppController.currentuser.getCalendar().getTaskstobeplanned().add(task);

                if (reloadCount > 0) {
                    try {



                            //}
                        Collections.sort(AppController.currentuser.getCalendar().getTaskstobeplanned());
                        AutoTask.reloadCount--;
                        Stage stage = (Stage) hero.getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/AutoTask.fxml"));

                        Parent root = loader.load();
                        Stage stage1=new Stage();
                        stage1.setTitle("Task");
                        stage1.setScene(new Scene(root));
                        stage1.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{

                    System.out.println("------------------------------------------------------");
                    System.out.println("rani wsselt");
                    User.currentcalendar=AppController.currentcalendar;
                    System.out.println("hna ni hna");
                    for (Task t:AppController.currentcalendar.getTaskstobeplanned()){
                        System.out.println("the taskto add is heree ::"+t.getName());
                    }

                    System.out.println("after tasks");
                    User.currentcalendar.planifyauto("2023-05-15Monday","2023-05-29Monday");
                    AppController.currentcalendar=User.currentcalendar;
                        //AppController.currentuser.planifyauto("2023-05-15Monday","2023-05-29Monday");
                        //AppController.currentcalendar=App.currentuser.getCalendar();
                        //App.ShowCalendar();



                    Stage stage = (Stage) hero.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/Calendar.fxml"));
                    Parent root = loader.load();
                    Stage stage1=new Stage();
                    stage1.setTitle("Calendar");
                    stage1.setScene(new Scene(root));
                    stage1.show();

                }

        }


}
