package PresentationLayer.controller;

import PresentationLayer.TourLogModel;
import businesslayer.Tour;
import businesslayer.TourLog;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TourLogViewController implements Initializable {

    public TourLogModel logViewModel = new TourLogModel();
    public Button confirmButton;
    public TextField dateField;
    public TextField reportField;
    public TextField distanceField;
    public TextField timeField;
    public TextField ratingField;
    public TextField weatherField;
    public TextField avSpeedField;


    public void addLog() throws SQLException {
        logViewModel.addLog();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void setLog(TourLog value) {
        logViewModel.setLog(value);
    }

    public void setTour(Tour value) {
        logViewModel.setTour(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(dateField.textProperty(), logViewModel.getDate());
        Bindings.bindBidirectional(reportField.textProperty(), logViewModel.getReport());
        Bindings.bindBidirectional(distanceField.textProperty(), logViewModel.getDistance());
        Bindings.bindBidirectional(timeField.textProperty(), logViewModel.getTime());
        Bindings.bindBidirectional(ratingField.textProperty(), logViewModel.getRating());
        Bindings.bindBidirectional(weatherField.textProperty(), logViewModel.getWeather());
        Bindings.bindBidirectional(avSpeedField.textProperty(), logViewModel.getSpeed());
        confirmButton.disableProperty().bind(dateField.textProperty().isEmpty().and(reportField.textProperty().isEmpty().and(
                distanceField.textProperty().isEmpty()).and(timeField.textProperty().isEmpty()).and(
                ratingField.textProperty().isEmpty()).and(weatherField.textProperty().isEmpty()).and(
                avSpeedField.textProperty().isEmpty())));
    }

}
