package PlanningApp.Controller;

import PlanningApp.Model.User;

import java.io.*;

public class AppController {
    protected static User currentuser ;
    protected static User getcurrentuser(){
        return currentuser;
    }
    protected static void setcurrentuser(User user){
        currentuser = user;
    }
    protected static String FilePath ;
    protected static void setFilePath(String path){
        FilePath = path;
    }
    protected static String getFilePath(){
        return FilePath;
    }
    protected void AppController(){
        // Do I need it ??
    }

    public static void main(String[] args) {
        //check if the file is empty or not
        //if not empty then load login controller
        File file = new File(AppController.FilePath);
        if (file.exists() && file.length() == 0) {
            System.out.println("File is empty.");
            //TODO Ask for registration
            // after registrations add the user to the arraylist in app
            // and do this
            // AppController.setcurrentuser(user);
        } else {
            System.out.println("File is not empty.");
            //TODO Read the users Arraylist from the file
            // load login controller after login happens do this
            // AppController.setcurrentuser(user);
            // App.setCurrentuser(user);
        }

    }




}