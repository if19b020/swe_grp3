package businesslayer;

import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;
import javafx.collections.ObservableList;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TourplannerManager {
    List<Tour> GetItems() throws SQLException;
    List<Tour> Search(String itemName, boolean caseSensitive) throws SQLException;
    Tour GetItem(Integer id) throws SQLException;
    void CreateTourItem(String name, String description, String distance, String start, String end) throws SQLException;
    void UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException;
    void saveTourToPdf(Tour value, ObservableList<TourLog> tourLogs);
    void DeleteTour(Tour item);
    void DeleteTourImage(String imagePath);

    List<TourLog> GetLogs(Tour newValue) throws SQLException;
    void CreateTourLog(TourLog log, Tour item) throws SQLException;

    boolean validateAddressInput(String start, String finish);

    void UpdateLogItem(TourLog genLog, Integer id) throws SQLException;
    void DeleteLog(TourLog log);

    String GetMapQuest(String name, String from, String to) throws JSONException, IOException, InterruptedException;
}
