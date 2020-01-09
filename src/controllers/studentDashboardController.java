package controllers;

import bll.Student;
import com.jfoenix.controls.JFXButton;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentDashboardController implements Initializable {

    String userID=loginScreenController.le;

    @FXML
    private AnchorPane studentDashboard;

    @FXML
    private JFXButton LogOut;


    @FXML
    private Label txtStudentName;

    //navigation to assignments window
    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StdAssign.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDet(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentFeeDet.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to feedback window
    @FXML
    void goToFeedback(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/giveFeedback.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to notices window
    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentNotice.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to profile window
    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        studentDashboard.getChildren().setAll(pane);
    }

//    navigation to  window
//    public void logOutClicked(ActionEvent actionEvent) throws IOException {
//        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
//        studentDashboard.getChildren().setAll(pane);
//    }
//
//    public void profileClicked(ActionEvent actionEvent) throws IOException {
//        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
//        studentDashboard.getChildren().setAll(pane);
//    }

    @FXML
    private TableColumn<Student, String> student_Email;
    ObservableList<Student> slist = FXCollections.observableArrayList();

    //load student details from the student table
    void loadStudentProfile()
    {
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getProfile(userID);
            System.out.println(rs);

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

                txtStudentName.setText(slist.get(0).getName());
//                txtName.setText(slist.get(0).getName());
//                txtCourse.setText(slist.get(0).getCourse());
//                txtEmail.setText(slist.get(0).getEmail());
//                txtLevel.setText(slist.get(0).getLevel());
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
     * initialize the methods
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentProfile();
    }
}

