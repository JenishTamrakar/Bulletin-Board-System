package controllers;

import bll.FeeDetails;
import dao.FeeDetailsDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentFeeDetailsController implements Initializable {
    @FXML
    private TableView<FeeDetails> FeeTbl;

    @FXML
    private TableColumn<String, String> fee_SN;

    @FXML
    private TableColumn<FeeDetails, String> fee_Description;

    @FXML
    private TableColumn<FeeDetails, String> fee_Amount;

    @FXML
    private TableColumn<FeeDetails, String> fee_Deadline;

    ObservableList<FeeDetails> fdlist = FXCollections.observableArrayList();

    void loadFeeDetails()
    {
        try {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            ResultSet rs = fdd.getFeeDetails();
            int i = 1;
            while(rs.next())
            {
                fdlist.add(new FeeDetails(
                        i,
                        rs.getString("fee_id"),
                        rs.getString("fee_amount"),
                        rs.getString("fee_deadline_date"),
                        rs.getString("fee_details"),
                        rs.getString("student_course"),
                        rs.getString("student_level")
                ));
                fee_SN.setCellValueFactory(new PropertyValueFactory<>("sn"));
                fee_Amount.setCellValueFactory(new PropertyValueFactory<>("Fee_Amt"));
                fee_Description.setCellValueFactory(new PropertyValueFactory<>("Fee_Details"));
                fee_Deadline.setCellValueFactory(new PropertyValueFactory<>("Deadline_Date"));
                FeeTbl.setItems(fdlist);
                i++;
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
    public void initialize(URL location, ResourceBundle resources)
    {
        loadFeeDetails();
    }
}
