package controllers;

import bll.Event;
import bll.Student;

import bll.viewFeedback;
import dao.viewFeedbackDao;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class viewFeedbackController implements Initializable {

    @FXML
    private AnchorPane feedbackPane;

    @FXML
    private Button feedbackRecords;
    @FXML
    private TableView<viewFeedback> feedbackTbl;
    @FXML
    private TableColumn<viewFeedback, String> feedbackTitle;
    @FXML
    private TextArea feedbackDescriptionTxt;

    @FXML
    private TextField feedbackTitleTxt;

    @FXML
    private TextField feedbackDateTxt;

    ObservableList<viewFeedback> feedbackList = FXCollections.observableArrayList();

    @FXML
    void goToAssignments(ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        feedbackPane.getChildren().setAll(pane);

    }
    @FXML
    void feedbackRecords() {
        try {
            viewFeedbackDao vd =(viewFeedbackDao) Naming.lookup("rmi://localhost/ViewFeedback");
            ResultSet rs =vd.getFeedback();
            System.out.println(rs);

            while (rs.next()){
                feedbackList.add(new viewFeedback(
                        rs.getString("feedback_id"),
                        rs.getString("feedback_title"),
                        rs.getString("feedback_description"),
                        rs.getString("feedback_date")
                ));
                feedbackTitle.setCellValueFactory(new PropertyValueFactory<>("feedback_title"));
                feedbackTbl.setItems(feedbackList);
//                System.out.println(rs.next("feedback_title"));

            }
        }catch (NotBoundException e){
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources){

        feedbackRecords();
        System.out.println(feedbackList.size());

    }


    public void feedbackTblClicked(MouseEvent event) {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }

        }
    void onEdit()
    {
        // check the table's selected item and get selected item
        if (feedbackTbl.getSelectionModel().getSelectedItem() != null) {
            viewFeedback selectedEvent = feedbackTbl.getSelectionModel().getSelectedItem();
            feedbackTitleTxt.setText(selectedEvent.getFeedback_title());
            feedbackDateTxt.setText(selectedEvent.getFeedback_date());
            feedbackDescriptionTxt.setText(selectedEvent.getFeedback_description());


        }

    }
    @FXML
    void submitFeedback(){
        try {
            viewFeedbackDao vfd = (viewFeedbackDao) Naming.lookup("rmi://localhost/ViewFeedback");
            viewFeedback vf = new viewFeedback();
            vf.setFeedback_title(feedbackTitleTxt.getText());
            vf.setFeedback_description(feedbackDescriptionTxt.getText());
            vf.setFeedback_date(LocalDate.now().toString());
            vfd.addFeedback(vf);
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Feedback Submitted");
            alert.setContentText("Feedback has been successfully submitted.");
            alert.showAndWait();
            feedbackTitleTxt.clear();
            feedbackDescriptionTxt.clear();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
