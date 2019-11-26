package BBS.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class registerScreenController {


    @FXML
    private AnchorPane registerPane;

    @FXML
    private TextField uID;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink createAccount;

    @FXML
    void createAcc(MouseEvent event) {

    }

    @FXML
    void loginClick(ActionEvent event) throws IOException {AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/loginScreen.fxml"));
    registerPane.getChildren().setAll(pane);
    }
}
