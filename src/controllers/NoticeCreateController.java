package controllers;

import bll.Notice;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.NoticeDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class NoticeCreateController {
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
    void showDate(ActionEvent event) {
        LocalDate ld = pickDate.getValue();
    }
    @FXML
    void addNotice(ActionEvent event) throws RemoteException, NotBoundException {
    try{
        NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
        Notice n = new Notice();

    }catch (Exception e){
        System.out.println(e);
    }
    }

}

