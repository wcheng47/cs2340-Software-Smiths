package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 11/21/2016.
 */
public class BanUserController {
    private User currentUser;
    private Main myApp;

    @FXML
    private TextField userField;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @FXML
    private void handleBackPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleSubmitPressed() {
        String userId = userField.getText();
        ArrayList<User> userList = myApp.getUserList();
        int indexBan = 0;
        boolean toBan = false;
        for(int j = 0; j < userList.size() && !toBan; j++) {
            if(userList.get(j).getUserName().equals(userId)) {
                toBan = true;
                indexBan = j;
            }
        }
        if(toBan) {
            myApp.banUser(indexBan);
            myApp.writeSecurity("USER " + userId + " BANNED by admin" + currentUser.getUserName());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText(userId + " was successfully banned.");
            alert.showAndWait();
            myApp.loadApplication(currentUser);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("No such user exists.");
            alert.showAndWait();
        }
    }
}
