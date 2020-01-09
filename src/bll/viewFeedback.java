/**
 * @author Rashim Joshi
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;

public class viewFeedback implements Serializable {
    //private data member
    private String feedback_title;
    private  String feedback_description;
    private String feedback_date;

    private static final long serialVersionUID = 1L;

    //constructor with parameters
    public viewFeedback(String feedback_title, String feedback_description, String feedback_date){
        this.feedback_title = feedback_title;
        this.feedback_description = feedback_description;
        this.feedback_date = feedback_date;
    }

    public viewFeedback() {

    }

    public viewFeedback(String feedback_id, String feedback_title, String feedback_description, String feedback_date) {
    }

    //getter method for feedback title
    public String getFeedback_title() {
        return feedback_title;
    }

    //setter method for feedback title
    public void setFeedback_title(String feedback_title) {
        this.feedback_title = feedback_title;
    }

    //getter method for feedback description
    public String getFeedback_description() {
        return feedback_description;
    }

    //setter method for feedback description
    public void setFeedback_description(String feedback_description) { this.feedback_description = feedback_description; }

    //getter method for feedback date
    public String getFeedback_date() {
        return feedback_date;
    }

    //setter method for feedback date
    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }
}
