package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class loginScreenController {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField UID;

    @FXML
    private Button login;

    @FXML
    private PasswordField Password;

    @FXML
    private Hyperlink createAccount;

    @FXML
    void registerClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/registerScreen.fxml"));
        loginPane.getChildren().setAll(pane);
    }
    Alert a = new Alert((Alert.AlertType.NONE));

    @FXML
    public void checkDetail(){
        System.out.println("login btn clicked");
        String userID = UID.getText();
        String userPassword = Password.getText();
        System.out.println("UID = "+userID+" Password = "+userPassword);
        try {
            LoginDao ld = (LoginDao) Naming.lookup("rmi://rmi://localhost/Login");
            Boolean rs = ld.checkUser(UID.getText(), Password.getText());
            try {
                if (rs) {
                    AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentDashboard.fxml")));
                    System.out.println("Next page");
                    loginPane.getChildren().setAll(pane);
                } else {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Invalid ID or Password");
                    a.show();
                }
            } catch (IOException ex) {
                System.out.println(ex);
                System.out.println("Could not connect database");
            }
        }catch (RemoteException re){
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        }catch (ArithmeticException ae){
            System.out.println("java.lang.ArithematicException");
            System.out.println(ae);
        }catch (NotBoundException e){
            e.printStackTrace();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

}
