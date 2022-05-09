package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class TourViewModel {

    @Getter
    private final StringProperty tourName = new SimpleStringProperty("");

    @Getter
    private final StringProperty from = new SimpleStringProperty("");

    @Getter
    private final StringProperty to = new SimpleStringProperty("");

    @Getter
    private final StringProperty description = new SimpleStringProperty("");

    public void addTour() {
    }
}
