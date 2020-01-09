package bll;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEvent implements Serializable {

    //private data  member
    public String eventTitle;
    public String eventDescription;
    public String eventDate;
    public String eventTime;

    private static final long serialVersionUID = 1L;

    //getter method for event title
    public String getEventTitle() {
        return eventTitle;
    }

    //setter method for event title
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    //getter method for event description
    public String getEventDescription() {
        return eventDescription;
    }

    //setter method for event description
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    //getter method for event date
    public String getEventDate() {
        return eventDate;
    }

    //setter method for event date
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    //getter method for event  time
    public String getEventTime() {
        return eventTime;
    }

    //setter method for event time
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
