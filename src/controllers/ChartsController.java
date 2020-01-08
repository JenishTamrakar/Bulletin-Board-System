package controllers;

import com.jfoenix.controls.JFXButton;
import dao.FacultyDao;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {

    @FXML
    private AnchorPane chartPane;

    @FXML
    private JFXButton SgnOut;

    @FXML
    private PieChart studentCourseChart;

    @FXML
    private PieChart studentLevelChart;

    @FXML
    private PieChart facultyCourseChart;
    private ObservableList<PieChart.Data> dataStudentCourse= FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> dataStudentLevel= FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> dataFacultyCourse= FXCollections.observableArrayList();

    @FXML
    void goToAssignments(ActionEvent event) {

    }

    @FXML
    void goToDashboard(ActionEvent event) {

    }

    @FXML
    void goToEvents(ActionEvent event) {

    }

    @FXML
    void goToFacultyRecords(ActionEvent event) {

    }

    @FXML
    void goToFeeDetails(ActionEvent event) {

    }

    @FXML
    void goToNotices(ActionEvent event) {

    }

    @FXML
    void goToStudentRecords(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    void loadStudentCoursePieChart(){
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getStudentRecordsByCourse();
            while (rs.next()){
                dataStudentCourse.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
            studentCourseChart.setData(dataStudentCourse);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadStudentLevelPieChart(){
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getStudentRecordsByLevel();
            while (rs.next()){
                dataStudentLevel.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
            studentLevelChart.setData(dataStudentLevel);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadFacultyCoursePieChart(){
        try {
            FacultyDao fd = (FacultyDao) Naming.lookup("rmi://localhost/Faculty");
            ResultSet rs = fd.getFacultyRecordsByCourse();
            while (rs.next()){
                dataFacultyCourse.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
            facultyCourseChart.setData(dataFacultyCourse);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentCoursePieChart();
        loadStudentLevelPieChart();
        loadFacultyCoursePieChart();
    }
}
