package PresentationLayer;

import PresentationLayer.controller.TourLogViewController;
import PresentationLayer.controller.TourViewController;
import businesslayer.TourplannerManager;
import businesslayer.TourplannerManagerFactory;
import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainViewModel {
    public MainViewModel() throws SQLException {
        tourItems.addAll(manager.GetItems());
        title.set("Title");
    }

    private final TourplannerManager manager = TourplannerManagerFactory.getManager();
    private final Property<Tour> currentTour = new SimpleObjectProperty<>();

    @Getter private final ObservableList<Tour> tourItems = FXCollections.observableArrayList();
    @Getter private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
    @Getter private final StringProperty search = new SimpleStringProperty("");
    @Getter private final StringProperty title = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");
    @Getter private final StringProperty adjacentTour = new SimpleStringProperty("");
    @Getter private final ObjectProperty<Image> route = new SimpleObjectProperty<>();

    @Getter private final ChangeListener<Tour> changeListener = (observableValue, oldValue, newValue) -> {
        if ((newValue != null) && (oldValue) != (newValue)) {
            currentTour.setValue(newValue);
            title.set(newValue.getName());
            description.set(newValue.getFullDescription());
            tourLogs.clear();
            try {
                tourLogs.addAll(manager.GetLogs(newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                FileInputStream inputStream = new FileInputStream("Images/"+ newValue.getName() + ".jpg");
                route.set(new Image(inputStream));
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void refresh() throws SQLException {
        tourItems.clear();
        search.set("");
        title.set("Title");
        description.set("");
        route.set(null);
        List<Tour> items = manager.GetItems();
        tourItems.addAll(items);
        tourLogs.clear();
    }

    public void searchAction() throws SQLException {
        tourItems.clear();
        List<Tour> items = manager.Search(search.get(), false);
        tourItems.addAll(items);
    }

    public void clearAction() throws SQLException {
        tourItems.clear();
        search.set("");
        List<Tour> items = manager.GetItems();
        tourItems.addAll(items);
    }

    public void addNewLog() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/createLogView.fxml"));
        stage.setScene(new Scene(loader.load(), 280, 580));
        stage.setTitle("Manage Log");
        stage.setMinWidth(800);
        stage.setMinHeight(280);
        stage.setMaxWidth(800);
        stage.setMaxHeight(280);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        TourLogViewController controller =  loader.getController();
        controller.setTour(currentTour.getValue());
        stage.setOnHiding(e ->{
            try {
                tourLogs.clear();
                tourLogs.addAll(manager.GetLogs(currentTour.getValue()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void removeTour() throws SQLException {
        manager.DeleteTour(currentTour.getValue());
        refresh();
    }

    public void editTour() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/createTourView.fxml"));
        stage.setScene(new Scene(loader.load(), 400, 630));
        stage.setTitle("Manage Tour");
        stage.setMinWidth(630);
        stage.setMinHeight(400);
        stage.setMaxWidth(630);
        stage.setMaxHeight(400);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        TourViewController controller =  loader.getController();
        controller.setTour(currentTour.getValue());
        stage.setOnHiding(e ->{
            try {
                clearAction();
                var tour = manager.GetItem(currentTour.getValue().getId());
                title.set(tour.getName());
                description.set(tour.getDescription());
                FileInputStream inputStream = new FileInputStream("Images/"+ tour.getName() + ".jpg");
                route.set(new Image(inputStream));
                inputStream.close();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    public void addTour() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/createTourView.fxml"));
        stage.setScene(new Scene(root, 400, 630));
        stage.setTitle("Manage Tour");
        stage.setMinWidth(630);
        stage.setMinHeight(400);
        stage.setMaxWidth(630);
        stage.setMaxHeight(400);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setOnHiding(e ->{
            try {
                refresh();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void removeLog(TourLog tourLog) throws SQLException {
        manager.DeleteLog(tourLog);
        tourLogs.clear();
        tourLogs.addAll(manager.GetLogs(currentTour.getValue()));
    }

    public void editLog(TourLog tourLog) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/createLogView.fxml"));
        stage.setScene(new Scene(loader.load(), 280, 580));
        stage.setTitle("Manage Log");
        stage.setMinWidth(580);
        stage.setMinHeight(280);
        stage.setMaxWidth(580);
        stage.setMaxHeight(280);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        TourLogViewController controller =  loader.getController();
        controller.setLog(tourLog);
        stage.setOnHiding(e ->{
            try {
                tourLogs.clear();
                tourLogs.addAll(manager.GetLogs(currentTour.getValue()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void saveTour() {
        manager.saveTourToPdf(currentTour.getValue(), tourLogs);
    }



}
