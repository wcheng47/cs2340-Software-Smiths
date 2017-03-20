package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.model.*;

import java.util.Date;
import java.util.Random;

/**
 * Created by Yash on 10/12/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class SubmitSourceReportScreenController {

    private User currentUser;

    private Main myApp;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private ComboBox<String> typeField;

    @FXML
    private ComboBox<String> conditionField;

    //private int reportNumber = myApp.getSourceReportList().size();

    private ObservableList<String> myTypes = FXCollections
            .observableArrayList("Bottled", "Stream", "Lake", "Other");

    private ObservableList<String> myConditions = FXCollections
            .observableArrayList("Waste", "Treatable", "Clean", "Other");

    @FXML
    private void initialize() {
        typeField.setItems(myTypes);
        typeField.setValue("Bottled");
        conditionField.setItems(myConditions);
        conditionField.setValue("Waste");
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @FXML
    private void handleCancelPressed() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        Date date = new Date();
        String name = currentUser.getFirstName() + " " + currentUser.getLastName();
        double longitude;
        double latitude;
        try {
            isValidSourceReport(longitudeField.getText(), latitudeField.getText());
            longitude = Double.parseDouble(longitudeField.getText());
            latitude = Double.parseDouble(latitudeField.getText());
            Random random = new Random();
            SourceReport report = new SourceReport(
                    random.nextInt(100),
                    name,
                    date,
                    longitude,
                    latitude,
                    typeField.getValue(),
                    conditionField.getValue());
            myApp.addSourceReport(report);
            myApp.loadApplication(currentUser);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(e.getMessage());
                AlertMessage.sendMessage("Invalid Coordinates", "The latitude " +
                        "and/or longitude you entered are not in valid format.");
        }
    }

    public void isValidSourceReport(String longField, String latField) throws NumberFormatException {
        double longitude = Double.parseDouble(longField);
        double latitude = Double.parseDouble(latField);
            if (latitude > 90 || latitude < -90
                    || longitude > 180 || longitude < -180) {
                throw new NumberFormatException();
            }
        }
    }