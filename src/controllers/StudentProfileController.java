package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentProfileController {
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
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentDashboard.fxml")));
        studentProfile.getChildren().setAll(pane);

    }

}
