package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MainViewModel {
    public void addNewTour() throws IOException {
        /*Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com.example.tourplanner/createTourView.fxml")));
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("New Tour");
        stage.show();*/
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/createTourView.fxml"));
        stage.setScene(new Scene(root, 400, 630));
        stage.setTitle("Manage Tour");
        stage.setMinWidth(630);
        stage.setMinHeight(400);
        stage.setMaxWidth(630);
        stage.setMaxHeight(400);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        /*stage.setOnHiding(e ->{
            try {
                refresh();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });*/
    }
}
