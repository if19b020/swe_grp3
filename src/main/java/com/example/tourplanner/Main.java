package com.example.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Logger log = LogManager.getLogger(Main.class);
        log.info("Application started");
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource("mainView.fxml"))));
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(new Scene(root, 1300, 900));
        primaryStage.setMinWidth(1002);
        primaryStage.setMinHeight(500);
        primaryStage.show();;
    }

    public static void main(String[] args) {
        launch(args);
    }
}