package PresentationLayer.controller;

import PresentationLayer.MainViewModel;
import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public MainViewModel mainViewModel = new MainViewModel();
    public TableView<TourLog> table;
    public ListView<Tour> listTourItems;
    public TextField searchField;
    public Label titleName;
    public Label description;
    public ImageView route;
    public Button editLog;
    public Button addLog;
    public Button removeTour;
    public Button editTour;
    public Button removeLog;
    public MenuItem saveTourFile;
    public TableColumn<TourLog,String> date;
    public TableColumn<TourLog,String> report;
    public TableColumn<TourLog,String> distance;
    public TableColumn<TourLog,String> time;
    public TableColumn<TourLog,String> rating;
    public TableColumn<TourLog,String> weather;
    public TableColumn<TourLog,String> speed;




    public MainViewController() throws SQLException {
    }

    public void searchAction() throws SQLException {
        mainViewModel.searchAction();
    }


    public void addNewLog() throws IOException {
        mainViewModel.addNewLog();
    }


    public void removeTour() throws SQLException {
        mainViewModel.removeTour();
    }

    public void editTour() throws IOException {
        mainViewModel.editTour();
    }

    public void removeLog() throws SQLException {
        mainViewModel.removeLog(table.getSelectionModel().getSelectedItem());
    }

    public void editLog() throws IOException {
        mainViewModel.editLog(table.getSelectionModel().getSelectedItem());
    }
    public void addTour() throws IOException {
        mainViewModel.addTour();
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindBidirectional(searchField.textProperty(), mainViewModel.getSearch());
        Bindings.bindBidirectional(titleName.textProperty(), mainViewModel.getTitle());
        Bindings.bindBidirectional(description.textProperty(), mainViewModel.getDescription());
        Bindings.bindBidirectional(route.imageProperty(), mainViewModel.getRoute());
        table.setItems(mainViewModel.getTourLogs());
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        report.setCellValueFactory(new PropertyValueFactory<>("Report"));
        distance.setCellValueFactory(new PropertyValueFactory<>("Distance"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        weather.setCellValueFactory(new PropertyValueFactory<>("Weather"));
        speed.setCellValueFactory(new PropertyValueFactory<>("Speed"));
        listTourItems.setItems(mainViewModel.getTourItems());
        listTourItems.getSelectionModel().selectedItemProperty().addListener(mainViewModel.getChangeListener());
        editTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        removeTour.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        saveTourFile.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        addLog.disableProperty().bind(listTourItems.getSelectionModel().selectedItemProperty().isNull());
        editLog.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        removeLog.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
        FormatCells();
    }

    private void FormatCells() {

        listTourItems.setCellFactory((param -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || (item == null) || (item.getName() == null)) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        }));
    }

    public void saveTour() {
        mainViewModel.saveTour();
    }




}
