package test.java;

import main.java.controller.SubmitSourceReportScreenController;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Created by Rishabh on 11/15/2016.
 * This class's purpose is to:
 */
public class RishabhJUnitTests {
    private SubmitSourceReportScreenController myController;
    private String longitude;
    private String latitude;

    @Before
    public void setupApplicationController() throws NoSuchFieldException {
        myController = new SubmitSourceReportScreenController();
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeOutOfBoundsLongitudePositive() {
        myController = new SubmitSourceReportScreenController();
        latitude = "-100";
        longitude = "30";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveOutOfBoundsLongitudePositive() {
        myController = new SubmitSourceReportScreenController();
        latitude = "100";
        longitude = "30";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeOutOfBoundsLongitudeNegative() {
        myController = new SubmitSourceReportScreenController();
        latitude = "-100";
        longitude = "-30";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveOutOfBoundsLongitudeNegative() {
        myController = new SubmitSourceReportScreenController();
        latitude = "100";
        longitude = "-30";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudeNegativeOutOfBounds() {
        myController = new SubmitSourceReportScreenController();
        latitude = "-70";
        longitude = "-200";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudePositiveOutOfBounds() {
        myController = new SubmitSourceReportScreenController();
        latitude = "-70";
        longitude = "200";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudeNegativeOutOfBounds() {
        myController = new SubmitSourceReportScreenController();
        latitude = "70";
        longitude = "-200";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudePositiveOutOfBounds() {
        myController = new SubmitSourceReportScreenController();
        latitude = "70";
        longitude = "200";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNegativeLongitudeNotParsable() {
        myController = new SubmitSourceReportScreenController();
        latitude = "-70";
        longitude = "Cheese";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudePositiveLongitudeNotParsable() {
        myController = new SubmitSourceReportScreenController();
        latitude = "70";
        longitude = "Cheese";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNotParsableLongitudeNegative() {
        myController = new SubmitSourceReportScreenController();
        latitude = "Cheese";
        longitude = "-20";
        myController.isValidSourceReport(longitude, latitude);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void checkLatitudeNotParsableLongitudePositive() {
        myController = new SubmitSourceReportScreenController();
        latitude = "Cheese";
        longitude = "20";
        myController.isValidSourceReport(longitude, latitude);
    }
}