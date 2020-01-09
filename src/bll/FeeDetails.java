/**
 * @author Jenish Tamrakar
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class FeeDetails implements Serializable
{
    //private data member
    private String Fee_ID;
    private String Fee_Amt;
    private String Deadline_Date;
    private String Fee_Details;
    private String Student_course;
    private String Student_level;

    private static final  long serialVersionUID=1L;

    //constructor with parameter
    public FeeDetails(String Fee_Amt, String Deadline_Date, String Fee_Details, String Student_course, String Student_level)
    {
        this.Fee_Amt = Fee_Amt;
        this.Deadline_Date = Deadline_Date;
        this.Fee_Details = Fee_Details;
        this.Student_course = Student_course;
        this.Student_level = Student_level;
    }

    public FeeDetails(int i, String fee_id, String fee_amount, String fee_deadline_date, String fee_details, String student_course, String student_level) {
    }

    public FeeDetails() {

    }

    //getter method for Fee ID
    public String getFee_ID() {return Fee_ID;}

    //setter method for fee ID
    public void setFee_ID(String Fee_ID) { this.Fee_ID = Fee_ID; }

    //getter method for Fee Amount
    public String getFee_Amt() {return Fee_Amt;}

    //setter method for fee amount
    public void setFee_Amt(String Fee_Amt) { this.Fee_Amt = Fee_Amt; }

    //getter method for Fee Deadline date
    public String getDeadline_Date() {return Deadline_Date;}

    //setter method for fee deadline date
    public void setDeadline_Date(String Deadline_Date) {this.Deadline_Date=Deadline_Date;}

    //getter method for Fee Details
    public String getFee_Details() {return  Fee_Details;}

    //setter method for fee details
    public void setFee_Details(String fee_Details) { this.Fee_Details = fee_Details; }

    //getter method for Fee course
    public String getStudent_course() {return Student_course;}

    //setter method for fee course
    public void setStudent_course(String student_course) { this.Student_course = student_course;}

    //getter method for Fee level
    public String getStudent_level() {return Student_level; }

    //setter method for fee level
    public void setStudent_level(String student_level) {this.Student_level = student_level;}

}
