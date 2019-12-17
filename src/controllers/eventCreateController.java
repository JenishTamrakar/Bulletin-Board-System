package controllers;

import bll.CreateEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.CreateEventDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.Naming;

public class eventCreateController {

    @FXML
    private AnchorPane eventPane;

    @FXML
    private JFXButton SgnOut;

    @FXML
    private JFXTextArea EventDesc;

    @FXML
    private JFXTextField EventTitle;

    @FXML
    private TreeTableView<?> EventTbl;

    @FXML
    private JFXTextField EventDate;

    @FXML
    private JFXTextField EventTime;

    @FXML
    private JFXButton AddEvent;

    @FXML
    private JFXButton UpdateEvent;

    @FXML
    void addEventClicked(ActionEvent event) {
        try
        {
            CreateEventDao ced = (CreateEventDao) Naming.lookup("rmi://localhost/CreateEvent");
            CreateEvent ce = new CreateEvent();
            ce.setEventTitle(EventTitle.getText());
            ce.setEventDescription(EventDesc.getText());
            ce.setEventDate(EventDate.getText());
            ce.setEventTime(EventTime.getText());
            ced.addEvent(ce);
            System.out.println(EventTitle.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Event Added");
            alert.setContentText("Event Successfully Added!");
            alert.showAndWait();
            EventTitle.setText(null);
            EventDesc.setText(null);
            EventDate.setText(null);
            EventTime.setText(null);
        } catch (Exception o) {
            System.out.println(o);
        }

    }

    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        eventPane.getChildren().setAll(pane);
    }


    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void updateEvent(ActionEvent event) {

    }

}
