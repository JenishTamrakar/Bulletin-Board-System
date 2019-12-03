package bll;

import java.io.Serializable;

public class Student implements Serializable
{
    private String student_ID;
    private String name;
    private String email;
    private String course;
    private String level;

    private static final long serialVersionUID = 1L;

    public String getStudent_ID()
    {
        return student_ID;
    }

    public void setStudent_ID(String student_ID)
    {
        this.student_ID = student_ID;
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

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

}
