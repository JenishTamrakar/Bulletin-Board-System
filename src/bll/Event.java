package bll;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Serializable
{
    private String Event_ID;
    private String Event_title;
    private String Event_date;
    private String Event_time;
    private String Event_desc;

    private static final  long serialVersionUID=1L;

    public Event(String Event_ID, String Event_title, String Event_date, String Event_time, String Event_desc)
    {
        this.Event_ID = Event_ID;
        this.Event_title = Event_title;
        this.Event_date = Event_date;
        this.Event_time = Event_time;
        this.Event_desc = Event_desc;
    }

    public Event() {

    }

    public String getEvent_ID() {
        return Event_ID;
    }

    public void setEvent_ID(String event_ID) {
        Event_ID = event_ID;
    }

    public String getEvent_title() {
        return Event_title;
    }

    public void setEvent_title(String event_title) {
        Event_title = event_title;
    }

    public String getEvent_date() {
        return Event_date;
    }

    public void setEvent_date(String event_date) {
        Event_date = event_date;
    }

    public String getEvent_time() {
        return Event_time;
    }

    public void setEvent_time(String event_time) {
        Event_time = event_time;
    }

    public String getEvent_desc() {
        return Event_desc;
    }

    public void setEvent_desc(String event_desc) {
        Event_desc = event_desc;
    }
}
