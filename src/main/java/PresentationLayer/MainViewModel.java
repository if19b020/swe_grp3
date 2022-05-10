package PresentationLayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewModel {
    public void addNewTour() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/createTourView.fxml"));
        stage.setScene(new Scene(root, 400, 630));
        stage.setTitle("Add New Tour");
        stage.setMinWidth(630);
        stage.setMinHeight(400);
        stage.setMaxWidth(630);
        stage.setMaxHeight(400);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
}
