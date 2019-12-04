package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.event.TreeModelEvent;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private JFXButton noticebutton;
    @FXML
    private AnchorPane adminDashboard;
    @FXML
    private JFXButton goToNotice;

    @FXML
    void goToNotice(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        adminDashboard.getChildren().setAll(pane);

    }
    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        adminDashboard.getChildren().setAll(pane);

    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        adminDashboard.getChildren().setAll(pane);
    }

}


