package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AdminDashboardController {
    @FXML
    private AnchorPane adminDashboard;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton showChartBtn;

    //navigation to notice window
    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to student records window
    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to faculty records window
    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to assignments window
    @FXML
    void goToAssignments(ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDetails(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        adminDashboard.getChildren().setAll(pane);

    }

    //navigation to feedback window
    @FXML
    void goToFeedback(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/viewFeedback.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

    //show chart
    @FXML
    void showChartBtnClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/charts.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }



}


