package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 11/22/2016.
 */
public class UnblockUserController {
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
        int indexUnblock = 0;
        boolean toUnblock = false;
        for(int j = 0; j < userList.size() && !toUnblock; j++) {
            if(userList.get(j).getUserName().equals(userId)) {
                toUnblock = true;
                indexUnblock = j;
            }
        }
        if(toUnblock) {
            myApp.resetLog(indexUnblock);
            myApp.writeSecurity("USER " + userId + " UNBLOCKED by admin " + currentUser.getUserName());
            myApp.loadApplication(currentUser);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("No such user exists.");
            alert.showAndWait();
        }
    }
}
