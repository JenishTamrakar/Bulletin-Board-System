package controllers;

import bll.Faculty;
import dao.FacultyDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FacultyRecordsController
{

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
    private TreeTableView<?> FacRecTable;

    @FXML
    private TreeTableColumn<?, ?> FacID;

    @FXML
    private TreeTableColumn<?, ?> FacName;

    @FXML
    private TreeTableColumn<?, ?> FacCourse;

    @FXML
    private TreeTableColumn<?, ?> FacEmail;

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
            sd.addStudent(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setContentText("Faculty Record Successfully Added!");

            //if (alert.getResult() == ButtonType.YES)
            //{
            //	AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/loginScreen.fxml"));
            //	registerPane.getChildren().setAll(pane);
            //}
            alert.showAndWait();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void updtFacRecClicked(ActionEvent event) {

    }


}
