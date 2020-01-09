/**
 * @author Roshan Shrestha
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;

public class Faculty implements Serializable
{

    //private data member

    private String faculty_SN;
    private String faculty_ID;
    private String name;
    private  String pass;
    private String email;
    private String course;

    private static final long serialVersionUID = 1L;

    //empty constructor
    public Faculty()
    {

    }


    //getter method for faculty SN
    public String getFaculty_SN()
    {
        return faculty_SN;
    }

    //setter method for faculty SN
    public void setFaculty_SN(String faculty_SN)
    {
        this.faculty_SN = faculty_SN;
    }

    //getter method for faculty ID
    public String getFaculty_ID()
    {
        return faculty_ID;
    }

    //setter method for faculty ID
    public void setFaculty_ID(String faculty_ID)
    {
        this.faculty_ID = faculty_ID;
    }

    //getter method for faculty Name
    public String getName()
    {
        return name;
    }

    //getter method for faculty password
    public String getPass()
    {
        return pass;
    }


    //setter method for faculty password
    public void setPass(String pass){
        this.pass = pass;
    }

    //setter method for faculty name
    public void setName(String name)
    {
        this.name = name;
    }

    //getter method for faculty email
    public String getEmail()
    {
        return email;
    }

    //setter method for faculty email
    public void setEmail(String email)
    {
        this.email = email;
    }

    //getter method for faculty course
    public String getCourse()
    {
        return course;
    }

    //setter method for faculty course
    public void setCourse(String course)
    {
        this.course = course;
    }


    //constructor with parameters
    public Faculty(String faculty_SN, String faculty_ID, String name, String course, String email)
    {
        this.faculty_SN = faculty_SN;
        this.faculty_ID = faculty_ID;
        this.name = name;

        this.course = course;
        this.email = email;
    }


}
