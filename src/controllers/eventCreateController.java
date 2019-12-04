package controllers;

import bll.CreateEvent;
import com.jfoenix.controls.*;
import dao.CreateEventDao;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class eventCreateController {

    @FXML
    private JFXButton SgnOut;

    @FXML
    private JFXTextArea EventDesc;

    @FXML
    private JFXTextField EventTitle;

    @FXML
    private JFXButton AddEvent;

    @FXML
    private JFXButton UpdateEvent;

    @FXML
    private JFXDatePicker EventDate;

    @FXML
    private JFXTimePicker EventTime;

    @FXML
    private TreeTableView<?> EventTbl;

    @FXML
    void addEventClicked(ActionEvent event) throws RemoteException, NotBoundException {
        try
        {
            CreateEventDao ced = (CreateEventDao) Naming.lookup("rmi://localhost/Event");
            CreateEvent ce = new CreateEvent();
            ce.setEventTitle(EventTitle.getText());
            ce.setEventDescription(EventDesc.getText());
            LocalDate ed=EventDate.getValue();
            ce.setEventDate(ed);
            System.out.println(ed);
            ced.eventCreate(ce);

        } catch (Exception o) {
            System.out.println(o);
        }

    }

}

