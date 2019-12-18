package bll;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class FeeDetails implements Serializable
{
    private String Fee_ID;
    private String Fee_Amt;
    private String Deadline_Date;
    private String Fee_Details;
    private String Student_course;
    private String Student_level;

    private static final  long serialVersionUID=1L;

    public FeeDetails(String Fee_ID, String Fee_Amt, String Deadline_Date, String Fee_Details, String Student_course, String Student_level)
    {
        this.Fee_ID = Fee_ID;
        this.Fee_Amt = Fee_Amt;
        this.Deadline_Date = Deadline_Date;
        this.Fee_Details = Fee_Details;
        this.Student_course = Student_course;
        this.Student_level = Student_level;
    }

    public FeeDetails() {

    }

    public String getFee_ID() {return Fee_ID;}

    public void setFee_ID(String Fee_ID) { this.Fee_ID = Fee_ID; }

    public String getFee_Amt() {return Fee_Amt;}

    public void setFee_Amt(String Fee_Amt) { this.Fee_Amt = Fee_Amt; }

    public String getDeadline_Date() {return Deadline_Date;}

    public void setDeadline_Date(String Deadline_Date) {this.Deadline_Date=Deadline_Date;}

    public String getFee_Details() {return  Fee_Details;}

    public void setFee_Details(String fee_Details) { this.Fee_Details = fee_Details; }

    public String getStudent_course() {return Student_course;}

    public void setStudent_course(String student_course) { this.Student_course = student_course;}

    public String getStudent_level() {return Student_level; }

    public void setStudent_level(String student_level) {this.Student_level = student_level;}
}
