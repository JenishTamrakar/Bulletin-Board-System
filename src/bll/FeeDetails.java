package bll;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class FeeDetails implements Serializable
{
    private String Fee_Amt;
    private LocalDate Deadline_Date;
    private String Fee_Details;
    private String Student_course;
    private String Student_level;

    private static final  long serialVersionUID=1L;

    public String getFee_Amt() {return Fee_Amt;}

    public void setFee_Amt(String Fee_Amt) { this.Fee_Amt = Fee_Amt; }

    public LocalDate getDeadline_Date() {return Deadline_Date;}

    public void setDeadline_Date(LocalDate Deadline_Date) {this.Deadline_Date=Deadline_Date;}

    public String getFee_Details() {return  Fee_Details;}

    public void setFee_Details(String fee_Details) { this.Fee_Details = fee_Details; }

    public String getStudent_course() {return Student_course;}

    public void setStudent_course(String student_course) { this.Student_course = student_course;}

    public String getStudent_level() {return Student_level; }

    public void setStudent_level(String student_level) {this.Student_level = student_level;}
}
