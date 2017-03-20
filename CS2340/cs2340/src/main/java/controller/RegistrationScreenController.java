package main.java.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.model.AlertMessage;
import main.java.model.User;

/**
 * Created by Yash on 9/28/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class RegistrationScreenController {

    private Main myApp;

    @FXML
    private TextField firstField;

    @FXML
    private TextField lastField;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private ComboBox<String> typeBox;

    private ObservableList<String> typeList = FXCollections.observableArrayList("User", "Worker", "Manager", "Admin");

    @FXML
    private void initialize() {
        typeBox.setItems(typeList);
        typeBox.setValue("User");
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleRegisterSubmitPressed() {
        String firstName = firstField.getText();
        String lastName = lastField.getText();
        String userName = userField.getText();
        try {
            checkValidName(firstName, lastName);
            User newUser = new User(firstName, lastName, userName,
                    passField.getText(), typeBox.getValue(), "FALSE", "0");
            boolean proceed = myApp.addUser(newUser);
            if(proceed) {
                myApp.loadApplication(newUser);
            } else {
                myApp.loadRegister();
            }
        } catch (Exception e) {
            AlertMessage.sendMessage("Invalid Name", "Your first and last " +
                    "name can only contain letters");
        }
    }

    public void checkValidName(String firstName, String lastName) throws Exception {
        String validNameRegex = "^[a-zA-Z]+$";
        if (!firstName.matches(validNameRegex)
                || !lastName.matches(validNameRegex)) {
            throw new Exception();
        }
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadWelcome();
    }



}
