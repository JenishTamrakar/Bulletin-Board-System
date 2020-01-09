/**
 * @author Susan Tamrakar
 * This controller is used to control the faculty actions.
 */

package controllers;

import bll.Faculty;
import dao.FacultyDao;
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

public class facultyDashboardController implements Initializable {
    String userID=loginScreenController.le;

    @FXML
    private AnchorPane facultyDashboardPane;

    @FXML
    private Label txtFacultyName;

    //navigation to assignments window
    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreateFaculty.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventFaculty.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    //navigation to notices window
    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyNotice.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    //navigation to lodin screen window
    @FXML
    void logOutClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    //navigation to profle window
    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/facultyProfile.fxml")));
        facultyDashboardPane.getChildren().setAll(pane);
    }

    @FXML
    private TableColumn<Faculty, String> student_Email;
    ObservableList<Faculty> slist = FXCollections.observableArrayList();


    /**
     * load faculty details from faculty table
     */
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

                txtFacultyName.setText(slist.get(0).getName());
//                txtName.setText(slist.get(0).getName());
//                txtCourse.setText(slist.get(0).getCourse());
//                txtEmail.setText(slist.get(0).getEmail());

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
        loadFacultyProfile();
    }
}
