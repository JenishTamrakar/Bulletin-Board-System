/**
 * @author Jenish Tamrakar
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;
import java.time.LocalDate;


public class Assignment implements Serializable
{

    //private data member

    private String Ass_ID;
    private String Ass_title;
    private String Ass_level;
    private String Ass_course;
    private String Ass_unit;
    private String Ass_date;

    private static final  long serialVersionUID=1L;

    //Constructor with parameters
    public Assignment(String Ass_ID, String Ass_title, String Ass_level, String Ass_course, String Ass_unit, String Ass_date)
    {
        this.Ass_ID = Ass_ID;
        this.Ass_title = Ass_title;
        this.Ass_level = Ass_level;
        this.Ass_course = Ass_course;
        this.Ass_unit = Ass_unit;
        this.Ass_date = Ass_date;
    }

    //Empty constructor
    public Assignment() {

    }

    //getter method for Assignment ID
    public String getAss_ID() {
        return Ass_ID;
    }

    public void setAss_ID(String ass_ID) {
        Ass_ID = ass_ID;
    }

    //getter method for Assignment Title
    public String getAss_title() {
        return Ass_title;
    }

    //setter method for Assignment Title
    public void setAss_title(String ass_title) {
        Ass_title = ass_title;
    }

    //getter method for Assignment Level
    public String getAss_level() {
        return Ass_level;
    }

    //setter method for Assignment level
    public void setAss_level(String ass_level) {
        Ass_level = ass_level;
    }

    //getter method for Assignment Course
    public String getAss_course() {
        return Ass_course;
    }

    //setter method for Assignment course
    public void setAss_course(String ass_course) {
        Ass_course = ass_course;
    }

    //getter method for Assignment unit
    public String getAss_unit() {
        return Ass_unit;
    }

    //setter method for Assignment unit
    public void setAss_unit(String ass_unit) {
        Ass_unit = ass_unit;
    }

    //getter method for Assignment Date
    public String getAss_date() {
        return Ass_date;
    }

    //setter method for Assignment date
    public void setAss_date(String ass_date) {
        Ass_date = ass_date;
    }
}
