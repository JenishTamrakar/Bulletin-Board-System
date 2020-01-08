package controllers;

import bll.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable
{

    String userID=loginScreenController.le;

    @FXML
    private AnchorPane studentDashboard;
    @FXML
    private AnchorPane studentProfile;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCourse;

    @FXML
    private JFXTextField txtLevel;

    @FXML
    private JFXButton changePasswordBtn;

    @FXML
    private JFXTextField txtStudentID;

    @FXML
    private JFXButton logOutBtn;

    @FXML
    private JFXButton editProfileBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private Pane changePwPane;

    @FXML
    private JFXButton updateProfileBtn;

    @FXML
    private JFXTextField newPassword;

    @FXML
    private JFXTextField confirmPassword;

    @FXML
    private JFXButton updatePasswordBtn;

    @FXML
    void backClicked(ActionEvent event) {

    }


    @FXML
    void changePassword(ActionEvent event) {
        changePasswordBtn.setOnMouseClicked((event1 -> {
            changePwPane.setVisible(true);
        }));
    }

    @FXML
    void editProfile(ActionEvent event) {
        editProfileBtn.setOnMouseClicked(event1 -> {
            txtStudentID.setEditable(true);
            txtName.setEditable(true);
            txtEmail.setEditable(true);
            txtCourse.setEditable(true);
            txtLevel.setEditable(true);
            updateProfileBtn.setVisible(true);
        });
    }

    @FXML
    void updateProfile(ActionEvent event) {
        try
        {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            Student s = new Student();
            s.setStudent_ID(txtStudentID.getText());
            s.setName(txtName.getText());
            s.setEmail(txtEmail.getText());
            s.setCourse(txtCourse.getText());
            s.setLevel(txtLevel.getText());
            sd.updateStudent(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Updated");
            alert.setContentText("Student Record Successfully Updated!");
            alert.showAndWait();
            txtStudentID.setEditable(false);
            txtName.setEditable(false);
            txtEmail.setEditable(false);
            txtCourse.setEditable(false);
            txtLevel.setEditable(false);
            updateProfileBtn.setVisible(false);
            //loadStudentProfile();

            //loadStudentData();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void updatePassword(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentDashboard.fxml")));
        studentProfile.getChildren().setAll(pane);

    }
    @FXML
    private TableColumn<Student, String> student_Email;
    ObservableList<Student> slist = FXCollections.observableArrayList();


    void loadStudentProfile()
    {
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getProfile(userID);
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

            txtStudentID.setText(slist.get(0).getStudent_ID());
            txtName.setText(slist.get(0).getName());
            txtCourse.setText(slist.get(0).getCourse());
            txtEmail.setText(slist.get(0).getEmail());
            txtLevel.setText(slist.get(0).getLevel());
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadStudentProfile();
    }
}
