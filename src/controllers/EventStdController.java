package controllers;

import bll.Event;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.EventDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

public class EventStdController implements Initializable
{
    @FXML
    private JFXTextField txtEventTitle;

    @FXML
    private JFXDatePicker txtEventDate;

    @FXML
    private JFXTimePicker txtEventTime;

    @FXML
    private JFXTextArea txtEventDesc;

    @FXML
    private TableView<Event> EventTbl;

    @FXML
    private TableColumn<Event, String> event_title;

    ObservableList<Event> evlist = FXCollections.observableArrayList();

    @FXML
    void EventTbl(MouseEvent event) {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    void onEdit()
    {
        // check the table's selected item and get selected item
        if (EventTbl.getSelectionModel().getSelectedItem() != null) {
            Event selectedEvent = EventTbl.getSelectionModel().getSelectedItem();
            txtEventDate.setValue(LocalDate.parse(selectedEvent.getEvent_date()));
            txtEventTime.setValue(LocalTime.parse(selectedEvent.getEvent_time()));
            txtEventDesc.setText(selectedEvent.getEvent_desc());
            txtEventTitle.setText(selectedEvent.getEvent_title());
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

                event_title.setCellValueFactory(new PropertyValueFactory<>("Event_title"));
                EventTbl.setItems(evlist);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEventDetails();
    }
}
