package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.model.PurityReport;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 11/22/2016.
 */
public class DeleteReportController {
    private Main myApp;

    private User currentUser;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField numberField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button submitButton;

    private ObservableList<String> typeList = FXCollections.observableArrayList("Source", "Purity");

    /*@FXML
    private void initialize() {
        typeBox.setItems(typeList);
        typeBox.setValue("User");

    }*/

    @FXML
    public void setMainApp(Main mainApp) {
        myApp = mainApp;
        typeBox.setItems(typeList);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @FXML
    public void handleCancelPressed() {
        myApp.loadApplication(currentUser);

    }

    @FXML
    public void handleSubmitPressed() {
        boolean foundReport = false;
        String type = typeBox.getValue();
        String numString = numberField.getText();
        int reportNumber = Integer.parseInt(numString);
        ArrayList<PurityReport> purityReportList;
        ArrayList<SourceReport> sourceReportList;
        if(type.equals("Purity")) {
            purityReportList = myApp.getPurityReportList();
            for(int j = 0; j < purityReportList.size() && !foundReport; j++) {
                PurityReport report = purityReportList.get(j);
                if(report.get_reportNumber() == reportNumber) {
                    myApp.deletePurityReport(j);
                    foundReport = true;
                    myApp.writeSecurity("PURITY REPORT " + reportNumber + " DELETED by manager " + currentUser.getUserName());
                }
            }
        } else {
            sourceReportList = myApp.getSourceReportList();
            for(int j = 0; j < sourceReportList.size() && !foundReport; j++) {
                SourceReport report = sourceReportList.get(j);
                if(report.get_reportNumber() == reportNumber) {
                    myApp.deleteSourceReport(j);
                    foundReport = true;
                    myApp.writeSecurity("SOURCE REPORT " + reportNumber + " DELETED by manager " + currentUser.getUserName());
                }
            }
        }
        if(foundReport) {
            myApp.loadApplication(currentUser);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report Not Found");
            alert.setContentText("No report with this number exists");
            alert.showAndWait();
        }


    }
}
