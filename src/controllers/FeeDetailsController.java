package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FeeDetailsController {

    @FXML
    private AnchorPane feeDetailsPane;



    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        feeDetailsPane.getChildren().setAll(pane);
    }
}
