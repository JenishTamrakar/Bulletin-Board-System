package controllers;

import bll.viewFeedback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.viewFeedbackDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;


public class giveFeedbackController {

    @FXML
    private AnchorPane feedbackpane;


    @FXML
    private JFXTextField feedbackTitleTxt;

    @FXML
    private JFXTextArea feedbackDescriptionTxt;

    @FXML
    private JFXButton submitFeedback;

    @FXML
    private JFXTextField EveSN;

    /**
     * submit feedback details
     * @param event
     */
    @FXML
    void submitFeedback(ActionEvent event) {
        try {
            viewFeedbackDao vfd = (viewFeedbackDao) Naming.lookup("rmi://localhost/ViewFeedback");
            viewFeedback vf = new viewFeedback();
            vf.setFeedback_title(feedbackTitleTxt.getText());
            vf.setFeedback_description(feedbackDescriptionTxt.getText());
            vf.setFeedback_date(LocalDate.now().toString());
            vfd.addFeedback(vf);
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Feedback Submitted");
            alert.setContentText("Feedback has been successfully submitted.");
            alert.showAndWait();
            feedbackTitleTxt.clear();
            feedbackDescriptionTxt.clear();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    //navigation to dashboard window
    public void goBack(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentDashboard.fxml")));
        feedbackpane.getChildren().setAll(pane);

    }

}
