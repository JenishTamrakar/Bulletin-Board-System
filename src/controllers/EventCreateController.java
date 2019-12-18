package controllers;

import bll.Event;
import com.jfoenix.controls.*;
import dao.EventDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
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

public class EventCreateController implements Initializable {

    @FXML
    private AnchorPane eventPane;

    @FXML
    private JFXButton SgnOut;

    @FXML
    private JFXTextArea EventDesc;

    @FXML
    private JFXTextField EventTitle;

    @FXML
    private TableView<Event> EventsTbl;

    @FXML
    private TableColumn<Event, String> event_SN;

    @FXML
    private TableColumn<Event, String> event_title;

    @FXML
    private TableColumn<Event, String> event_Date;

    @FXML
    private TableColumn<Event, String> event_time;

    @FXML
    private TableColumn<Event, String> event_des;

    @FXML
    private JFXTextField EveSN;
    @FXML
    private JFXButton AddEvent;

    @FXML
    private JFXButton DelEvent;

    @FXML
    private JFXButton ResetBtn;

    @FXML
    private JFXButton UpdtEvent;
    @FXML
    private JFXDatePicker DateEvent;

    @FXML
    private JFXTimePicker TimeEvent;

    ObservableList<Event> evlist = FXCollections.observableArrayList();

    @FXML
    void addEventClicked(ActionEvent event) {
        LocalDate ld = DateEvent.getValue();
        LocalTime lt = TimeEvent.getValue();
        LocalDate l = LocalDate.now();
//        System.out.println(lt.toString());
        try
        {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            Event ev = new Event();
            ev.setEvent_title(EventTitle.getText());
            ev.setEvent_date(ld.toString());
            ev.setEvent_time(lt.toString());
            ev.setEvent_desc(EventDesc.getText());
            if(ld.isAfter(l))
            {
                ed.addEventDet(ev);
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Event Added");
                alert.setContentText("Event has been successfully added.");
                alert.showAndWait();
                EveSN.clear();
                EventTitle.clear();
                EventDesc.clear();
                DateEvent.setValue(null);
                TimeEvent.setValue(null);
                EventsTbl.getItems().clear();
                loadEventDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }


        } catch (Exception o) {
            System.out.println(o);
        }

    }

    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        eventPane.getChildren().setAll(pane);
    }


    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void goToStudentRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentRecords.fxml")));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    void delEventClicked(ActionEvent event)
    {
        try {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            Event ev = new Event();
            ev.setEvent_ID(EveSN.getText());
            ed.deleteEventDet(ev);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Removed");
            alert.setContentText("Event Record Successfully Removed!");

            EveSN.clear();
            EventTitle.clear();
            EventDesc.clear();
            DateEvent.setValue(null);
            TimeEvent.setValue(null);
            EventsTbl.getItems().clear();
            loadEventDetails();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void resetBtnClicked(ActionEvent event)
    {
        EveSN.clear();
        EventTitle.clear();
        EventDesc.clear();
        DateEvent.setValue(null);
        TimeEvent.setValue(null);
        EventsTbl.getItems().clear();
        loadEventDetails();
    }

    @FXML
    void updtEventClicked(ActionEvent event)
    {
        LocalDate ld = DateEvent.getValue();
        LocalTime lt = TimeEvent.getValue();
        LocalDate l = LocalDate.now();
//        System.out.println(lt.toString());
        try
        {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            Event ev = new Event();
            ev.setEvent_ID(EveSN.getText());
            ev.setEvent_title(EventTitle.getText());
            ev.setEvent_date(ld.toString());
            ev.setEvent_time(lt.toString());
            ev.setEvent_desc(EventDesc.getText());
            if(ld.isAfter(l))
            {
                ed.updateEventDet(ev);
                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Event Updated");
                alert.setContentText("Event has been successfully updated.");
                alert.showAndWait();
                EveSN.clear();
                EventTitle.clear();
                EventDesc.clear();
                DateEvent.setValue(null);
                TimeEvent.setValue(null);
                EventsTbl.getItems().clear();
                loadEventDetails();
            }
            else if(ld.isBefore(l))
            {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Incorrect Date");
                alert.setContentText("The date cannot be set.");
                alert.showAndWait();
            }
        } catch (Exception o) {
            System.out.println(o);
        }
    }

    void loadEventDetails()
    {
        try {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            ResultSet rs = ed.getEventDetails();

            while(rs.next())
            {
                evlist.add(new Event(
                        rs.getString("event_id"),
                        rs.getString("event_title"),
                        rs.getString("event_date"),
                        rs.getString("event_time"),
                        rs.getString("event_description")
                ));

                event_SN.setCellValueFactory(new PropertyValueFactory<>("Event_ID"));
                event_title.setCellValueFactory(new PropertyValueFactory<>("Event_title"));
                event_Date.setCellValueFactory(new PropertyValueFactory<>("Event_date"));
                event_time.setCellValueFactory(new PropertyValueFactory<>("Event_time"));
                event_des.setCellValueFactory(new PropertyValueFactory<>("Event_desc"));
                EventsTbl.setItems(evlist);
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
    void EventsTblMouseClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    void onEdit()
    {
        // check the table's selected item and get selected item
        if (EventsTbl.getSelectionModel().getSelectedItem() != null) {
            Event selectedEvent = EventsTbl.getSelectionModel().getSelectedItem();
            EveSN.setText(selectedEvent.getEvent_ID());
            DateEvent.setValue(LocalDate.parse(selectedEvent.getEvent_date()));
            TimeEvent.setValue(LocalTime.parse(selectedEvent.getEvent_time()));
            EventDesc.setText(selectedEvent.getEvent_desc());
            EventTitle.setText(selectedEvent.getEvent_title());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEventDetails();
    }
}
