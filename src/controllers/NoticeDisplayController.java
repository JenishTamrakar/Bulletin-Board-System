package controllers;

import bll.Notice;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.NoticeDao;
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
import java.util.ResourceBundle;

public class NoticeDisplayController implements Initializable {
    @FXML
    private JFXTextField txtNoticeTitle;

    @FXML
    private JFXTextArea txtNoticeDesc;

    @FXML
    private JFXDatePicker txtNoticeDate;

    @FXML
    private TableView<Notice> NoticeTbl;

    @FXML
    private TableColumn<Notice, String> notice_title;

    ObservableList<Notice> nlist = FXCollections.observableArrayList();

    @FXML
    void NoticeTblClicked(MouseEvent event)
    {
        if (event.getClickCount() > 1)
        {
            onEdit();
        }
    }

    void onEdit()
    {
        // check the table's selected item and get selected item
        if (NoticeTbl.getSelectionModel().getSelectedItem() != null) {
            Notice selectedNotice = NoticeTbl.getSelectionModel().getSelectedItem();
            txtNoticeDate.setValue(LocalDate.parse(selectedNotice.getNoticeDate()));
            txtNoticeDesc.setText(selectedNotice.getNoticeDescription());
            txtNoticeTitle.setText(selectedNotice.getNoticeTitle());
        }
    }

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

                notice_title.setCellValueFactory(new PropertyValueFactory<>("noticeTitle"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNotices();
    }
}
