package controllers;

import bll.Event;
import bll.Student;

import bll.viewFeedback;
import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import dao.viewFeedbackDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewFeedbackController implements Initializable {

    @FXML
    private AnchorPane feedbackPane;
    @FXML
    private JFXMasonryPane masonLayout;

    @FXML
    private StackPane feedbackRootPane;

    @FXML
    private TextArea feedbackDescriptionTxt;

    @FXML
    private TextField feedbackTitleTxt;

    @FXML
    private TextField feedbackDateTxt;
    @FXML
    private Button backBtn;

        ObservableList<viewFeedback> feedbackList = FXCollections.observableArrayList();

    @FXML
    void goToAssignments(ActionEvent event) throws  IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreate.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AdminDashboard.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventCreate.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFacultyRecords(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyRecords.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToFeeDetails(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/FeeDetails.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }

    @FXML
    void goToNotices(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/NoticeCreate.fxml"));
        feedbackPane.getChildren().setAll(pane);

    }
    @FXML
    void goBack(ActionEvent event) throws IOException{
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentDashboard.fxml")));
        feedbackPane.getChildren().setAll(pane);
    }
    @FXML
    void feedbackRecords() {
        try {
            viewFeedbackDao vd =(viewFeedbackDao) Naming.lookup("rmi://localhost/ViewFeedback");
            ResultSet rs =vd.getFeedback();
            System.out.println(rs);

            while (rs.next()){
                feedbackList.add(new viewFeedback(
                        rs.getString("feedback_id"),
                        rs.getString("feedback_title"),
                        rs.getString("feedback_description"),
                        rs.getString("feedback_date")
                ));
                for (int i = 0; i < feedbackList.size(); i++) {

                    ArrayList<Node> children = new ArrayList<>();
                    StackPane stackPane = new StackPane();
                    double width = 1000;
                    stackPane.setPrefWidth(width);
                    JFXDepthManager.setDepth(stackPane, 3);
                    children.add(stackPane);


                    StackPane header = new StackPane();
                    VBox headerContent = new VBox();
                    headerContent.setSpacing(10);
                    headerContent.setPadding(new Insets(20, 10, 10, 10));
                    Label eventTitle = new Label();
                    Label eventDataTime = new Label();

                    eventTitle.setStyle("-fx-font: 24 arial;");

                    eventTitle.setText(feedbackList.get(i).getFeedback_title());
                    eventDataTime.setStyle("-fx-font: 16 arial;");

                    eventDataTime.setText("Date :"+feedbackList.get(i).getFeedback_date());

                    header.getChildren().add(headerContent);
                    headerContent.getChildren().addAll(eventTitle, eventDataTime);
                    header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: rgb(255,255,255,0.87);");

                    VBox.setVgrow(header, Priority.ALWAYS);


                    StackPane descriptionBody = new StackPane();

                    VBox bodyContent = new VBox();
                    bodyContent.setAlignment(Pos.CENTER);
                    Label eventDescription = new Label();
                    bodyContent.setPadding(new Insets(0, 10, 10, 10));
                    eventDescription.setTextFill(Color.web("#000000"));
                    eventDescription.setStyle("-fx-font: 18 arial;");
                    eventDescription.setText(feedbackList.get(i).getFeedback_description());
                    bodyContent.getChildren().addAll(eventDescription);
                    descriptionBody.getChildren().add(bodyContent);
                    VBox content = new VBox();
                    content.getChildren().addAll(header, descriptionBody);
                    descriptionBody.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");


                    int finalI = i;
                    stackPane.setOnMouseClicked(event -> {
                        JFXDialogLayout fullDetailDialogContent = new JFXDialogLayout();
                        fullDetailDialogContent.setHeading(new Text(feedbackList.get(finalI).getFeedback_title()));
                        JFXTextArea descriptionArea = new JFXTextArea(feedbackList.get(finalI).getFeedback_description());
                        descriptionArea.setWrapText(true);
                        descriptionArea.setEditable(false);
                        descriptionArea.setFocusColor(Color.web("#FFF"));
                        descriptionArea.setUnFocusColor(Color.web("#FFF"));
                        descriptionArea.setPrefHeight(130);

                        fullDetailDialogContent.setBody(descriptionArea);
                        JFXDialog fullDetailDialog = new JFXDialog(feedbackRootPane, fullDetailDialogContent, JFXDialog.DialogTransition.CENTER);
                        JFXButton okayButton = new JFXButton("close");
                        fullDetailDialogContent.setActions(okayButton);

                        okayButton.setOnAction(closeEvent -> fullDetailDialog.close());
                        fullDetailDialog.show();
                    });
                    stackPane.getChildren().addAll(content);

                    masonLayout.getChildren().addAll(children);
                }

            }
        }catch (NotBoundException e){
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources){

        feedbackRecords();
        System.out.println(feedbackList.size());

    }

}
