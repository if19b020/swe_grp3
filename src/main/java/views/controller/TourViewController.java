package views.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class TourViewController implements Initializable {



    public static void main(String[] args) {
        launch(args);
    }


    public void addNewTour(ActionEvent actionEvent) {
    }

    public void removeTour(ActionEvent actionEvent) {
    }

    public void editTour(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional();
    }
}
