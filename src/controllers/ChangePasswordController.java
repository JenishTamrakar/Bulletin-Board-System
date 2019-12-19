package controllers;

import bll.changePassword;
import dao.changePasswordDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChangePasswordController {

    @FXML
    private TextField password;

    @FXML
    private Button changePassword;

    @FXML
    private TextField confirmPassword;

    @FXML
    void changePassword(ActionEvent event) {
        try {
            changePasswordDao cpd = (changePasswordDao) Naming.lookup("rmi://localhost/changePassword");
            changePassword cp =new changePassword();
            cp.setUser_password(password.getText());
            cpd.updatePassword(cp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Changed");
            alert.setContentText("Your Password has been changed successfully ");
            password.setText(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

}
