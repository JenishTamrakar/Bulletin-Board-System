/**
 * @author Roshan Shrestha
 * This controller class is used to check the user details and login to the system.
 */

package controllers;

import dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public  static  String id;
    public static String le;



    //display alert dialog
    @FXML
    void registerClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hello, New User");
        alert.setHeaderText("Don't have a account ?");
        alert.setContentText("Contact college admin to get your account created !");

        alert.showAndWait();

    }

    //check login details
    @FXML
    public String checkDetail(){
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
            le=UID.getText();
            System.out.println("UID = "+user_id+" Password = "+user_password);
            try {
                LoginDao ld = (LoginDao) Naming.lookup("rmi://localhost/Login");
                ResultSet rs= ld.checkUser(user_id, user_password);
                if (rs.next()) {
                    System.out.println(rs.getString(1));
                    if (user_id.equals(rs.getString(1)) && user_password.equals(rs.getString(2)) && rs.getString(3).equals("admin")) {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/AdminDashboard.fxml"));
                        loginPane.getChildren().setAll(pane);
                    } else if (user_id.equals(rs.getString(1)) && user_password.equals(rs.getString(2)) && rs.getString(3).equals("faculty")) {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/facultyDashboard.fxml"));
                        loginPane.getChildren().setAll(pane);
                    } else if
                    (user_id.equals(rs.getString(1)) && user_password.equals(rs.getString(2)) && rs.getString(3).equals("student"))
                    {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/studentDashboard.fxml"));
                        loginPane.getChildren().setAll(pane);
                    }
                }
                else {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Wrong login details !");
                        alert.setContentText("Either user ID or password field is invalid !");

                        alert.showAndWait();
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
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void getAccount(WindowEvent windowEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hello, New User");
        alert.setHeaderText("Don't have a account ?");
        alert.setContentText("Contact college admin to get your account created !");

        alert.showAndWait();
    }
}
