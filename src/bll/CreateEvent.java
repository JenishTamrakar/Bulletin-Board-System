package bll;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEvent implements Serializable {
    public String eventTitle;
    public String eventDescription;
    public String eventDate;
    public String eventTime;

    private static final long serialVersionUID = 1L;

    public String getEventTitle() { return eventTitle; }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
