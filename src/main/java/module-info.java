module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires lombok;

    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    //exports ViewController;
    //exports TourController;
    exports views;
    exports views.controller;
}