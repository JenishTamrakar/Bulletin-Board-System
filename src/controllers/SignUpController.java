package controllers;

import dao.signUpDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private TextField UID;

    @FXML
    private Button validateUID;
    @FXML
    private AnchorPane signUpPane;

    @FXML
    public  String validateUID(){

        if (UID.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Enter Your ID number!");
            alert.setContentText("Use your college ID card number!");
            alert.showAndWait();
        }else {
            System.out.println("checking UID");
            String user_id = UID.getText();
            try {
                signUpDao sud = (signUpDao) Naming.lookup("rmi://localhost/SignUp");
                ResultSet rs = sud.checkID(user_id);
                while (rs.next()){
                    if (user_id.equals(rs.getString(1))){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("UID existin system !");
                        alert.setContentText("You can change your password and login");
                        alert.showAndWait();
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/changePassword.fxml"));
                        signUpPane.getChildren().setAll(pane);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("UID doesnt exist!");

                        alert.setContentText("Check Your user Id !");

                        alert.showAndWait();
                    }
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;


    }
}
