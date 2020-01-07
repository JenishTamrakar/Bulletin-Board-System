package controllers;

import bll.Faculty;
import bll.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.FacultyDao;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FacultyProfileController implements Initializable {

    String userID=loginScreenController.le;

    @FXML
    private AnchorPane facultyProfile;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCourse;

    @FXML
    private JFXButton changePasswordBtn;

    @FXML
    private JFXTextField txtFacultyID;

    @FXML
    private JFXButton logOutBtn;

    @FXML
    private JFXButton editProfileBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    void backClicked(ActionEvent event) {

    }

    @FXML
    void changePassword(ActionEvent event) {

    }

    @FXML
    void editProfile(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        facultyProfile.getChildren().setAll(pane);

    }
    @FXML
    private TableColumn<Faculty, String> student_Email;
    ObservableList<Faculty> slist = FXCollections.observableArrayList();


    void loadFacultyProfile()
    {
        try {
            FacultyDao fd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            ResultSet rs = fd.getProfile(userID);
            while(rs.next())
            {
                System.out.println(rs.getString("faculty_sn"));
                slist.add(new Faculty(
                        rs.getString("faculty_sn"),
                        rs.getString("faculty_id"),
                        rs.getString("faculty_name"),
                        rs.getString("faculty_course"),
                        rs.getString("faculty_email")
                ));

                txtFacultyID.setText(slist.get(0).getFaculty_ID());
                txtName.setText(slist.get(0).getName());
                txtCourse.setText(slist.get(0).getCourse());
                txtEmail.setText(slist.get(0).getEmail());

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
        loadFacultyProfile();
    }
}
