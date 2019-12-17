package bll;

import java.io.Serializable;

public class Student implements Serializable
{
    private String student_SN;
    private String student_ID;
    private String name;
    private String email;
    private String course;
    private String level;

    private static final long serialVersionUID = 1L;

    public Student(String student_SN, String student_ID, String name, String course, String email, String level)
    {
        this.student_SN = student_SN;
        this.student_ID = student_ID;
        this.name = name;
        this.course = course;
        this.email = email;
        this.level = level;
    }

    public Student() {

    }

    public String getStudent_SN()
    {
        return student_SN;
    }

    public void setStudent_SN(String student_SN)
    {
        this.student_SN = student_SN;
    }

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
