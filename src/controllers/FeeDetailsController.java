package controllers;

import bll.FeeDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.FeeDetailsDao;
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


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FeeDetailsController implements Initializable {

    @FXML
    private AnchorPane feeDetailsPane;

    @FXML
    private JFXButton eventsTop;

    @FXML
    private JFXButton facultyTop;

    @FXML
    private JFXButton assignmentsTop;

    @FXML
    private JFXButton studentRecordTop;

    @FXML
    private JFXButton studentRecordTop1;

    @FXML
    private JFXTextArea FeeDet;

    @FXML
    private JFXTextField FeeAmt;

    @FXML
    private JFXButton AddFeeDet;

    @FXML
    private JFXButton UpdtFeeDet;

    @FXML
    private JFXButton DelFeeDet;

    @FXML
    private JFXButton ResetBtn;

    @FXML
    private TableView<FeeDetails> FeeTbl;

    @FXML
    private TableColumn<FeeDetails, String> fee_SN;

    @FXML
    private TableColumn<FeeDetails, String> fee_Course;

    @FXML
    private TableColumn<FeeDetails, String> fee_Level;

    @FXML
    private TableColumn<FeeDetails, String> fee_Details;

    @FXML
    private TableColumn<FeeDetails, String> fee_Amt;

    @FXML
    private TableColumn<FeeDetails, String> fee_Deadline;

    @FXML
    private JFXTextField DedDate;

    @FXML
    private JFXTextField FeeStdID;
    @FXML
    private JFXTextField FeeStdLevel;

    @FXML
    private JFXTextField FeeStdCourse;

    @FXML
    private JFXTextField FeeSN;

//    @FXML
//    private JFXTextField FeeDedDate;

    @FXML
    private JFXDatePicker FeeDedDate;

    ObservableList<FeeDetails> fdlist = FXCollections.observableArrayList();

    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        feeDetailsPane.getChildren().setAll(pane);

    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void addFeeDet(ActionEvent event){
        LocalDate ld= FeeDedDate.getValue();
        LocalDate l = LocalDate.now();
//        System.out.println(ld);

        try
        {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            FeeDetails fd = new FeeDetails();
            fd.setFee_Amt(FeeAmt.getText());
            fd.setDeadline_Date(ld.toString());
            fd.setFee_Details(FeeDet.getText());
            fd.setStudent_course(FeeStdCourse.getText());
            fd.setStudent_level(FeeStdLevel.getText());
            if(ld.isAfter(l)) {
                fdd.addFeeDet(fd);
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Fee Detail Added");
                alert.setContentText("Fee Details have been successfully added.");
                alert.showAndWait();
                FeeSN.clear();
                FeeAmt.clear();
                FeeDet.clear();
                FeeDedDate.setValue(null);
                FeeStdCourse.clear();
                FeeStdLevel.clear();
                FeeTbl.getItems().clear();
                loadFeeDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void loadFeeDetails()
    {
        try {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            ResultSet rs = fdd.getFeeDetails();

            while(rs.next())
            {
                fdlist.add(new FeeDetails(
                        rs.getString("fee_id"),
                        rs.getString("fee_amount"),
                        rs.getString("fee_deadline_date"),
                        rs.getString("fee_details"),
                        rs.getString("student_course"),
                        rs.getString("student_level")
                ));

                fee_SN.setCellValueFactory(new PropertyValueFactory<>("Fee_ID"));
                fee_Amt.setCellValueFactory(new PropertyValueFactory<>("Fee_Amt"));
                fee_Course.setCellValueFactory(new PropertyValueFactory<>("Student_course"));
                fee_Level.setCellValueFactory(new PropertyValueFactory<>("Student_level"));
                fee_Details.setCellValueFactory(new PropertyValueFactory<>("Fee_Details"));
                fee_Deadline.setCellValueFactory(new PropertyValueFactory<>("Deadline_Date"));
                FeeTbl.setItems(fdlist);
//                System.out.println(fdlist);
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
    void resetBtnClicked(ActionEvent event)
    {
        FeeSN.clear();
        FeeAmt.clear();
        FeeDet.clear();
        FeeDedDate.setValue(null);
        FeeStdCourse.clear();
        FeeStdLevel.clear();
    }

    @FXML
    void updtFeeDetClicked(ActionEvent event)
    {
        LocalDate ld= FeeDedDate.getValue();
        LocalDate l = LocalDate.now();
        try
        {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            FeeDetails fd = new FeeDetails();

            fd.setFee_ID(FeeSN.getText());
            fd.setFee_Amt(FeeAmt.getText());
            fd.setFee_Details(FeeDet.getText());
            fd.setDeadline_Date(ld.toString());
            fd.setStudent_course(FeeStdCourse.getText());
            fd.setStudent_level(FeeStdLevel.getText());
            if(ld.isAfter(l))
            {
                fdd.updateFeeDet(fd);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Record Updated");
                alert.setContentText("Fee Details Successfully Updated!");
                alert.showAndWait();
                FeeSN.clear();
                FeeAmt.clear();
                FeeDet.clear();
                FeeDedDate.setValue(null);
                FeeStdCourse.clear();
                FeeStdLevel.clear();
                FeeTbl.getItems().clear();
                loadFeeDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    @FXML
    void delFeeDetClicked(ActionEvent event)
    {
        try {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            FeeDetails fd = new FeeDetails();
            fd.setFee_ID(FeeSN.getText());
            fdd.deleteFeeDet(fd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Removed");
            alert.setContentText("Fee Record Successfully Removed!");

            alert.showAndWait();
            FeeSN.clear();
            FeeAmt.clear();
            FeeDet.clear();
            FeeDedDate.setValue(null);
            FeeStdCourse.clear();
            FeeStdLevel.clear();
            FeeTbl.getItems().clear();
            loadFeeDetails();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void FeeTblMouseClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    void onEdit()
    {
        // check the table's selected item and get selected item
        if (FeeTbl.getSelectionModel().getSelectedItem() != null) {
            FeeDetails selectedFee = FeeTbl.getSelectionModel().getSelectedItem();
            FeeSN.setText(selectedFee.getFee_ID());
//            LocalDate ld = LocalDate.parse(selectedFaculty.getDeadline_Date());
            FeeDedDate.setValue(LocalDate.parse(selectedFee.getDeadline_Date()));
//            FeeDedDate.setValue(LocalDate(selectedFaculty.getDeadline_Date()));
            FeeAmt.setText(selectedFee.getFee_Amt());
            FeeDet.setText(selectedFee.getFee_Details());
            FeeStdCourse.setText(selectedFee.getStudent_course());
            FeeStdLevel.setText(selectedFee.getStudent_level());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadFeeDetails();
    }
}
