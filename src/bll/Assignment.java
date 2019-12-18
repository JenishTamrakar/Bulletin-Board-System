package bll;

import java.io.Serializable;
import java.time.LocalDate;

public class Assignment implements Serializable
{
    private String Ass_ID;
    private String Ass_title;
    private String Ass_level;
    private String Ass_course;
    private String Ass_unit;
    private String Ass_date;

    private static final  long serialVersionUID=1L;

    public Assignment(String Ass_ID, String Ass_title, String Ass_level, String Ass_course, String Ass_unit, String Ass_date)
    {
        this.Ass_ID = Ass_ID;
        this.Ass_title = Ass_title;
        this.Ass_level = Ass_level;
        this.Ass_course = Ass_course;
        this.Ass_unit = Ass_unit;
        this.Ass_date = Ass_date;
    }

    public Assignment() {

    }

    public String getAss_ID() {
        return Ass_ID;
    }

    public void setAss_ID(String ass_ID) {
        Ass_ID = ass_ID;
    }

    public String getAss_title() {
        return Ass_title;
    }

    public void setAss_title(String ass_title) {
        Ass_title = ass_title;
    }

    public String getAss_level() {
        return Ass_level;
    }

    public void setAss_level(String ass_level) {
        Ass_level = ass_level;
    }

    public String getAss_course() {
        return Ass_course;
    }

    public void setAss_course(String ass_course) {
        Ass_course = ass_course;
    }

    public String getAss_unit() {
        return Ass_unit;
    }

    public void setAss_unit(String ass_unit) {
        Ass_unit = ass_unit;
    }

    public String getAss_date() {
        return Ass_date;
    }

    public void setAss_date(String ass_date) {
        Ass_date = ass_date;
    }
}
