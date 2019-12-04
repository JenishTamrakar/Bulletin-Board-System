package controllers;

import bll.Notice;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.NoticeDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class NoticeCreateController {

    @FXML
    private AnchorPane noticePane;
    @FXML
    private JFXTextArea NoticeDesc;

    @FXML
    private JFXTextField NoticeTitle;
    @FXML
    private  JFXTextField NoticeDate;

    @FXML
    private JFXButton AddNtc;

    @FXML
    private JFXButton UpdateNtc;

    @FXML
    private TreeTableView<?> NtcTable;

    @FXML
    private TreeTableColumn<?, ?> NtcTitleTbl;

    @FXML
    private TreeTableColumn<?, ?> NtcIssueDateTbl;

    @FXML
    private TreeTableColumn<?, ?> NtcDescTbl;

    @FXML
    private DatePicker pickDate;

    @FXML
    void goToStudentRecords(javafx.event.ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        noticePane.getChildren().setAll(pane);
    }
    @FXML
    void goToEvents(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        noticePane.getChildren().setAll(pane);
    }
    @FXML
    void goToAssignments(javafx.event.ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        noticePane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(javafx.event.ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        noticePane.getChildren().setAll(pane);
    }
    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        noticePane.getChildren().setAll(pane);
    }


    @FXML
    void addNotice(ActionEvent event) throws RemoteException, NotBoundException {
    try{
        NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
        Notice n = new Notice();
        n.setNoticeTitle(NoticeTitle.getText());
        n.setNoticeDate(NoticeDate.getText());
        n.setNoticeDescription(NoticeDesc.getText());
        nd.addNotice(n);

    }catch (Exception e){
        System.out.println(e);
    }
    }

}

