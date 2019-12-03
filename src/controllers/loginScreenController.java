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


    @FXML
    public void checkDetail(){
        if (UID.getText().trim().isEmpty() && Password.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Enter the login details !");
            alert.setContentText("Either user ID or password field is empty !");

            alert.showAndWait();
        }else {
            System.out.println("login btn clicked");
            String user_id = UID.getText();
            String user_password = Password.getText();
            System.out.println("UID = "+user_id+" Password = "+user_password);
            try {
                LoginDao ld = (LoginDao) Naming.lookup("rmi://localhost/Login");
                Boolean rs = ld.checkUser(UID.getText(), Password.getText());
                try {
                    if (rs) {
                        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml ")));
                        System.out.println("Next page");
                        loginPane.getChildren().setAll(pane);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid details");
                        alert.setContentText("Check your UID or Password and try again !");
                        alert.showAndWait();
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

}
