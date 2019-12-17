package controllers;

import bll.Event;
import com.jfoenix.controls.*;
import dao.EventDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.Naming;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventCreateController {

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
    private JFXButton AddEvent;

    @FXML
    private JFXButton UpdateEvent;

    @FXML
    private JFXDatePicker DateEvent;

    @FXML
    private JFXTimePicker TimeEvent;

    @FXML
    void addEventClicked(ActionEvent event) {
        LocalDate ld = DateEvent.getValue();
        LocalTime lt = TimeEvent.getValue();
        try
        {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            Event ev = new Event();
            ev.setEvent_title(EventTitle.getText());
            ev.setEvent_date(ld);
            ev.setEvent_time(lt);
            ev.setEvent_desc(EventDesc.getText());
            ed.addEventDet(ev);
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Event Added");
            alert.setContentText("Event has been successfully added.");
            alert.showAndWait();

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
