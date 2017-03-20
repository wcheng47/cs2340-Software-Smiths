package main.java.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by vijay on 10/12/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class SourceReport extends Report {
    private StringProperty _reporterName = new SimpleStringProperty();
    private StringProperty _waterType = new SimpleStringProperty();
    private StringProperty _waterCondition = new SimpleStringProperty();

    public String get_reporterName() {
        return _reporterName.get();
    }

    public String get_waterType() {
        return _waterType.get();
    }

    public String get_waterCondition() {
        return _waterCondition.get();
    }

    public SourceReport(int reportNumber, String reporterName, Date date,
                        double longitude, double latitude, String waterType,
                        String waterCondition){
        super(reportNumber, date, latitude, longitude);
        _reporterName.set(reporterName);
        _waterType.set(waterType);
        _waterCondition.set(waterCondition);
    }

    public String toString(){
        return  "Report Number: " +  get_reportNumber() + "\n" +
                "Reporter Name: " + get_reporterName() + "\n" +
                "Date: " +  get_date() + "\n" +
                "Longitude: " + get_longitude() + "\n" +
                "Latitude: " + get_latitude() +"\n" +
                "Water Type: " + get_waterType() + "\n" +
                "Water Condition: " + get_waterCondition();
    }

    public String getTitle() {
        return "<h2>Report " + get_reportNumber() + "</h2>";
    }

    public String getDescription() {
        return  "<h2>Report " +  get_reportNumber() + "</h2>" + "\n\n" +
                "Reporter Name: " + get_reporterName() + "\n" +
                "<br> Date: " +  get_date() + "\n" +
                "<br> Longitude: " + get_longitude() + "\n" +
                "<br> Latitude: " + get_latitude() +"\n" +
                "<br> Water Type: " + get_waterType() + "\n" +
                "<br> Water Condition: " + get_waterCondition() + "</h2>";
    }

}
