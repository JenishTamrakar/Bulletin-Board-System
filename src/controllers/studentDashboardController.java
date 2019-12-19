package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class studentDashboardController {

    @FXML
    private AnchorPane studentDashboard;

    @FXML
    private JFXButton LogOut;

    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StdAssign.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDet(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentFeeDet.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    @FXML
    void goToFeedback(ActionEvent event) {

    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentNotice.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    public void logOutClicked(ActionEvent actionEvent) {
    }

    public void profileClicked(ActionEvent actionEvent) {
    }
}

