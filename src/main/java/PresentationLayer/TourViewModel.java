package PresentationLayer;
import businesslayer.TourplannerManager;
import businesslayer.TourplannerManagerFactory;
import com.example.tourplanner.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class TourViewModel {
    private final TourplannerManager manager = TourplannerManagerFactory.getManager();
    private Integer id = null;
    private String oldName;
    @Getter private final StringProperty name = new SimpleStringProperty("");
    @Getter private final StringProperty from = new SimpleStringProperty("");
    @Getter private final StringProperty to = new SimpleStringProperty("");
    @Getter private final StringProperty description = new SimpleStringProperty("");
    Logger log = LogManager.getLogger(TourViewModel.class);


    public void addAction() throws SQLException, IOException, JSONException, InterruptedException {
        String descriptionWithDistance;
        if(!manager.validateAddressInput(from.getValue(), to.getValue())){
            log.info("Address input contained wrong values and no tour was added or changed.");
            return;
        }
        if(id == null){
            String distance = manager.GetMapQuest(name.getValue(), from.getValue(), to.getValue());
            manager.CreateTourItem(name.getValue(), description.getValue(), distance, from.getValue(), to.getValue());
        } else {
            if(!name.getValue().equals(oldName)){
                manager.DeleteTourImage("Images/"+oldName+".jpg");
            }
            String distance = manager.GetMapQuest(name.getValue(), from.getValue(), to.getValue());
            descriptionWithDistance = ("This Tour has a total length of "+distance+"km\n" +description.getValue());
            manager.UpdateTourItem(id, name.getValue(), description.getValue(), descriptionWithDistance, from.getValue(), to.getValue());
        }
    }

    public void setTour(Tour value) {
        id = value.getId();
        name.set(value.getName());
        description.set(value.getDescription());
        from.set(value.getFrom());
        to.set(value.getTo());
        oldName = name.getValue();
    }
}
