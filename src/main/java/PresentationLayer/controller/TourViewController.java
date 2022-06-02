package PresentationLayer.controller;

import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import PresentationLayer.MainViewModel;
import PresentationLayer.TourViewModel;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class TourViewController implements Initializable {

    public TourViewModel tourViewModel = new TourViewModel();
    public Button confirmButton;
    public TextField nameField;
    public TextField startField;
    public TextField endField;
    public TextArea descriptionField;


    public void addAction() throws SQLException, IOException, JSONException, InterruptedException {
        tourViewModel.addAction();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(nameField.textProperty(), tourViewModel.getName());
        Bindings.bindBidirectional(startField.textProperty(), tourViewModel.getFrom());
        Bindings.bindBidirectional(endField.textProperty(), tourViewModel.getTo());
        Bindings.bindBidirectional(descriptionField.textProperty(), tourViewModel.getDescription());
        confirmButton.disableProperty().bind(nameField.textProperty().isEmpty().or(startField.textProperty().isEmpty().or(endField.textProperty().isEmpty()))
        );
    }

    public void setTour(Tour value) {
        tourViewModel.setTour(value);
    }
}


