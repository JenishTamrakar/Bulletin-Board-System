/**
 * @author Jenish Tamrakar
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;

public class Notice implements Serializable
{
    //private data member
    private String noticeID;
    private String noticeTitle;
    private String noticeDate;
    private String noticeDescription;
    private static final long serialVersionUID = 1L;

    //constructor with parameters
    public Notice(String noticeID, String noticeTitle, String noticeDate, String noticeDescription)
    {
        this.noticeID = noticeID;
        this.noticeTitle = noticeTitle;
        this.noticeDate = noticeDate;
        this.noticeDescription = noticeDescription;
    }

    //empty constructor
    public Notice()
    {

    }

    //getter method for notice ID
    public String getNoticeID() {
        return noticeID;
    }

    //setter method for notice ID
    public void setNoticeID(String noticeID) {
        this.noticeID = noticeID;
    }

    //getter method for notice title
    public String getNoticeTitle() {
        return noticeTitle;
    }

    //setter method for notice title
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    //getter method for notice date
    public String getNoticeDate() {
        return noticeDate;
    }

    //setter method for notice date
    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    //getter method for notice description
    public String getNoticeDescription() {
        return noticeDescription;
    }

    //setter method for notice description
    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

}
