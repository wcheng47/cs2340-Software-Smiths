package main.java.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by Arber on 10/24/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class PurityReport extends Report {
    private StringProperty _nameOfWorker = new SimpleStringProperty();
    private WaterCondition _waterOverallCondition = WaterCondition.SAFE;
    private DoubleProperty _virusPPM = new SimpleDoubleProperty();
    private DoubleProperty _contaminantPPM = new SimpleDoubleProperty();
    private String _year;

    public String get_nameOfWorker() {
        return _nameOfWorker.get();
    }

    public String get_waterOverallCondition() {
        return _waterOverallCondition.toString();
    }

    public double get_virusPPM() {
        return _virusPPM.get();
    }

    public double get_contaminantPPM() {
        return _contaminantPPM.get();
    }

    public PurityReport(int reportNumber, String reporterName, Date date,
                        double longitude, double latitude,
                        String waterCondition, double virusPPM,
                        double contaminantPPM){
        super(reportNumber, date, longitude, latitude);
        String [] dateString = date.toString().split(" ");
        _year = dateString[5];
        _nameOfWorker.set(reporterName);
        _virusPPM.set(virusPPM);
        _contaminantPPM.set(contaminantPPM);
        switch(waterCondition.charAt(0)){
            case 'u':
                _waterOverallCondition = WaterCondition.UNSAFE;
                break;
            case 's':
                _waterOverallCondition = WaterCondition.SAFE;
                break;
            case 't':
                _waterOverallCondition = WaterCondition.TREATABLE;
                break;
        }
    }

    public String toString(){
        return  "Report Number: " +  get_reportNumber() + "\n" +
                "Worker Name: " + get_nameOfWorker() + "\n" +
                "Date: " +  get_date() + "\n" +
                "Longitude: " + get_longitude() + "\n" +
                "Latitude: " + get_latitude() +"\n" +
                "Water Condition: " + get_waterOverallCondition();
    }

    //public String getTitle() {
    //    return "<h2>Report " + get_reportNumber() + "</h2>";
    //}
    //
    //public String getDescription() {
    //    return  "<h2>Report " +  get_reportNumber() + "</h2>" + "\n\n" +
    //            "Worker Name: " + get_nameOfWorker() + "\n" +
    //            "<br> Date: " +  get_date() + "\n" +
    //            "<br> Longitude: " + get_longitude() + "\n" +
    //            "<br> Latitude: " + get_virusPPM() +"\n" +
    //            "<br> Overall Water Condition: " + get_waterOverallCondition() +
    //            "<br> Virus PPM: " + get_virusPPM() +
    //            "<br> Contaminant PPM: " + get_contaminantPPM() + "</h2>";
    //}

    private enum WaterCondition {
        SAFE, TREATABLE, UNSAFE
    }

    public boolean includeInGraph(String position, String year) {
        String [] doubles = position.split(", ");
        double latitude = Double.parseDouble(doubles[0]);
        double longitude = Double.parseDouble(doubles[1]);

        return this.get_latitude() == latitude
                && this.get_longitude() == longitude
                && _year.equals(year);
    }
}
