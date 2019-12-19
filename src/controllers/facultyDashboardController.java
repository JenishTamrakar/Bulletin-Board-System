package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class facultyDashboardController
{
    @FXML
    private AnchorPane facultyDashboardPane;

    @FXML
    void goToAssignments(ActionEvent event) {

    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) {

    }

    @FXML
    void logOutClicked(ActionEvent event) {

    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }
}
