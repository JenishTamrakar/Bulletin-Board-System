package controllers;

import bll.Notice;
import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import dao.NoticeDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NoticeDisplayController implements Initializable {
    @FXML
    private StackPane noticeRootPane;

    @FXML
    private ScrollPane scrollNotice;

    @FXML
    private JFXMasonryPane masonLayout;

    ObservableList<Notice> nlist = FXCollections.observableArrayList();

    void loadNotices()
    {
        try {
            NoticeDao nd = (NoticeDao) Naming.lookup("rmi://localhost/Notice");
            ResultSet rs = nd.getNoticeDetails();

            while(rs.next())
            {
                nlist.add(new Notice(
                        rs.getString("notice_id"),
                        rs.getString("notice_title"),
                        rs.getString("notice_date"),
                        rs.getString("notice_description")
                ));

//                notice_title.setCellValueFactory(new PropertyValueFactory<>("noticeTitle"));
//                NoticeTbl.setItems(nlist);
            }

            ArrayList<Node> children = new ArrayList<>();
            for (int i = 0; i < nlist.size(); i++) {
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

                eventTitle.setText(nlist.get(i).getNoticeTitle());
                eventDataTime.setStyle("-fx-font: 16 arial;");

                eventDataTime.setText("Date :"+nlist.get(i).getNoticeDate());

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
                eventDescription.setText(nlist.get(i).getNoticeDescription());
                bodyContent.getChildren().addAll(eventDescription);
                descriptionBody.getChildren().add(bodyContent);
                VBox content = new VBox();
                content.getChildren().addAll(header, descriptionBody);
                descriptionBody.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");


                int finalI = i;
                stackPane.setOnMouseClicked(event -> {
                    JFXDialogLayout fullDetailDialogContent = new JFXDialogLayout();
                    fullDetailDialogContent.setHeading(new Text(nlist.get(finalI).getNoticeTitle()));
                    JFXTextArea descriptionArea = new JFXTextArea(nlist.get(finalI).getNoticeDescription());
                    descriptionArea.setWrapText(true);
                    descriptionArea.setEditable(false);
                    descriptionArea.setFocusColor(Color.web("#FFF"));
                    descriptionArea.setUnFocusColor(Color.web("#FFF"));
                    descriptionArea.setPrefHeight(130);

                    fullDetailDialogContent.setBody(descriptionArea);
                    JFXDialog fullDetailDialog = new JFXDialog(noticeRootPane, fullDetailDialogContent, JFXDialog.DialogTransition.CENTER);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNotices();
    }
}
