/**
 * @author Jenish Tamrakar
 * This controller class is used to add, update and delete the notices by the admin.
 */

package controllers;

import bll.Notice;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.NoticeDao;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NoticeCreateController implements Initializable {

    @FXML
    private AnchorPane noticePane;
    @FXML
    private JFXTextArea NoticeDesc;

    @FXML
    private JFXTextField NoticeTitle;

    @FXML
    private JFXButton AddNtc;

    @FXML
    private JFXButton UpdtNtc;

    @FXML
    private JFXDatePicker NoticeDate;

    @FXML
    private JFXButton DelNtc;

    @FXML
    private JFXButton ResetBtn;

    @FXML
    private TableView<Notice> NoticeTbl;

    @FXML
    private TableColumn<Notice, String> notice_SN;

    @FXML
    private TableColumn<Notice, String> notice_Title;

    @FXML
    private TableColumn<Notice, String> notice_Desc;

    @FXML
    private TableColumn<Notice, String> notice_Date;

    @FXML
    private JFXTextField NtcSN;

    ObservableList<Notice> nlist = FXCollections.observableArrayList();

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to student window
    @FXML
    void goToStudentRecords(javafx.event.ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to faculty record window
    @FXML
    void goToFacultyRecords(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to assignments window
    @FXML
    void goToAssignments(javafx.event.ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDetails(javafx.event.ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    //navigation to dashboard window
    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    /**
     * add notice details
     * @param event
     * @throws RemoteException
     * @throws NotBoundException
     */
    @FXML
    void addNotice(ActionEvent event) throws RemoteException, NotBoundException {
        LocalDate ld = NoticeDate.getValue();
        LocalDate l = LocalDate.now();
        try{
        NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
        Notice n = new Notice();
        n.setNoticeTitle(NoticeTitle.getText());
        n.setNoticeDate(NoticeDate.getValue().toString());
        n.setNoticeDescription(NoticeDesc.getText());

        if(ld.isAfter(l))
        {
            nd.addNotice(n);

            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Notice Added");
            alert.setContentText("Notice has been successfully posted.");
            alert.showAndWait();

            NtcSN.clear();
            NoticeTitle.clear();
            NoticeDesc.clear();
            NoticeDate.setValue(null);
            NoticeTbl.getItems().clear();
            loadNotices();
        }
        else if(ld.isBefore(l))
        {
            Alert alert = new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Incorrect Date");
            alert.setContentText("The date cannot be set.");
            alert.showAndWait();
        }

        }catch (Exception e){
        System.out.println(e);
        }
    }

    //reset data from the textfields
    @FXML
    void resetBtnClicked(ActionEvent event)
    {
        NtcSN.clear();
        NoticeTitle.clear();
        NoticeDesc.clear();
        NoticeDate.setValue(null);
    }

    /**
     * update notice details
     * @param event
     */
    @FXML
    void updtNtcClicked(ActionEvent event)
    {
        LocalDate ld = NoticeDate.getValue();
        LocalDate l = LocalDate.now();
        try{
            NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
            Notice n = new Notice();
            n.setNoticeID(NtcSN.getText());
            n.setNoticeTitle(NoticeTitle.getText());
            n.setNoticeDate(NoticeDate.getValue().toString());
            n.setNoticeDescription(NoticeDesc.getText());
            if(ld.isAfter(l))
            {
                nd.updateNotice(n);
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Notice Updated");
                alert.setContentText("Notice has been successfully updated.");
                alert.showAndWait();

                NtcSN.clear();
                NoticeTitle.clear();
                NoticeDesc.clear();
                NoticeDate.setValue(null);
                NoticeTbl.getItems().clear();
                loadNotices();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * delete notice details
     * @param event
     */
    @FXML
    void delNtcClicked(ActionEvent event)
    {
        try {
            NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
            Notice n = new Notice();
            n.setNoticeID(NtcSN.getText());
            nd.deleteNotice(n);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notice Removed");
            alert.setContentText("Notice Successfully Removed!");

            NtcSN.clear();
            NoticeTitle.clear();
            NoticeDesc.clear();
            NoticeDate.setValue(null);
            NoticeTbl.getItems().clear();
            loadNotices();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * select row data from the table
     * @param event
     */
    @FXML
    void NoticeTblClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    //edit data from the table
    void onEdit()
    {
        // check the table's selected item and get selected item
        if (NoticeTbl.getSelectionModel().getSelectedItem() != null) {
            Notice selectedNotice = NoticeTbl.getSelectionModel().getSelectedItem();
            NtcSN.setText(selectedNotice.getNoticeID());
            NoticeDate.setValue(LocalDate.parse(selectedNotice.getNoticeDate()));
            NoticeDesc.setText(selectedNotice.getNoticeDescription());
            NoticeTitle.setText(selectedNotice.getNoticeTitle());
        }
    }

    /**
     *load notice data from the notic table
     */
    void loadNotices()
    {
        try {
            NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
            ResultSet rs = nd.getNoticeDetails();

            while(rs.next())
            {
                nlist.add(new Notice(
                        rs.getString("notice_id"),
                        rs.getString("notice_title"),
                        rs.getString("notice_date"),
                        rs.getString("notice_description")
                ));

                notice_SN.setCellValueFactory(new PropertyValueFactory<>("noticeID"));
                notice_Title.setCellValueFactory(new PropertyValueFactory<>("noticeTitle"));
                notice_Desc.setCellValueFactory(new PropertyValueFactory<>("noticeDescription"));
                notice_Date.setCellValueFactory(new PropertyValueFactory<>("noticeDate"));
                NoticeTbl.setItems(nlist);
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
    public void initialize(URL location, ResourceBundle resources)
    {
        loadNotices();
    }
}

