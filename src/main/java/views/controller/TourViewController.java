package views.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import views.TourViewModel;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class TourViewController implements Initializable {

    public TourViewModel tourViewModel;
    public Button confirmButton;
    public TextField tourName;
    public TextField from;
    public TextField to;
    public TextField description;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Bindings.bindBidirectional(tourName.textProperty(), tourViewModel.getTourName());
        Bindings.bindBidirectional(from.textProperty(), tourViewModel.getFrom());
        Bindings.bindBidirectional(to.textProperty(), tourViewModel.getTo());
        Bindings.bindBidirectional(description.textProperty(), tourViewModel.getDescription());*/
        // TODO confirmButton
    }


    public void addNewTour(ActionEvent actionEvent) {
        tourViewModel.addTour();
    }

    public void removeTour(ActionEvent actionEvent) {
    }

    public void editTour(ActionEvent actionEvent) {
    }

}
