package controllers;

import bll.Student;
import dao.StudentDao;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentRecordsController implements Initializable
{
    @FXML
    private AnchorPane studentRecordPane;

    @FXML
    private JFXButton AddStdRec;

    @FXML
    private JFXButton UpdtStdRec;

    @FXML
    private JFXButton DelStdRec;

    @FXML
    private JFXButton ResetBtn;

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
    private JFXTextField StdSN;

    @FXML
    private TableView<Student> StudentTbl;

    @FXML
    private TableColumn<Student, String> student_SN;

    @FXML
    private TableColumn<Student, String> student_ID;

    @FXML
    private TableColumn<Student, String> student_Name;

    @FXML
    private TableColumn<Student, String> student_Course;

    @FXML
    private TableColumn<Student, String> student_Level;

    @FXML
    private TableColumn<Student, String> student_Email;

    ObservableList<Student> slist = FXCollections.observableArrayList();

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        studentRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        studentRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        studentRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToAssignments(ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        studentRecordPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        studentRecordPane.getChildren().setAll(pane);
    }
    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        studentRecordPane.getChildren().setAll(pane);
    }


    @FXML
    void addStdRecClicked(ActionEvent event) throws RemoteException, NotBoundException {
        try
        {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            Student s = new Student();
            s.setStudent_SN(StdSN.getText());
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
            StdSN.setText(null);
            EntrStdID.setText(null);
            EntrStdName.setText(null);
            EntrStdEmail.setText(null);
            EntrStdCourse.setText(null);
            EntrStdLvl.setText(null);
            StudentTbl.getItems().clear();
            loadStudentData();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void updtStdRecClicked(ActionEvent event)
    {
        try
        {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            Student s = new Student();
            s.setStudent_SN(StdSN.getText());
            s.setStudent_ID(EntrStdID.getText());
            s.setName(EntrStdName.getText());
            s.setEmail(EntrStdEmail.getText());
            s.setCourse(EntrStdCourse.getText());
            s.setLevel(EntrStdLvl.getText());
            //System.out.println(r.getUID());
            //System.out.println(r.getPassword());
            sd.updateStudent(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Updated");
            alert.setContentText("Student Record Successfully Updated!");

//            if (alert.getResult() == ButtonType.YES)
//            {
//            	AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/StudentRecords.fxml"));
//            	studentRecordPane.getChildren().setAll(pane);
//            }

            alert.showAndWait();
            StdSN.setText(null);
            EntrStdID.setText(null);
            EntrStdName.setText(null);
            EntrStdEmail.setText(null);
            EntrStdCourse.setText(null);
            EntrStdLvl.setText(null);
            StudentTbl.getItems().clear();
            loadStudentData();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void delStdRecClicked(ActionEvent event) {
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            Student s = new Student();
            s.setStudent_SN(StdSN.getText());
            sd.deleteStudent(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Removed");
            alert.setContentText("Student Record Successfully Removed!");

//            if (alert.getResult() == ButtonType.YES)
//            {
//            	AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/StudentRecords.fxml"));
//            	studentRecordPane.getChildren().setAll(pane);
//            }

            alert.showAndWait();
            StdSN.setText(null);
            EntrStdID.setText(null);
            EntrStdName.setText(null);
            EntrStdEmail.setText(null);
            EntrStdCourse.setText(null);
            EntrStdLvl.setText(null);
            StudentTbl.getItems().clear();
            loadStudentData();
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    @FXML
    void resetBtnClicked(ActionEvent event)
    {
        StdSN.setText(null);
        EntrStdID.setText(null);
        EntrStdName.setText(null);
        EntrStdEmail.setText(null);
        EntrStdCourse.setText(null);
        EntrStdLvl.setText(null);
    }

    void loadStudentData()
    {
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getStudentRecords();

            while(rs.next())
            {
                slist.add(new Student(
                        rs.getString("student_sn"),
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_course"),
                        rs.getString("student_email"),
                        rs.getString("student_level")
                ));

                student_SN.setCellValueFactory(new PropertyValueFactory<>("student_SN"));
                student_ID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
                student_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
                student_Course.setCellValueFactory(new PropertyValueFactory<>("course"));
                student_Level.setCellValueFactory(new PropertyValueFactory<>("level"));
                student_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
                StudentTbl.setItems(slist);
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

    @FXML
    void StudentTblMouseClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    void onEdit() {
        // check the table's selected item and get selected item
        if (StudentTbl.getSelectionModel().getSelectedItem() != null) {
            Student selectedStudent = StudentTbl.getSelectionModel().getSelectedItem();
            StdSN.setText(selectedStudent.getStudent_SN());
            EntrStdID.setText(selectedStudent.getStudent_ID());
            EntrStdName.setText(selectedStudent.getName());
            EntrStdLvl.setText(selectedStudent.getLevel());
            EntrStdCourse.setText(selectedStudent.getCourse());
            EntrStdEmail.setText(selectedStudent.getEmail());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadStudentData();
    }
}
