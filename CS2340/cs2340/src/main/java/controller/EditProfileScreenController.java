package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.model.Profile;
import main.java.model.User;


/**
 * Created by Yash on 10/3/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class EditProfileScreenController {

    private User currentUser;

    private Main myApp;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipField;

    private Profile userProfile;

    public void setCurrentUser(User user) {
        currentUser = user;
        userProfile = currentUser.getProfile();
        emailField.setText(userProfile.get_email());
        phoneField.setText(userProfile.get_phoneNumber());
        streetField.setText(userProfile.get_streetAddress());
        cityField.setText(userProfile.get_city());
        stateField.setText(userProfile.get_state());
        zipField.setText(userProfile.get_zipcode());
    }

    public void setMainApp(Main mainApp) {myApp = mainApp;}

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        userProfile.set_email(emailField.getText());
        userProfile.set_phoneNumber(phoneField.getText());
        userProfile.set_streetAddress(streetField.getText());
        userProfile.set_city(cityField.getText());
        userProfile.set_state(stateField.getText());
        userProfile.set_zipcode(zipField.getText());
        currentUser.addProfile(userProfile);
        myApp.loadApplication(currentUser);
    }


}
