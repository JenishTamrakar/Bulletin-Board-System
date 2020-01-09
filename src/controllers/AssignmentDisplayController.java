package controllers;

import bll.Assignment;
import bll.Student;
import dao.AssignmentDao;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

public class AssignmentDisplayController implements Initializable {

    String userID=loginScreenController.le;

    @FXML
    private AnchorPane studentAssignmentPane;

    @FXML
    private TableView<Assignment> AssignmentTbl;

    @FXML
    private TableColumn<Assignment, String> assign_SN;

    @FXML
    private TableColumn<Assignment, String> assign_Title;

    @FXML
    private TableColumn<Assignment, String> assign_Unit;

    @FXML
    private TableColumn<Assignment, String> assign_Deadline;

    @FXML
    private Label txtStudentName;

    public static String course;
    public static String level;

    ObservableList<Student> slist = FXCollections.observableArrayList();
    ObservableList<Assignment> aslist = FXCollections.observableArrayList();

    /**
     * load assignment details from assignment table into te table
     */
    void loadAssignDetails()
    {
        try {
            AssignmentDao ad = (AssignmentDao) Naming.lookup("rmi://localhost/Assignment");
//            System.out.println("Course = "+ course);
//            System.out.println("Course = "+ level);
            ResultSet rs = ad.getAssignDetailsByCourseAndLevel(course, level);

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
                assign_SN.setCellValueFactory(new PropertyValueFactory<>("Ass_ID"));
                assign_Title.setCellValueFactory(new PropertyValueFactory<>("Ass_title"));
//                ass_Course.setCellValueFactory(new PropertyValueFactory<>("Ass_course"));
//                ass_Level.setCellValueFactory(new PropertyValueFactory<>("Ass_level"));
                assign_Unit.setCellValueFactory(new PropertyValueFactory<>("Ass_unit"));
                assign_Deadline.setCellValueFactory(new PropertyValueFactory<>("Ass_date"));
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
     * load student data from the student table into the required textfields
     */
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

                txtStudentName.setText(slist.get(0).getName());
//                txtName.setText(slist.get(0).getName());
                course = slist.get(0).getCourse();
//                txtEmail.setText(slist.get(0).getEmail());
                level = slist.get(0).getLevel();
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

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentFeeDet.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    //navigation to feedback window
    @FXML
    void goToFeedback(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/giveFeedback.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    //navigation to notice window
    @FXML
    void goToNotice(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentNotice.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    //navigation to profile window
    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        studentAssignmentPane.getChildren().setAll(pane);
    }

    /**
     * initilaize the methods
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadStudentProfile();
        loadAssignDetails();
    }
}
