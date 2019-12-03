package bll;

import java.io.Serializable;

public class Faculty implements Serializable
{
    private String faculty_ID;
    private String name;
    private String email;
    private String course;

    private static final long serialVersionUID = 1L;

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

}
