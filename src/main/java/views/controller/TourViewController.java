package views.controller;

import com.example.tourplanner.Tour;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.MainViewModel;
import views.TourViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class TourViewController implements Initializable {

    public TourViewModel tourViewModel = new TourViewModel();

    public TextField tourName = new TextField();
    public TextField from = new TextField();
    public TextField to = new TextField();
    public TextField description = new TextField();
    public Button confirmButton = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(tourName.textProperty(), tourViewModel.getTourName());
        Bindings.bindBidirectional(from.textProperty(), tourViewModel.getFrom());
        Bindings.bindBidirectional(to.textProperty(), tourViewModel.getTo());
        Bindings.bindBidirectional(description.textProperty(), tourViewModel.getDescription());
        // TODO confirmButton
        //confirmButton.disableProperty().bind(tourName.textProperty().isEmpty().or(from.textProperty().isEmpty().or(to.textProperty().isEmpty())));

    }


    public void addNewTour(ActionEvent actionEvent) throws IOException {
        tourViewModel.addTour();
        MainViewModel mvm = new MainViewModel();
        mvm.addNewTour();

    }

    public void removeTour(ActionEvent actionEvent) {
    }

    public void editTour(ActionEvent actionEvent) {
    }

    public void confirmTour(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
        // TODO add tour to DB
        System.out.println(tourViewModel.getTourName().getValue());
        String name = tourViewModel.getTourName().getValue();
        String from = tourViewModel.getFrom().getValue();
        String to = tourViewModel.getTo().getValue();
        String description = tourViewModel.getDescription().getValue();
        Tour tour = new Tour(name, from, to, description);


    }
}
