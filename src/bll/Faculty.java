package bll;

import java.io.Serializable;

public class Faculty implements Serializable
{
    private String faculty_SN;
    private String faculty_ID;
    private String name;
    private  String pass;
    private String email;
    private String course;

    private static final long serialVersionUID = 1L;

    public Faculty()
    {

    }

    public String getFaculty_SN()
    {
        return faculty_SN;
    }

    public void setFaculty_SN(String faculty_SN)
    {
        this.faculty_SN = faculty_SN;
    }

    public String getFaculty_ID()
    {
        return faculty_ID;
    }

    public void setFaculty_ID(String faculty_ID)
    {
        this.faculty_ID = faculty_ID;
    }

    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public Faculty(String faculty_SN, String faculty_ID, String name
            , String course, String email)
    {
        this.faculty_SN = faculty_SN;
        this.faculty_ID = faculty_ID;
        this.name = name;

        this.course = course;
        this.email = email;
    }


}
