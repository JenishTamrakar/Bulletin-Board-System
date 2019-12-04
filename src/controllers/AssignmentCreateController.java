package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class AssignmentCreateController {



    @FXML
    private JFXTextField AssignTitle;

    @FXML
    private JFXButton AddAssign;

    @FXML
    private JFXButton UpdateAssign;

    @FXML
    private TreeTableView<?> NtcTable;

    @FXML
    private TreeTableColumn<?, ?> AssignIDTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignTitleTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignLvlTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignCrseTbl;

    @FXML
    private TreeTableColumn<?, ?> AssignUnitTbl;

    @FXML
    private TreeTableColumn<?, ?> DeadDateTbl;

    @FXML
    private JFXDatePicker DeadDate;

    @FXML
    private JFXTextField AssignID;

    @FXML
    private JFXTextField AssignLvl;

    @FXML
    private JFXTextField AssignCrse;

    @FXML
    private JFXTextField AssignUnit;
}
