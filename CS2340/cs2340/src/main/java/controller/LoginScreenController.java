package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 9/20/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class LoginScreenController {
    private User currentUser;
    private User toBlock;
    private Main myApp;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML Button submitButton;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleHomePressed() {
        myApp.loadWelcome();
    }

    @FXML
    private void handleSubmitPressed() {
        boolean loadApp;
        boolean isBlocked;
        boolean isBanned;
        currentUser = new User(null, null, null, null, User.Type.USER.toString(), "FALSE", "0");
        String username = userField.getText();
        String password = passField.getText();
        ArrayList<User> userList = myApp.getUserList();
        loadApp = isValidLogin(username, password, userList);
        isBlocked = Integer.parseInt(currentUser.getLogCount()) == 3;
        isBanned = currentUser.getBanned().equals("TRUE");
        if (loadApp) {
            myApp.loadApplication(currentUser);
        } else if(!loadApp && isBanned) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User Banned");
            alert.setContentText("You've been banned. You cannot submit reports, but you can still view them.");
            alert.showAndWait();
            myApp.loadApplication(currentUser);
        } else if(!loadApp && !isBanned && isBlocked) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User Blocked");
            alert.setContentText("You failed to login too many times, you must request an admin to unblock your account.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Credentials Error");
            alert.setContentText("No user with these credentials exists. Please reenter your information, or if you have no made an account, click 'Sign Up'.");
            alert.showAndWait();
        }
    }

    /**
     * tests to make sure the user is valid
     * @param username the username you're checking against the list
     * @param password the password you're checking against the list
     * @param userList the List of Users from the database
     * @return whether the login is valid or not.
     */
    public boolean isValidLogin(String username, String password, ArrayList<User> userList) {
        boolean loadApp = false;
        boolean userNotPass = false;
        String logUser = username;
        String toLog = "";
        for(int j = 0; j < userList.size() && !loadApp; j++) {
            User user = userList.get(j);
            toLog = "LOGIN ATTEMPT ";
            boolean isBanned = user.getBanned().equals("TRUE");
            boolean isBlocked = Integer.parseInt(user.getLogCount()) == 3;
            if (user.getUserName().equals(username)) {
                if (isBanned) {
                    toLog += "by " + username + ": Failure, User Banned";
                    loadApp = false;
                    j = userList.size();
                    currentUser = user;
                } else if (!isBanned && isBlocked) {
                    toLog += "by " + username + ": Failure, User Blocked due to Excessive Login Attempts";
                    loadApp = false;
                    j = userList.size();
                    currentUser = user;
                } else if (!isBanned && !isBlocked && !(user.getPassword().equals(password))) {
                    toLog += "by " + username + ": Failure, Bad Password";
                    currentUser = user;
                    myApp.incrementLog(j);
                    loadApp = false;
                    j = userList.size();
                } else {
                    toLog += "by " + username + ": Success";
                    loadApp = true;
                    currentUser = user;
                    myApp.resetLog(j);
                }
            } else {
                toLog += "Unknown Username";
                loadApp = false;
            }
        }
        myApp.writeSecurity(toLog);
        return loadApp;
            //System.out.println(j + " " + user.getUserName().equals(username));
            /*if(user.getUserName().equals(username) && user.getPassword().equals(password)) {
                if(user.getBanned().equals("TRUE")) {
                    currentUser = user;
                    toLog += "by " + username + ": Failure, User Banned";
                    loadApp = false;
                    j = userList.size();

                } else {
                    loadApp = true;
                    currentUser = user;
                    toLog += "by " + username + ": Success";
                }
            } else if(user.getUserName().equals(username) && !(user.getPassword().equals(password))){
                currentUser = user;
                currentUser.incrementLogCount();
                j = userList.size();
                loadApp = false;
                userNotPass = true;
            } else {
                System.out.println(j + " Incorrect pass and user");
                loadApp = false;
                toLog += "Unknown Username";
            }
        }
        if(userNotPass && !currentUser.getBanned().equals("TRUE")) {
            toLog = "LOGIN ATTEMPT by " + username + ": Failure, bad password";
        }*/
    }



    @FXML
    private void handleSignUpPressed() {
        myApp.loadRegister();
    }
}
