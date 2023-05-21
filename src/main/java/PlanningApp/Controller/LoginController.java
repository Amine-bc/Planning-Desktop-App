package PlanningApp.Controller;

import PlanningApp.Model.App;
import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import PlanningApp.View.FirstPageView;
import PlanningApp.View.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController  {

    @FXML
    private BorderPane firstPage;

    //@FXML
    //private BorderPane Periode;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String USERS_FILE = "src/main/java/PlanningApp/Files/users.txt";
    private static final String USERS_FILEpath = Util.getPlatformIndependentFilePath();




    @FXML
    private void login() throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            System.out.println(App.users.isEmpty());
            for (String key : App.users.keySet()) {
                System.out.println("Key: " + key );
            }
            AppController.currentuser = App.users.get(username);
            App.setCurrentuser(App.users.get(username));
            System.out.println("user form DB"+AppController.currentuser.getname());
            AppController.currentcalendar= AppController.currentuser.getCalendar();
            //TODO nothing

            System.out.println("Correct");
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            //TODO load user object from memory


            Stage stage = (Stage) firstPage.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/Calendar.fxml"));

            Parent root = loader.load();
            Stage stage1=new Stage();
            stage1.setTitle("Calendar Application");
            stage1.setScene(new Scene(root));
            stage1.show();


        } else {
            System.out.println("false");
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }

    }
    @FXML
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User(username,password);
        user.createCalendar(2021,2022,2,10,10,30);

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username and password are required.");
            return;
        }
        if (userExists(username)) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username already exists.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            writer.flush();
            App.init();
            App.users.put(username,user);

            for (String key : App.users.keySet()) {

                System.out.println("Key: " + key );
            }

            AppController.currentuser=user;
            App.SaveToDb(USERS_FILEpath);
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "User registered successfully.");
            System.out.println("APP user"+AppController.currentuser.getname());
            App.users = null ;
            App.ReadfromDb("src/main/java/PlanningApp/Files/users.ser");
            System.out.println("after");
            for (String key : App.users.keySet()) {

                System.out.println("Key: " + key );
            }

            Stage stage = (Stage) firstPage.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlanningApp/View/PeriodePage.fxml"));

            Parent root = loader.load();
            Stage stage1=new Stage();
            stage1.setTitle("periode");
            stage1.setScene(new Scene(root));
            stage1.show();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Failed to register user.");
        }

    }
    public static void writeListToFile(ArrayList<User> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> readListFromFile(String filePath) {
        ArrayList<User> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            list = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Failed to read user data.");
        }
        return false;
    }

    private boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Failed to read user data.");
        }
        return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
