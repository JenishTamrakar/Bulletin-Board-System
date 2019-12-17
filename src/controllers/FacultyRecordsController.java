package controllers;

import bll.Faculty;
import dao.FacultyDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class FacultyRecordsController implements Initializable
{
    @FXML
    private AnchorPane facultyRecordPane;

    @FXML
    private JFXButton AddFacRec;

    @FXML
    private JFXButton UpdtFacRec;

    @FXML
    private JFXTextField SearchFacRecTable;

    @FXML
    private JFXTextField EntrFacID;

    @FXML
    private JFXTextField EntrFacName;

    @FXML
    private JFXTextField EntrFacCourse;

    @FXML
    private JFXTextField EntrFacEmail;

    @FXML
    private JFXTextField FacSN;

    @FXML
    private JFXButton DelFacRec;

    @FXML
    private JFXButton ResetBtn;

    @FXML
    private TableView<Faculty> FacultyTbl;

    @FXML
    private TableColumn<Faculty, String> faculty_SN;

    @FXML
    private TableColumn<Faculty, String> faculty_id;

    @FXML
    private TableColumn<Faculty, String> faculty_name;

    @FXML
    private TableColumn<Faculty, String> faculty_course;

    @FXML
    private TableColumn<Faculty, String> faculty_email;

    ObservableList<Faculty> flist = FXCollections.observableArrayList();

    @FXML
    void goToNotices(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        facultyRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToStudentRecords(javafx.event.ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        facultyRecordPane.getChildren().setAll(pane);
    }

    @FXML
    void goToEvents(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        facultyRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToAssignments(javafx.event.ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        facultyRecordPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(javafx.event.ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        facultyRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToDashboard(javafx.event.ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        facultyRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void addFacRecClicked(ActionEvent event) {
        try
        {
            FacultyDao sd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            Faculty f = new Faculty();
            f.setFaculty_ID(EntrFacID.getText());
            f.setName(EntrFacName.getText());
            f.setEmail(EntrFacEmail.getText());
            f.setCourse(EntrFacCourse.getText());
            //System.out.println(r.getUID());
            //System.out.println(r.getPassword());
            sd.addFaculty(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setContentText("Faculty Record Successfully Added!");

            //if (alert.getResult() == ButtonType.YES)
            //{
            //	AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/loginScreen.fxml"));
            //	registerPane.getChildren().setAll(pane);
            //}
            alert.showAndWait();
            FacSN.setText(null);
            EntrFacID.setText(null);
            EntrFacName.setText(null);
            EntrFacEmail.setText(null);
            EntrFacCourse.setText(null);
            FacultyTbl.getItems().clear();
            loadFacultyData();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    void loadFacultyData()
    {
        try
        {
            FacultyDao fd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            ResultSet rs = fd.getFacultyRecords();

            while(rs.next())
            {
                flist.add(new Faculty(
                        rs.getString("faculty_sn"),
                        rs.getString("faculty_id"),
                        rs.getString("faculty_name"),
                        rs.getString("faculty_course"),
                        rs.getString("faculty_email"))
                );
//                System.out.println(flist.get(0));
//                System.out.println(rs.getString("faculty_id"));
//                System.out.println(rs.getString("faculty_name"));
//                System.out.println(rs.getString("faculty_course"));
//                System.out.println(rs.getString("faculty_email"));
            }

            faculty_SN.setCellValueFactory(new PropertyValueFactory<>("faculty_SN"));
            faculty_id.setCellValueFactory(new PropertyValueFactory<>("faculty_ID"));
            faculty_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            faculty_course.setCellValueFactory(new PropertyValueFactory<>("course"));
            faculty_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            FacultyTbl.setItems(flist);
//            faculty_id.getCellFactory();
//            System.out.println(flist);
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    @FXML
    void updtFacRecClicked(ActionEvent event)
    {
        try
        {
            FacultyDao fd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            Faculty f = new Faculty();
            f.setFaculty_SN(FacSN.getText());
            f.setFaculty_ID(EntrFacID.getText());
            f.setName(EntrFacName.getText());
            f.setEmail(EntrFacEmail.getText());
            f.setCourse(EntrFacCourse.getText());
            //System.out.println(r.getUID());
            //System.out.println(r.getPassword());
            fd.updateFaculty(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Updated");
            alert.setContentText("Faculty Record Successfully Updated!");

            //if (alert.getResult() == ButtonType.YES)
            //{
            //	AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/loginScreen.fxml"));
            //	registerPane.getChildren().setAll(pane);
            //}
            alert.showAndWait();
            FacSN.setText(null);
            EntrFacID.setText(null);
            EntrFacName.setText(null);
            EntrFacEmail.setText(null);
            EntrFacCourse.setText(null);
            FacultyTbl.getItems().clear();
            loadFacultyData();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void FacultyTblMouseClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    @FXML
    void delFacClicked(ActionEvent event)
    {
        try {
            FacultyDao fd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            Faculty f = new Faculty();
            f.setFaculty_SN(FacSN.getText());
            fd.deleteFaculty(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Removed");
            alert.setContentText("Faculty Record Successfully Removed!");

            alert.showAndWait();
            FacSN.setText(null);
            EntrFacID.setText(null);
            EntrFacName.setText(null);
            EntrFacEmail.setText(null);
            EntrFacCourse.setText(null);
            FacultyTbl.getItems().clear();
            loadFacultyData();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void resetBtnClicked(ActionEvent event)
    {
        FacSN.setText(null);
        EntrFacID.setText(null);
        EntrFacName.setText(null);
        EntrFacEmail.setText(null);
        EntrFacCourse.setText(null);
    }

    void onEdit() {
        // check the table's selected item and get selected item
        if (FacultyTbl.getSelectionModel().getSelectedItem() != null) {
            Faculty selectedFaculty = FacultyTbl.getSelectionModel().getSelectedItem();
            FacSN.setText(selectedFaculty.getFaculty_SN());
            EntrFacID.setText(selectedFaculty.getFaculty_ID());
            EntrFacName.setText(selectedFaculty.getName());
            EntrFacCourse.setText(selectedFaculty.getCourse());
            EntrFacEmail.setText(selectedFaculty.getEmail());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadFacultyData();
    }
}
