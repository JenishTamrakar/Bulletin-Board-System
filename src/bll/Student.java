package bll;

import java.io.Serializable;

public class Student implements Serializable
{
    //private data member
    private String student_SN;
    private String student_ID;
    private String name;
    private String email;
    private String course;
    private String level;

    private static final long serialVersionUID = 1L;

    //constructor with parameters
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

    //getter method for student SN
    public String getStudent_SN()
    {
        return student_SN;
    }

    //setter method for student SN
    public void setStudent_SN(String student_SN)
    {
        this.student_SN = student_SN;
    }

    //getter method for student ID
    public String getStudent_ID()
    {
        return student_ID;
    }

    //setter method for student
    public void setStudent_ID(String student_ID)
    {
        this.student_ID = student_ID;
    }

    //getter method for student name
    public String getName()
    {
        return name;
    }

    //setter method for student
    public void setName(String name)
    {
        this.name = name;
    }

    //getter method for student email
    public String getEmail()
    {
        return email;
    }

    //setter method for student
    public void setEmail(String email)
    {
        this.email = email;
    }

    //getter method for student course
    public String getCourse()
    {
        return course;
    }

    //setter method for student
    public void setCourse(String course)
    {
        this.course = course;
    }

    //getter method for student level
    public String getLevel()
    {
        return level;
    }

    //setter method for student
    public void setLevel(String level)
    {
        this.level = level;
    }
}
