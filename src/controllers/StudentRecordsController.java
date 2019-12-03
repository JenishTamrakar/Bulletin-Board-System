package controllers;

import bll.Student;
import dao.StudentDao;
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

public class StudentRecordsController
{

    @FXML
    private JFXButton AddStdRec;

    @FXML
    private JFXButton UpdtStdRec;

    @FXML
    private JFXTextField SearchStdRecTable;

    @FXML
    private JFXTextField EntrStdID;

    @FXML
    private JFXTextField EntrStdName;

    @FXML
    private JFXTextField EntrStdLvl;

    @FXML
    private JFXTextField EntrStdCourse;

    @FXML
    private JFXTextField EntrStdEmail;

    @FXML
    private TreeTableView<?> StdRecTable;

    @FXML
    private TreeTableColumn<?, ?> StdID;

    @FXML
    private TreeTableColumn<?, ?> StdName;

    @FXML
    private TreeTableColumn<?, ?> StdLvl;

    @FXML
    private TreeTableColumn<?, ?> StdCourse;

    @FXML
    private TreeTableColumn<?, ?> StdEmail;

    @FXML
    void addStdRecClicked(ActionEvent event) throws RemoteException, NotBoundException {
        try
        {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            Student s = new Student();
            s.setStudent_ID(EntrStdID.getText());
            s.setName(EntrStdName.getText());
            s.setEmail(EntrStdEmail.getText());
            s.setCourse(EntrStdCourse.getText());
            s.setLevel(EntrStdLvl.getText());
            //System.out.println(r.getUID());
            //System.out.println(r.getPassword());
            sd.addStudent(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Added");
            alert.setContentText("Student Record Successfully Added!");

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
    void updtStdRecClicked(ActionEvent event) {

    }


}
