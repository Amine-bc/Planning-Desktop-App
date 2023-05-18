package PlanningApp.Model;

import java.io.*;
import java.util.HashMap;

public class App {
    // this class is the main class of the model part of the application
    HashMap<String,User> users;
    public App(){
        this.users = new HashMap<String,User>();
    }
    public void addUser(User user){
        users.put(user.getname(),user);
    }
    public void removeUser(User user){
        users.remove(user.getname());
    }
    public static void main(String[] args) {
        // this method is the main method of the model part of the application
        // it is used to test the model classes
        App app = new App();
        User user = new User("Amine","Mimi");
        User user2 = new User("Hamid","mimi2");
        app.addUser(user);
        app.addUser(user2);
        app.SaveToDb("src/main/java/PlanningApp/Files/users.ser");
        app.users = null ;
        app.ReadfromDb("src/main/java/PlanningApp/Files/users.ser");
       System.out.println(app.users.get("Amine").profile.getpassword());

        }

    public void ReadfromDb(String FilePath)
    {
        try
        {
            FileInputStream fileOut2 = new FileInputStream(FilePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileOut2);
            // read the user object
            this.users = (HashMap<String,User>) objectIn.readObject();
            System.out.println("users list deserialized successfully.");

        }
        catch (Exception e)
        {
            System.out.println("Exception is caught");
        }


    }

    public void SaveToDb(String FilePath){
        try {
            FileOutputStream fileOut = new FileOutputStream(FilePath);
            ObjectOutputStream usersmap = new ObjectOutputStream(fileOut);
            usersmap.writeObject(users);
            System.out.println("users list serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
