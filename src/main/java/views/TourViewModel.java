package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import lombok.Getter;
import views.controller.TourViewController;

public class TourViewModel {

    @Getter
    private final StringProperty tourName = new SimpleStringProperty("");

    @Getter
    private final StringProperty from = new SimpleStringProperty("");

    @Getter
    private final StringProperty to = new SimpleStringProperty("");

    @Getter
    private final StringProperty description = new SimpleStringProperty("");

    public void addTour() {Object controller;

    }
}
