package controllers;

import bll.Assignment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dao.AssignmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.Naming;
import java.time.LocalDate;

public class AssignmentCreateController {


    @FXML
    private AnchorPane assignmentPane;
    @FXML
    private JFXTextField AssignTitle;

    @FXML
    private JFXButton AddAssign;

    @FXML
    private JFXButton UpdateAssign;

    @FXML
    private TreeTableView<?> NtcTable;

    @FXML
    private TreeTableColumn<?, ?> AssignIDTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignTitleTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignLvlTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignCrseTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignUnitTbl;

    @FXML
    private TreeTableColumn<?, ?> DeadDateTbl;

    @FXML
    private JFXDatePicker DeadDate;

    @FXML
    private JFXTextField AssignID;

    @FXML
    private JFXTextField AssignLvl;

    @FXML
    private JFXTextField AssignCrse;

    @FXML
    private JFXTextField AssignUnit;

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        assignmentPane.getChildren().setAll(pane);
    }
    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }


    @FXML
    void goToFeeDetails(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    @FXML
    void addAssClicked(ActionEvent actionEvent)
    {
        LocalDate ld = DeadDate.getValue();
        try
        {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
            Assignment as = new Assignment();
            as.setAss_title(AssignTitle.getText());
            as.setAss_level(AssignLvl.getText());
            as.setAss_course(AssignCrse.getText());
            as.setAss_unit(AssignUnit.getText());
            as.setAss_date(ld);
            ad.addAssDet(as);
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Assignment Added");
            alert.setContentText("Assignment has been successfully added.");
            alert.showAndWait();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
