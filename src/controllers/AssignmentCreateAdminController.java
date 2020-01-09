package controllers;

import bll.Assignment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dao.AssignmentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AssignmentCreateAdminController implements Initializable {


    @FXML
    private AnchorPane assignmentPane;
    @FXML
    private JFXTextField AssignTitle;

    @FXML
    private JFXButton AddAssign;

    @FXML
    private JFXButton UpdtAssDet;
    @FXML
    private TableView<Assignment> AssignmentTbl;

    @FXML
    private TableColumn<Assignment, String> ass_ID;

    @FXML
    private TableColumn<Assignment, String> ass_Title;

    @FXML
    private TableColumn<Assignment, String> ass_Course;

    @FXML
    private TableColumn<Assignment, String> ass_Level;

    @FXML
    private TableColumn<Assignment, String> ass_Unit;

    @FXML
    private TableColumn<Assignment, String> ass_DeadDate;

    @FXML
    private JFXButton DelAssDet;

    @FXML
    private JFXButton ResetBtn;

    @FXML
    private JFXTextField AssSN;

    @FXML
    private JFXDatePicker DeadDate;

//    @FXML
//    private JFXTextField AssignID;

    @FXML
    private JFXTextField AssignLvl;

    @FXML
    private JFXTextField AssignCrse;

    @FXML
    private JFXTextField AssignUnit;

    ObservableList<Assignment> aslist = FXCollections.observableArrayList();

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        assignmentPane.getChildren().setAll(pane);

    }

    //navigation to notices window
    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        assignmentPane.getChildren().setAll(pane);
    }

    //navigation to student records window
    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    //navigation to faculty records window
    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDetails(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    //navigation to dashboard window
    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        assignmentPane.getChildren().setAll(pane);
    }

    /**
     *add new assignments
     * @param actionEvent
     */
    @FXML
    void addAssClicked(ActionEvent actionEvent)
    {
        LocalDate ld = DeadDate.getValue();
        LocalDate l = LocalDate.now();
        try
        {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
            Assignment as = new Assignment();
            as.setAss_title(AssignTitle.getText());
            as.setAss_level(AssignLvl.getText());
            as.setAss_course(AssignCrse.getText());
            as.setAss_unit(AssignUnit.getText());
            as.setAss_date(ld.toString());
            if(ld.isAfter(l)) {
                ad.addAssDet(as);
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Assignment Added");
                alert.setContentText("Assignment has been successfully added.");
                alert.showAndWait();
                AssSN.clear();
                AssignTitle.clear();
                AssignLvl.clear();
                DeadDate.setValue(null);
                AssignCrse.clear();
                AssignUnit.clear();
                AssignmentTbl.getItems().clear();
                loadAssignDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * reset the text fields
     * @param event
     */
    @FXML
    void resetBtnClicked(ActionEvent event)
    {
        AssSN.clear();
        AssignTitle.clear();
        AssignLvl.clear();
        DeadDate.setValue(null);
        AssignCrse.clear();
        AssignUnit.clear();
    }

    /**
     * update assignment details
     * @param event
     */
    @FXML
    void updtAssDetClicked(ActionEvent event)
    {
        LocalDate ld= DeadDate.getValue();
        LocalDate l = LocalDate.now();
        try
        {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
            Assignment as = new Assignment();
            as.setAss_ID(AssSN.getText());
            as.setAss_title(AssignTitle.getText());
            as.setAss_level(AssignLvl.getText());
            as.setAss_course(AssignCrse.getText());
            as.setAss_unit(AssignUnit.getText());
            as.setAss_date(ld.toString());

            if(ld.isAfter(l))
            {
                ad.updateAssDet(as);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Record Updated");
                alert.setContentText("Assignment Details Successfully Updated!");
                alert.showAndWait();
                AssSN.clear();
                AssignTitle.clear();
                AssignLvl.clear();
                DeadDate.setValue(null);
                AssignCrse.clear();
                AssignUnit.clear();
                AssignmentTbl.getItems().clear();
                loadAssignDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    /**
     * delete assignment details
     * @param event
     */
    @FXML
    void delAssDetClicked(ActionEvent event)
    {
        try {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
            Assignment as = new Assignment();
            as.setAss_ID(AssSN.getText());
            ad.deleteAssDet(as);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Removed");
            alert.setContentText("Fee Record Successfully Removed!");

            alert.showAndWait();
            AssSN.clear();
            AssignTitle.clear();
            AssignLvl.clear();
            DeadDate.setValue(null);
            AssignCrse.clear();
            AssignUnit.clear();
            AssignmentTbl.getItems().clear();
            loadAssignDetails();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /**
     * select data row from table to edit data
     * @param event
     */
    @FXML
    void AssignmentTblMouseClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    /**
     * edit the table data from textfield
     */
    void onEdit()
    {
        // check the table's selected item and get selected item
        if (AssignmentTbl.getSelectionModel().getSelectedItem() != null) {
            Assignment selectedAssignment = AssignmentTbl.getSelectionModel().getSelectedItem();
            AssSN.setText(selectedAssignment.getAss_ID());
            DeadDate.setValue(LocalDate.parse(selectedAssignment.getAss_date()));
            AssignTitle.setText(selectedAssignment.getAss_title());
            AssignLvl.setText(selectedAssignment.getAss_level());
            AssignCrse.setText(selectedAssignment.getAss_course());
            AssignUnit.setText(selectedAssignment.getAss_unit());
        }
    }

    /**
     * load assignment details from assignment table into the table
     */
    void loadAssignDetails()
    {
        try {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
            ResultSet rs = ad.getAssignDetails();

            while(rs.next())
            {
                aslist.add(new Assignment(
                        rs.getString("assignment_id"),
                        rs.getString("assignment_title"),
                        rs.getString("assignment_level"),
                        rs.getString("assignment_course"),
                        rs.getString("assignment_unit"),
                        rs.getString("assignment_deadline_date")
                ));

                ass_ID.setCellValueFactory(new PropertyValueFactory<>("Ass_ID"));
                ass_Title.setCellValueFactory(new PropertyValueFactory<>("Ass_title"));
                ass_Course.setCellValueFactory(new PropertyValueFactory<>("Ass_course"));
                ass_Level.setCellValueFactory(new PropertyValueFactory<>("Ass_level"));
                ass_Unit.setCellValueFactory(new PropertyValueFactory<>("Ass_unit"));
                ass_DeadDate.setCellValueFactory(new PropertyValueFactory<>("Ass_date"));
                AssignmentTbl.setItems(aslist);
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * initialization of the methods
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadAssignDetails();
    }
}
