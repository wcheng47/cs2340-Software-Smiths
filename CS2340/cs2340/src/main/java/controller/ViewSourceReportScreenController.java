package main.java.controller;

import main.java.model.PurityReport;
import main.java.model.Report;
import main.java.model.SourceReport;
import main.java.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.text.SimpleDateFormat;


/**
 * Created by chitramahajani on 10/25/16.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class ViewSourceReportScreenController {

    @FXML
    private TableView reportsTable;

    private Main myApp;

    private User currentUser;

    void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setMainApp(Main mainApp) {
        //boolean value is true for source reports false for purity reports
        myApp = mainApp;
        TableColumn reportCol = new TableColumn("Report Type");
        TableColumn reportNumCol = new TableColumn("Report Number");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn reporterNameCol = new TableColumn("Reporter Name");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn typeCol = new TableColumn("Water Type");
        TableColumn conditionCol = new TableColumn("Condition");
        TableColumn virusCol = new TableColumn("Virus");
        TableColumn contaminationCol = new TableColumn("Contaminant");

        reportCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();

                        String type = "";

                        if (report instanceof SourceReport){
                            type = "Source";
                        } else if (report instanceof PurityReport){
                            type = "Purity";
                        }

                        return new SimpleStringProperty(type);
                    }
                }
        );

        reportNumCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();
                        return new SimpleStringProperty(report.get_reportNumber()+"");
                    }
                }
        );

        dateCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();

                        String date = new SimpleDateFormat("MM/dd/yyyy " +
                                "HH:mm").format(report.get_date());

                        return new SimpleStringProperty(date);
                    }
                }
        );

        reporterNameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();
                        if(report instanceof PurityReport){
                            return new SimpleStringProperty(((PurityReport)report).get_nameOfWorker());
                        }else {
                            return new SimpleStringProperty(((SourceReport)report).get_reporterName());
                        }
                    }
                }
        );

        locationCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();
                        return new SimpleStringProperty(report.get_latitude()+" "+report.get_longitude());
                    }
                }
        );

        typeCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();
                        if(report instanceof SourceReport) {
                            return new SimpleStringProperty(((SourceReport) report).get_waterType());
                        }else {
                            return new SimpleStringProperty("");
                        }
                    }
                }
        );

        conditionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();

                        String condition = "";

                        if (report instanceof SourceReport){
                            condition = ((SourceReport) report)
                                    .get_waterCondition();
                        } else if (report instanceof PurityReport){
                            condition = ((PurityReport) report).get_waterOverallCondition();
                        }

                        return new SimpleStringProperty(condition);
                    }
                }
        );

        virusCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();

                        String virus = "";

                        if (report instanceof PurityReport){
                            virus = ((PurityReport) report).get_virusPPM() + " PPM";
                        }

                        return new SimpleStringProperty(virus);
                    }
                }
        );

        contaminationCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures dataFeatures) {
                        Report report = (Report) dataFeatures.getValue();

                        String contaminant = "";

                        if (report instanceof PurityReport){
                            contaminant = ((PurityReport) report).get_contaminantPPM() + " " +
                                    "PPM";
                        }

                        return new SimpleStringProperty(contaminant);
                    }
                }
        );

        ObservableList<Report> sourceReport = FXCollections
                .observableArrayList(myApp.getSourceReportList());
        ObservableList<Report> purityReport = FXCollections
                .observableArrayList(myApp.getPurityReportList());

        ObservableList<Report> mySourceReports = FXCollections.concat(sourceReport, purityReport);

        reportsTable.setItems(mySourceReports);

        reportsTable.getColumns().addAll(reportCol, reportNumCol, dateCol,
                reporterNameCol, locationCol, typeCol, conditionCol, virusCol,
                contaminationCol);
    }

    @FXML
    private void handleExitClicked() {
        myApp.loadApplication(currentUser);
    }

    @FXML
    private void handleDonePressed() {
        myApp.loadApplication(currentUser);
    }
}
