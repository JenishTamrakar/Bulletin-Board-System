package controllers;

import bll.Event;
import bll.Student;
import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import dao.EventDao;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class    EventFacultyController implements Initializable {

    String userID=loginScreenController.le;

    @FXML
    private JFXMasonryPane masonLayout;

    @FXML
    private StackPane eventRootPane;
    @FXML
    private ScrollPane scrollEvent;



    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/AssignmentCreateFaculty.fxml")));
        eventRootPane.getChildren().setAll(pane);
    }


    @FXML
    void goToNotice(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/FacultyNotice.fxml")));
        eventRootPane.getChildren().setAll(pane);

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        eventRootPane.getChildren().setAll(pane);

    }


    ObservableList<Event> evlist = FXCollections.observableArrayList();


    void loadEventDetails() {
        try {
            EventDao ed = (EventDao) Naming.lookup("rmi://localhost/Event");
            ResultSet rs = ed.getEventDetails();

            while (rs.next()) {
                evlist.add(new Event(
                        rs.getString("event_id"),
                        rs.getString("event_title"),
                        rs.getString("event_date"),
                        rs.getString("event_time"),
                        rs.getString("event_description")
                ));
            }


            ArrayList<Node> children = new ArrayList<>();
            for (int i = 0; i < evlist.size(); i++) {
                StackPane stackPane = new StackPane();
                double width = 200;
                stackPane.setPrefWidth(width);
                JFXDepthManager.setDepth(stackPane, 1);
                children.add(stackPane);


                StackPane header = new StackPane();
                VBox headerContent = new VBox();
                headerContent.setSpacing(10);
                headerContent.setPadding(new Insets(20, 10, 10, 10));
                Label eventTitle = new Label();
                Label eventDataTime = new Label();

                eventTitle.setStyle("-fx-font: 24 arial;");

                eventTitle.setText(evlist.get(i).getEvent_title());
                eventDataTime.setStyle("-fx-font: 16 arial;");

                eventDataTime.setText("Date :"+evlist.get(i).getEvent_date()+"\nTime :" + evlist.get(i).getEvent_time());

                header.getChildren().add(headerContent);
                headerContent.getChildren().addAll(eventTitle, eventDataTime);
                header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: rgb(255,255,255,0.87);");

                VBox.setVgrow(header, Priority.ALWAYS);


                StackPane descriptionBody = new StackPane();

                VBox bodyContent = new VBox();
                bodyContent.setAlignment(Pos.CENTER);
                Label eventDescription = new Label();
                bodyContent.setPadding(new Insets(0, 10, 10, 10));
//                eventDescription.setPadding(new Insets(10, 0, 0, 0));
                eventDescription.setTextFill(Color.web("#000000"));
                eventDescription.setStyle("-fx-font: 18 arial;");
                eventDescription.setText(evlist.get(i).getEvent_desc());
                bodyContent.getChildren().addAll(eventDescription);
                descriptionBody.getChildren().add(bodyContent);
                VBox content = new VBox();
                content.getChildren().addAll(header, descriptionBody);
                descriptionBody.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");


                int finalI = i;
                stackPane.setOnMouseClicked(event -> {
                    JFXDialogLayout fullDetailDialogContent = new JFXDialogLayout();
                    fullDetailDialogContent.setHeading(new Text(evlist.get(finalI).getEvent_title()));
                    JFXTextArea descriptionArea = new JFXTextArea(evlist.get(finalI).getEvent_desc());
                    descriptionArea.setWrapText(true);
                    descriptionArea.setEditable(false);
                    descriptionArea.setFocusColor(Color.web("#FFF"));
                    descriptionArea.setUnFocusColor(Color.web("#FFF"));
                    descriptionArea.setPrefHeight(130);

                    fullDetailDialogContent.setBody(descriptionArea);
                    JFXDialog fullDetailDialog = new JFXDialog(eventRootPane, fullDetailDialogContent, JFXDialog.DialogTransition.CENTER);
                    JFXButton okayButton = new JFXButton("Okay");
                    fullDetailDialogContent.setActions(okayButton);

                    okayButton.setOnAction(closeEvent -> fullDetailDialog.close());
                    fullDetailDialog.show();
                });
                stackPane.getChildren().addAll(content);

            }
            masonLayout.getChildren().addAll(children);


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TableColumn<Student, String> student_Email;
    ObservableList<Student> slist = FXCollections.observableArrayList();

    void loadStudentProfile()
    {
        try {
            StudentDao sd = (StudentDao) Naming.lookup("rmi://localhost/Student");
            ResultSet rs = sd.getProfile(userID);

            while(rs.next())
            {
                slist.add(new Student(
                        rs.getString("student_sn"),
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_course"),
                        rs.getString("student_email"),
                        rs.getString("student_level")
                ));

//                txtStudentName.setText(slist.get(0).getName());
//                txtName.setText(slist.get(0).getName());
//                txtCourse.setText(slist.get(0).getCourse());
//                txtEmail.setText(slist.get(0).getEmail());
//                txtLevel.setText(slist.get(0).getLevel());
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEventDetails();
        loadStudentProfile();
    }

    public void goToProfile(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/facultyProfile.fxml")));
        eventRootPane.getChildren().setAll(pane);
    }
}

