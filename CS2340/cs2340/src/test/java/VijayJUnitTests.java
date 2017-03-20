package test.java;

import main.java.controller.SubmitWaterReportScreenController;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Created by vijay on 11/15/2016.
 * This is a JUnit test that tests WaterReports
 */
public class VijayJUnitTests {
    private SubmitWaterReportScreenController myController;
    private String longitude;
    private String latitude;
    private String contaminant;
    private String virus;

    @Before
    public void setupApplicationController() throws NoSuchFieldException {
        myController = new SubmitWaterReportScreenController();
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeOutOfBoundsLongitudePositive() {
        myController = new SubmitWaterReportScreenController();
        latitude = "-100";
        longitude = "30";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveOutOfBoundsLongitudePositive() {
        myController = new SubmitWaterReportScreenController();
        latitude = "100";
        longitude = "30";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeOutOfBoundsLongitudeNegative() {
        myController = new SubmitWaterReportScreenController();
        latitude = "-100";
        longitude = "-30";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveOutOfBoundsLongitudeNegative() {
        myController = new SubmitWaterReportScreenController();
        latitude = "100";
        longitude = "-30";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudeNegativeOutOfBounds() {
        myController = new SubmitWaterReportScreenController();
        latitude = "-70";
        longitude = "-200";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudePositiveOutOfBounds() {
        myController = new SubmitWaterReportScreenController();
        latitude = "-70";
        longitude = "200";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudeNegativeOutOfBounds() {
        myController = new SubmitWaterReportScreenController();
        latitude = "70";
        longitude = "-200";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudePositiveOutOfBounds() {
        myController = new SubmitWaterReportScreenController();
        latitude = "70";
        longitude = "200";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudeNotParsable() {
        myController = new SubmitWaterReportScreenController();
        latitude = "-70";
        longitude = "Cheese";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudeNotParsable() {
        myController = new SubmitWaterReportScreenController();
        latitude = "70";
        longitude = "Cheese";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNotParsableLongitudeNegative() {
        myController = new SubmitWaterReportScreenController();
        latitude = "Cheese";
        longitude = "-20";
        contaminant = "100";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkContaminantNotParsable() {
        myController = new SubmitWaterReportScreenController();
        latitude = "20";
        longitude = "20";
        contaminant = "nope";
        virus = "100";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkVirusNotParsable() {
        myController = new SubmitWaterReportScreenController();
        latitude = "20";
        longitude = "20";
        contaminant = "100";
        virus = "nope";
        myController.isValidWaterReport(longitude, latitude, contaminant,virus);
    }

}
