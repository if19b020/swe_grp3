package PresentationLayer;

import businesslayer.TourPlannerManager;
import businesslayer.TourPlannerManagerFactory;
import businesslayer.Tour;
import businesslayer.TourLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class TourLogModel  {

    private final TourPlannerManager manager = TourPlannerManagerFactory.getManager();
    private Integer id = null;
    @Setter
    private Tour tour = null;
    @Getter
    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();
    @Getter private final StringProperty date = new SimpleStringProperty("");
    @Getter private final StringProperty report = new SimpleStringProperty("");
    @Getter private final StringProperty distance = new SimpleStringProperty("");
    @Getter private final StringProperty time = new SimpleStringProperty("");
    @Getter private final StringProperty rating = new SimpleStringProperty("");
    @Getter private final StringProperty weather = new SimpleStringProperty("");
    @Getter private final StringProperty speed = new SimpleStringProperty("");

    public void addLog() throws SQLException {
        TourLog genLog = new TourLog(date.getValue(), report.getValue(), distance.getValue(), time.getValue(), rating.getValue(), weather.getValue(), speed.getValue());
        if(id == null){
            manager.CreateTourLog(genLog, tour);
        } else {
            manager.UpdateLogItem(genLog, id);
        }
    }

    public void setLog(TourLog value) {
        id = value.getId();
        date.set(value.getDate());
        report.set(value.getReport());
        distance.set(value.getDistance());
        time.set(value.getTime());
        rating.set(value.getRating());
        weather.set(value.getWeather());
        speed.set(value.getSpeed());

    }
}
