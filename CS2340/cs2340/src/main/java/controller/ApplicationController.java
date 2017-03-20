package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.java.model.User;

/**
 * Created by chitramahajani on 9/20/16.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class ApplicationController {

    private Main myApp;

    private static User currentUser;

    @FXML
    private Label applicationMessage;

    @FXML
    private Button createButton;

    @FXML
    private Button editButton;

    @FXML
    private Button viewMapButton;

    @FXML
    private Button submitSourceReportButton;

    @FXML
    private Button submitPurityReportButton;

    @FXML
    private Button viewHistoryGraphButton;

    @FXML
    private Button viewReportsButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button banUserButton;

    @FXML
    private Button unblockUserButton;

    @FXML
    private Button deleteReportButton;

    /**
     * A method that sets the Main app for referencing database objects
     * @param mainApp the Main object that determines what the app being used is
     */
    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleLogoutPressed() {
        myApp.loadWelcome();
    }

    /**
     * Sets the app's current user and modified the UI depending on what the
     * user can and can't do
     * @param user the user that will become the current user
     */
    @FXML
    public void setCurrentUser(User user) {
        currentUser = user;
        User.Type userType = currentUser.getType();


        if (userType == User.Type.USER || userType == User.Type.ADMIN) {
            submitPurityReportButton.setDisable(true);
        }
        if (userType == User.Type.MANAGER){
            //viewReportsButton.setDisable(false);
            viewHistoryGraphButton.setDisable(false);
            deleteReportButton.setDisable(false);
        } else {
            //viewReportsButton.setDisable(true);
            viewHistoryGraphButton.setDisable(true);
            deleteReportButton.setDisable(true);
        }
        if (userType == User.Type.ADMIN) {
            deleteUserButton.setDisable(false);
            banUserButton.setDisable(false);
            unblockUserButton.setDisable(false);
        } else {
            deleteUserButton.setDisable(true);
            banUserButton.setDisable(true);
            unblockUserButton.setDisable(true);
        }
        applicationMessage.setText("Welcome " + currentUser.getFirstName());
        if (currentUser.hasProfile()) {
            createButton.setDisable(true);
        } else {
            editButton.setDisable(true);
        }
        if (myApp.getSourceReportList() == null || myApp.getSourceReportList().size() == 0) {
            viewMapButton.setDisable(true);
        }
        if(currentUser.getBanned().equals("TRUE")) {
            submitSourceReportButton.setDisable(true);
            submitPurityReportButton.setDisable(true);
        }
        // For future implementation
        //if (currentUser.getType() == User.Type.WORKER) {
        // show water purity report button
        // button.setVisible(true)
        //} else {
        // make the button INVISIBLE
        // button.setVisible(false)
        //}
    }

    /**
     * Gets the app's current user.
     * @return User the user that is currently using the app
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    @FXML
    private void handleEditPressed() {
        if(currentUser.getProfile() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Profile to Edit");
            alert.setContentText("You have to make a profile to edit it. Click 'Create Profile' to make a profile.");
            alert.showAndWait();
        } else {
            myApp.loadEditProfile(currentUser);
        }
    }

    @FXML
    private void handleSubmitSourcePressed() {
        myApp.loadSourceReportPage(currentUser);
    }

    @FXML
    private void handleViewReportsPressed() {
        myApp.loadViewSourceReport(currentUser);
    }

    /*@FXML
    private void handleViewPurityReportsPressed() {
        myApp.loadViewPurityReport(currentUser);
    }*/

    @FXML
    private void handleSubmitPurityReportPressed() {
        myApp.loadWaterReport(currentUser);
    }

    @FXML
    private void handleViewMapPressed() {
        myApp.loadMap();
    }

    @FXML
    private void handleCreatePressed() { myApp.loadCreateProfile(currentUser);}

    @FXML
    private void handleHistoryGraphPressed() {
        myApp.loadHistoryGraph(currentUser);
    }

    @FXML
    private void handleDeleteUserPressed() {
        myApp.loadDeleteUser(currentUser);
    }

    @FXML
    private void handleBanUserPressed() {
        myApp.loadBanUser(currentUser);
    }

    @FXML
    private void handleUnblockPressed() { myApp.loadUnblockUser(currentUser); }

    @FXML
    private void handleDeleteReportPressed() { myApp.loadDeleteReport(currentUser); }

}