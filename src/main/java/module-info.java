module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires lombok;
    requires itextpdf;
    requires org.json;
    requires org.apache.logging.log4j;
    requires java.desktop;
    requires java.net.http;

    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    //exports ViewController;
    //exports TourController;
    exports PresentationLayer;
    exports PresentationLayer.controller;
}