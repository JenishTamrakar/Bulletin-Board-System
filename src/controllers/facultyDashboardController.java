package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class facultyDashboardController
{
    @FXML
    private AnchorPane facultyDashboardPane;

    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentNotice.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    void logOutClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }
}
