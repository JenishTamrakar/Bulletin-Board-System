package bll;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Serializable
{
    private String Event_title;
    private LocalDate Event_date;
    private LocalTime Event_time;
    private String Event_desc;

    private static final  long serialVersionUID=1L;

    public String getEvent_title() {
        return Event_title;
    }

    public void setEvent_title(String event_title) {
        Event_title = event_title;
    }

    public LocalDate getEvent_date() {
        return Event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        Event_date = event_date;
    }

    public LocalTime getEvent_time() {
        return Event_time;
    }

    public void setEvent_time(LocalTime event_time) {
        Event_time = event_time;
    }

    public String getEvent_desc() {
        return Event_desc;
    }

    public void setEvent_desc(String event_desc) {
        Event_desc = event_desc;
    }
}
