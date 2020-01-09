package controllers;

import bll.FeeDetails;
import bll.ForexResponse;
import bll.Student;
import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.FeeDetailsDao;
import dao.StudentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class StudentFeeDetailsController implements Initializable {

    String userID=loginScreenController.le;

    @FXML
    private AnchorPane studentFeeDetPane;

    @FXML
    private TableView<FeeDetails> FeeTbl;

    @FXML
    private TableColumn<String, String> fee_SN;

    @FXML
    private TableColumn<FeeDetails, String> fee_Description;

    @FXML
    private TableColumn<FeeDetails, String> fee_Amount;

    @FXML
    private TableColumn<FeeDetails, String> fee_Deadline;

    @FXML
    private Text forex;

    @FXML
    private JFXTextField feeNepali;

    @FXML
    private JFXTextField feePound;

    @FXML
    private JFXButton btnConvert;

    public String foreexchange;

    @FXML
    private Label txtStudentName;

    ObservableList<FeeDetails> fdlist = FXCollections.observableArrayList();

    public static String course_name;


    //load fee details from the fee details table
    void loadFeeDetails()
    {
        try {
            FeeDetailsDao fdd = (FeeDetailsDao) Naming.lookup("rmi://localhost/FeeDetails");
            ResultSet rs = fdd.getFeeDetailsByCourse(course_name);
            System.out.println("Course = "+course_name);
            int i = 1;
            while(rs.next())
            {
                fdlist.add(new FeeDetails(
                        i,
                        rs.getString("fee_id"),
                        rs.getString("fee_amount"),
                        rs.getString("fee_deadline_date"),
                        rs.getString("fee_details"),
                        rs.getString("student_course"),
                        rs.getString("student_level")
                ));
                fee_SN.setCellValueFactory(new PropertyValueFactory<>("sn"));
                fee_Amount.setCellValueFactory(new PropertyValueFactory<>("Fee_Amt"));
                fee_Description.setCellValueFactory(new PropertyValueFactory<>("Fee_Details"));
                fee_Deadline.setCellValueFactory(new PropertyValueFactory<>("Deadline_Date"));
                FeeTbl.setItems(fdlist);
                i++;
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

    //convert the GBP currency into NPR currency
    @FXML
    void btnConvertClicked(ActionEvent event)
    {
        double a = Double.parseDouble(feeNepali.getText()) * Double.parseDouble(forex.getText());
        feePound.setText(String.valueOf(a));
    }

    //convert the input currency into the nepali currency
    @FXML
    void feeNepaliKeyReleased(KeyEvent event)
    {
        double a = Double.parseDouble(feeNepali.getText()) * Double.parseDouble(forex.getText());
        feePound.setText(String.valueOf(a));
    }

    //connection with API and request data in JSON format
    public  void getForeignExchangerate(){
        try {
            Gson gson = new Gson();
            String url_str = "https://nrb.org.np/exportForexJSON.php";

            // Making Request
            URL url = new URL(url_str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            int responseCode = con.getResponseCode();
            System.out.println(("\nSending 'GET' request to URL : "+url));
            System.out.println("Resopnse code :"+responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!= null){
                response.append(inputLine);
            }
            in.close();
            ForexResponse forexResponse = gson.fromJson(response.toString(), ForexResponse.class);

            foreexchange = forexResponse.getConversion().getCurrency()[3].getTargetSell();
            System.out.println(foreexchange);
            forex.setText(foreexchange);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private TableColumn<Student, String> student_Email;
    ObservableList<Student> slist = FXCollections.observableArrayList();

    //laod student data from student table
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

                txtStudentName.setText(slist.get(0).getName());
//                txtName.setText(slist.get(0).getName());
//                System.out.println("Course name: "+slist.get(0).getCourse());
                course_name = slist.get(0).getCourse();
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

    //navigation to assignments window
    @FXML
    void goToAssignments(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/StdAssign.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    //navigation to events window
    @FXML
    void goToEvents(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/EventStd.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    //navigation to fee details window
    @FXML
    void goToFeeDetails(ActionEvent event) {

    }

    //navigation to feedback window
    @FXML
    void goToFeedback(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/giveFeedback.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    //navigation to notice window
    @FXML
    void goToNotice(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load((getClass().getResource("../fxml/StudentNotice.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    //navigation to profile window
    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/studentProfile.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    //navigation to login screen window
    @FXML
    void logOut(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load((getClass().getResource("../fxml/loginScreen.fxml")));
        studentFeeDetPane.getChildren().setAll(pane);
    }

    /**
     * initialize the methods
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getForeignExchangerate();
        loadStudentProfile();
        loadFeeDetails();
    }
}
