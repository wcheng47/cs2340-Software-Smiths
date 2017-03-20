package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.model.AlertMessage;
import main.java.model.Profile;
import main.java.model.User;

/**
 * Created by Yash on 10/3/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class CreateProfileScreenController {
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


    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {myApp = mainApp;}

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        String email = emailField.getText();
        String phone = phoneField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipField.getText();
        try {
            isValidProfile(email, phone, street, city, state, zip);
            Profile profile = new Profile(emailField.getText(), phoneField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
            currentUser.addProfile(profile);
            myApp.loadApplication(currentUser);
        } catch(Exception e) {
            AlertMessage.sendMessage("Invalid Profile", "You have not entered valid information for your profile.");
        }

    }

    public void isValidProfile(String email, String phoneNumber, String streetAddress, String city, String state, String zipCode) throws Exception {
        String isValidEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String isValidPhone = "^\\\\(?([0-9]{3})\\\\)?[-.\\\\s]?([0-9]{3})[-.\\\\s]?([0-9]{4})$";
        String isValidStreet = "^[0-9a-zA-Z\\.]+$";
        String isValidCity = "^[a-zA-Z]+$";
        String isValidState = "^[a-zA-Z]+$";
        String isValidZip = "\\d{5}";
        if(!email.matches(isValidEmail) || !phoneNumber.matches(isValidPhone) || !streetAddress.matches(isValidStreet)
                || !city.matches(isValidCity) || !state.matches(isValidState) || !zipCode.matches(isValidZip)) {
            throw new Exception("Invalid Profile Input");
        }


    }
}
