package controllers;

import bll.FeeDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.FeeDetailsDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.rmi.Naming;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class FeeDetailsController {

    @FXML
    private AnchorPane feeDetailsPane;

    @FXML
    private JFXButton eventsTop;

    @FXML
    private JFXButton facultyTop;

    @FXML
    private JFXButton assignmentsTop;

    @FXML
    private JFXButton studentRecordTop;

    @FXML
    private JFXButton studentRecordTop1;

    @FXML
    private JFXTextArea FeeDet;

    @FXML
    private JFXTextField FeeAmt;

    @FXML
    private JFXButton AddFeeDet;

    @FXML
    private JFXButton UpdateNtc;

    @FXML
    private TreeTableView<?> NtcTable;

    @FXML
    private TreeTableColumn<?, ?> FeeStdIDTbl;

    @FXML
    private TreeTableColumn<?, ?> FeeAmtTbl;

    @FXML
    private TreeTableColumn<?, ?> FeeDedDatetbl;

    @FXML
    private TreeTableColumn<?, ?> FeeDetTbl;

    @FXML
    private JFXTextField DedDate;

    @FXML
    private JFXTextField FeeStdID;
    @FXML
    private JFXTextField FeeStdLevel;

    @FXML
    private JFXTextField FeeStdCourse;

//    @FXML
//    private JFXTextField FeeDedDate;

    @FXML
    private JFXDatePicker FeeDedDate;


    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        feeDetailsPane.getChildren().setAll(pane);

    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void addFeeDet(ActionEvent event){
        LocalDate ld= FeeDedDate.getValue();
        System.out.println(ld);
        try
        {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            FeeDetails fd = new FeeDetails();
            fd.setFee_Amt(FeeAmt.getText());
            fd.setDeadline_Date(ld);
            fd.setFee_Details(FeeDet.getText());
            fd.setStudent_course(FeeStdCourse.getText());
            fd.setStudent_level(FeeStdLevel.getText());
            fdd.addFeeDet(fd);
            System.out.println(FeeStdLevel.getText());
            System.out.println(FeeStdCourse.getText());
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Fee Detail Added");
            alert.setContentText("Fee Details have been successfully added.");
            alert.showAndWait();
            FeeAmt.clear();
            FeeDet.setText(null);
            //FeeDedDate.setDa
            FeeStdCourse.setText(null);
            FeeStdLevel.clear();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
