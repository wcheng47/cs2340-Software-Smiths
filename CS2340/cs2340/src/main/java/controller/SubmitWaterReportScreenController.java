package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.model.AlertMessage;
import main.java.model.PurityReport;
import main.java.model.User;

import java.util.Date;
import java.util.Random;



/**
 * Created by chitramahajani on 10/25/16.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class SubmitWaterReportScreenController {

    @FXML
    private ComboBox<String> conditionValue;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField virusField;

    @FXML
    private TextField contaminantField;

    private User currentUser;

    private Main myApp;

    private ObservableList<String> myConditions = FXCollections
            .observableArrayList("Safe", "Treatable", "Unsafe");


    @FXML
    private void initialize() {
        conditionValue.setItems(myConditions);
        conditionValue.setValue("Safe");
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(ApplicationController.getCurrentUser());
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleDonePressed() {
        Date date = new Date();
        System.out.println(date.toString());
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        double longitude;
        double latitude;
        try {
            isValidWaterReport(longitudeField.getText(), latitudeField.getText(),contaminantField.getText(),virusField.getText());
            longitude = Double.parseDouble(longitudeField.getText());
            longitude +=0.0f;
            latitude = Double.parseDouble(latitudeField.getText());
            latitude +=0.0f;

            Random random = new Random();
            double virus = Double.parseDouble(virusField.getText());
            double contaminant = Double.parseDouble(contaminantField.getText());
            myApp.setMaxVirus(virus);
            myApp.setMaxContaminant(contaminant);

            PurityReport report = new PurityReport(
                    random.nextInt(100),
                    name, date,
                    longitude,
                    latitude,
                    conditionValue.getValue(),
                    virus,
                    contaminant);

            myApp.addPurityReport(report);
            myApp.addPurityLocation(latitudeField.getText() + ", " + longitudeField.getText());
            String [] dateStrings = date.toString().split(" ");
            myApp.addPurityYear(dateStrings[5]);
            myApp.loadApplication(currentUser);

        } catch (NumberFormatException e/*| NullPointerException e*/) {
            AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                    "and/or longitude you entered are not in valid format.");
        }
    }

    public void isValidWaterReport(String longField, String latField, String
            contField, String virusField) throws NumberFormatException {
        double longitude = Double.parseDouble(longField);
        double latitude = Double.parseDouble(latField);
        double contaminant = Double.parseDouble(contField);
        double virus = Double.parseDouble(virusField);
        if (latitude > 90 || latitude < -90
                || longitude > 180 || longitude < -180) {
            throw new NumberFormatException();
        }
    }
}
