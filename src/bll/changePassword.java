/**
 * @author Rashim Joshi
 * This is a completely encapsulated java class.
 * It has a private data member and getter and setter methods.
 */

package bll;

import java.io.Serializable;

public class changePassword implements Serializable {
    private String user_password;
    private static final long serialVersionUID = 1L;
    public changePassword(String user_password){
        this.user_password = user_password;
    }
    public changePassword(){

    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
