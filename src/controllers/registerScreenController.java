package controllers;

import bll.Register;
import dao.RegisterDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class registerScreenController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private TextField UID;

    @FXML
    private Button registerStudentBtn;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;



    @FXML
    private Button registerFacultyBtn;

    @FXML
    void loginClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/loginScreen.fxml"));
        registerPane.getChildren().setAll(pane);
    }

    @FXML
    void registerStudentClicked() throws RemoteException, NotBoundException {
        try
        {
            RegisterDao rd = (RegisterDao) Naming.lookup("rmi://localhost/Register");
            Register r = new Register();

            r.setUID(UID.getText());
            r.setPassword(password.getText());
            System.out.println(r.getUID());
            System.out.println(r.getPassword());
            rd.addUser(r);
        }
        catch(Exception e)
        {
            System.out.print(e);
        }

    }

    @FXML
    void registerFacultyClicked(ActionEvent event) {

    }

}
