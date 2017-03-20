package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import main.java.model.PurityReport;
import main.java.model.User;

import java.util.ArrayList;

/**
 * Created by Yash on 10/30/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class HistoryGraphController {

    private Main myApp;

    private User currentUser;

    @FXML
    private ComboBox<String> locationBox;

    @FXML
    private ComboBox<String> dataBox;

    @FXML
    private ComboBox<String> yearBox;

    private ObservableList<String> dataList = FXCollections.observableArrayList("Virus", "Contaminant");

    /*@FXML
    private void initialize() {
        typeBox.setItems(typeList);
        typeBox.setValue("User");

    }*/

    @FXML
    public void setMainApp(Main mainApp) {
        myApp = mainApp;
        ArrayList<String> locationList = myApp.getPurityLocationsList();
        ArrayList<String> yearList = myApp.getPurityYearList();
        ObservableList<String> graphLocationList = FXCollections.observableArrayList(locationList);
        ObservableList<String> graphYearList = FXCollections.observableArrayList(yearList);
        System.out.println(locationList);
        System.out.println(yearList);
        locationBox.setItems(graphLocationList);
        dataBox.setItems(dataList);
        yearBox.setItems(graphYearList);
        dataBox.setValue("Virus");
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
        String position = locationBox.getValue();
        String year = yearBox.getValue();
        String data = dataBox.getValue();
        ArrayList<PurityReport> reportList = myApp.getPurityReportList();
        ArrayList<PurityReport> toGraph = new ArrayList<>();
        for(PurityReport report : reportList) {
            if(report.includeInGraph(position, year)) {
                toGraph.add(report);
            }
        }
        myApp.loadGraph(toGraph, data, position, year, currentUser);
    }
}
