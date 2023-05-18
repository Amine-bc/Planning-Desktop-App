package PlanningApp.Controller;

import PlanningApp.Model.Task;
import PlanningApp.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String USERS_FILE = "src/main/java/PlanningApp/Files/users.txt";
    private static final String USERS_FILEdat = "src/main/java/PlanningApp/Files/user.dat";

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (validateLogin(username, password)) {
            System.out.println("Correct");
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            //TODO load user object from memory
        } else {
            System.out.println("false");
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    @FXML
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = new User(username,"Bouchoucha","lm_bouchoucha@esi.dz",password);
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
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "User registered successfully.");
            //Call writeListToFile
            ArrayList<User> list = new ArrayList<>();
            list.add(user);
            writeListToFile(list,USERS_FILEdat);
            // Call readListFromFile
            ArrayList<User> list2 = readListFromFile(USERS_FILEdat);
            System.out.println(list2.size());
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
