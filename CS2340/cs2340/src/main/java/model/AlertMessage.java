package main.java.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Created by chitramahajani on 10/3/16.
 * A class to create and handle different alert messages
 */
public class AlertMessage {

    /**
     * A method that displays an alert message with varying title and message
     *
     * @param title the title of the alert message window
     * @param message the message displayed by the alert box
     */
    public static void sendMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
