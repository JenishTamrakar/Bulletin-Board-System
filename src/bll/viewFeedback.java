/**
 * @author Rashim Joshi
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;

public class viewFeedback implements Serializable {
    private String feedback_id;
    private String feedback_title;
    private  String feedback_description;
    private String feedback_date;
    private static final long serialVersionUID = 1L;

    public viewFeedback(String feedback_id,String feedback_title, String feedback_description, String feedback_date){
        this.feedback_id=feedback_id;
        this.feedback_title = feedback_title;
        this.feedback_description = feedback_description;
        this.feedback_date = feedback_date;
    }

    public viewFeedback() {

    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_title() {
        return feedback_title;
    }

    public void setFeedback_title(String feedback_title) {
        this.feedback_title = feedback_title;
    }

    public String getFeedback_description() {
        return feedback_description;
    }

    public void setFeedback_description(String feedback_description) {
        this.feedback_description = feedback_description;
    }

    public String getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }
}
